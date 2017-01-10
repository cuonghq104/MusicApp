package techkids.cuong.finallab2.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import techkids.cuong.finallab2.R;
import techkids.cuong.finallab2.databases.DbContext;
import techkids.cuong.finallab2.events.ChangeFragmentEvent;
import techkids.cuong.finallab2.events.HideToolbarEvent;
import techkids.cuong.finallab2.fragments.GenreListFragment;
import techkids.cuong.finallab2.fragments.StartFragment;
import techkids.cuong.finallab2.models.Genre;
import techkids.cuong.finallab2.models.ListGenre;
import techkids.cuong.finallab2.networks.models.Module;
import techkids.cuong.finallab2.networks.models.SubGenre;
import techkids.cuong.finallab2.networks.services.GetGenreService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();

    private Toolbar toolbar;

    @BindView(R.id.gap)
    View gap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getDataFromInternet();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void getDataFromRealm() {
        RealmResults<Genre> list = DbContext.getInstance().getAllGenre();

        for (Genre genre : list) {
            ListGenre.add(genre);
        }

        changeFragment(new StartFragment(), false);
    }

    private void changeFragment(Fragment fragment, boolean addToBackStack) {

        if (addToBackStack)
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).addToBackStack(null).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();

    }

    private void getDataFromInternet() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rss.itunes.apple.com/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetGenreService getGenreService = retrofit.create(GetGenreService.class);

        Call<List<Module>> call = getGenreService.getAllGenres();

        call.enqueue(new Callback<List<Module>>() {
            @Override
            public void onResponse(Call<List<Module>> call, Response<List<Module>> response) {
                List<Module> modules = response.body();
                Module moduleMusic = null;

                for (Module module : modules) {
                    Log.d(TAG, module.toString());
                    if (module.getId().equals("34")) {
                        moduleMusic = module;
                    }
                }

                if (moduleMusic != null) {
                    for (SubGenre subGenre : moduleMusic.getSubGenres()) {
                        ListGenre.add(Genre.create(subGenre.getId(), subGenre.getTranslationKey()));
                    }


                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Genre genre : ListGenre.getList()) {
                            DbContext.getInstance().insert(genre);
                        }
                        changeFragment(new StartFragment(), false);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Module>> call, Throwable t) {
                getDataFromRealm();
                toast("onFailure");
            }
        });

    }

    public void toast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void goToTopSongFragment(ChangeFragmentEvent changeFragmentEvent) {

        Bundle bundle = new Bundle();

        Fragment fragment = changeFragmentEvent.getFragment();

        bundle.putInt(ListGenre.POSITION, changeFragmentEvent.getPosition());

        fragment.setArguments(bundle);

        changeFragment(fragment, changeFragmentEvent.isAddToBackStack());
    }

    @Subscribe
    public void hideToolbar(HideToolbarEvent hideToolbarEvent) {

        boolean hide = hideToolbarEvent.isHide();

        if (hide) {
            toolbar.setVisibility(View.GONE);
            gap.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
            gap.setVisibility(View.VISIBLE);
        }
    }

}
