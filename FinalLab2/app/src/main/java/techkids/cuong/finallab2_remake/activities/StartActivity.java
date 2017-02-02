package techkids.cuong.finallab2_remake.activities;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import techkids.cuong.finallab2_remake.R;
import techkids.cuong.finallab2_remake.databases.context.DbContext;
import techkids.cuong.finallab2_remake.databases.realmmodels.GenreContext;
import techkids.cuong.finallab2_remake.databases.realmmodels.MusicGenre;
import techkids.cuong.finallab2_remake.events.ChangeActionBarMenuEvent;
import techkids.cuong.finallab2_remake.events.ChangeActionBarPlaylistEvent;
import techkids.cuong.finallab2_remake.events.CurrentSongEvent;
import techkids.cuong.finallab2_remake.events.MusicPauseEvent;
import techkids.cuong.finallab2_remake.events.MusicPlayingEvent;
import techkids.cuong.finallab2_remake.events.NextSongEvent;
import techkids.cuong.finallab2_remake.events.OnFavoriteClickEvent;
import techkids.cuong.finallab2_remake.events.PauseEvent;
import techkids.cuong.finallab2_remake.events.PlayMusicEvent;
import techkids.cuong.finallab2_remake.events.PreviousSongEvent;
import techkids.cuong.finallab2_remake.events.ReadyEvent;
import techkids.cuong.finallab2_remake.events.TopSongEvent;
import techkids.cuong.finallab2_remake.fragments.GenresListFragment;
import techkids.cuong.finallab2_remake.fragments.MainPlayerFragment;
import techkids.cuong.finallab2_remake.fragments.MenuFragment;
import techkids.cuong.finallab2_remake.fragments.PlaylistFragment;
import techkids.cuong.finallab2_remake.models.SongContext;
import techkids.cuong.finallab2_remake.networks.jsonmodels.Genre;
import techkids.cuong.finallab2_remake.networks.jsonmodels.SearchSongResponseBody;
import techkids.cuong.finallab2_remake.networks.jsonmodels.Song;
import techkids.cuong.finallab2_remake.networks.jsonmodels.Store;
import techkids.cuong.finallab2_remake.networks.jsonmodels.TopSongResponseBody;
import techkids.cuong.finallab2_remake.networks.services.GetGenresService;
import techkids.cuong.finallab2_remake.networks.services.GetTopSongService;
import techkids.cuong.finallab2_remake.networks.services.SearchSongService;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = StartActivity.class.toString();

    @BindView(R.id.pb)
    ProgressBar pb;

    @BindView(R.id.mini_player)
    LinearLayout miniPlayer;

    @BindView(R.id.sb_player)
    SeekBar sbPlayer;

    @BindView(R.id.iv_song_image)
    CircleImageView ivSongImage;

    @BindView(R.id.tv_song_name)
    TextView tvSongName;

    @BindView(R.id.tv_song_artist)
    TextView tvSongArtist;

    private MediaPlayer mediaPlayer;

    private ActionBarDrawerToggle toggle;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.bind(this);
        setupUI();
        getDataFromRealm();
        addListener();
        handler.postDelayed(updateSeekBar, 100);

    }

    private void setupUI() {
        sbPlayer.setPadding(0, 0, 0, 0);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
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

        RealmResults<MusicGenre> genres = DbContext.getInstances().getAllGenres();

        Toast.makeText(StartActivity.this, String.format("%d", genres.size()), Toast.LENGTH_SHORT).show();

        if (genres.size() == 0) {

            getData();
            return;
        }

        Toast.makeText(StartActivity.this, String.format("Thời thanh xuân sẽ qua"), Toast.LENGTH_SHORT).show();

        for (MusicGenre genre : genres) {

            GenreContext.genres.add(genre);

        }

        pb.setVisibility(View.GONE);

        changeFragment(new MenuFragment(), false);

    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rss.itunes.apple.com/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetGenresService services = retrofit.create(GetGenresService.class);

        Call<List<Store>> listCall = services.getAllGenres();

        listCall.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                for (Store store : response.body()) {

                    Log.d(TAG, store.getId());

                    if (store.getId().equals("34")) {

                        for (Genre genre : store.getGenres()) {

                            Toast.makeText(StartActivity.this, String.format("%s %s", genre.getId(), genre.getName()), Toast.LENGTH_SHORT).show();

                            DbContext.getInstances().insert(MusicGenre.create(genre.getId(), genre.getName()));
                        }

                        EventBus.getDefault().post(new ReadyEvent(new GenresListFragment(), false));

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {

                Log.d(TAG, "onFailure");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Subscribe
    public void onReadyEvent(ReadyEvent readyEvent) {

        Toast.makeText(StartActivity.this, "ready", Toast.LENGTH_SHORT).show();

        pb.setVisibility(View.GONE);

        changeFragment(readyEvent.getFragment(), readyEvent.isAddToBackStack());

    }

    @Subscribe
    public void onTopSongEvent(final TopSongEvent event) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/us/rss/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetTopSongService service = retrofit.create(GetTopSongService.class);

        Call<TopSongResponseBody> call = service.getTopSong("50", event.getId());

        call.enqueue(new Callback<TopSongResponseBody>() {
            @Override
            public void onResponse(Call<TopSongResponseBody> call, Response<TopSongResponseBody> response) {
                SongContext.songs.clear();
                for (Song song : response.body().getContainer().getSongs()) {
                    SongContext.songs.add(song);
                }
                PlaylistFragment playlistFragment = new PlaylistFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(GenreContext.POSITION, event.getPosition());
                playlistFragment.setArguments(bundle);
                EventBus.getDefault().post(new ReadyEvent(playlistFragment, true));
            }

            @Override
            public void onFailure(Call<TopSongResponseBody> call, Throwable t) {

            }
        });
    }
    public void changeFragment(Fragment fragment, boolean addToBackStack) {

        if (addToBackStack)
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.fl_container, fragment)
                    .addToBackStack(null)
                    .commit();
        else
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();

    }

    private ImageButton btFavorite;

    private boolean isFavorite;

    @Subscribe
    public void onChangeActionBarEvent(ChangeActionBarPlaylistEvent event) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.action_bar_playlist, null);
        btFavorite = (ImageButton) view.findViewById(R.id.bt_favorite);
        if (!event.isFavorite()) {
            isFavorite = false;
            btFavorite.setBackground(getDrawable(R.drawable.unfavorite));
        } else {
            isFavorite = true;
        }
        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);
        btFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = !isFavorite;
                if (!isFavorite) {
                    btFavorite.setBackground(getDrawable(R.drawable.unfavorite));
                } else {
                    btFavorite.setBackground(getDrawable(R.drawable.favorite));
                }
                EventBus.getDefault().post(new OnFavoriteClickEvent());
            }
        });
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.back);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new MenuFragment(), false);
            }
        });

    }

    private Song song;

    public void playMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        this.song = SongContext.songs.get(songPosition);

        tvSongArtist.setText(song.getArtist().getLabel());

        tvSongName.setText(song.getName().getName());

//
        Picasso.with(this).load(song.getSongImages()[0].getUrl()).into(ivSongImage);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.mp3.zing.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchSongService searchSongService = retrofit.create(SearchSongService.class);


        String requestData = "{";

        requestData += String.format("\"q\":\"%s %s\"}", song.getName().getName(), song.getArtist().getLabel());


        Call<SearchSongResponseBody> call = searchSongService.request(requestData);

        call.enqueue(new Callback<SearchSongResponseBody>() {
            @Override
            public void onResponse(Call<SearchSongResponseBody> call, Response<SearchSongResponseBody> response) {

//                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                String url = response.body().getDocses()[0].getSource().getLink(); // your URL here

                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();

            }

            @Override
            public void onFailure(Call<SearchSongResponseBody> call, Throwable t) {

            }
        });
    }

    private int songPosition;

    @Subscribe
    public void onMusicPlayEvent(PlayMusicEvent event) {

        songPosition = event.getPosition();

        miniPlayer.setVisibility(View.VISIBLE);

        playMusic();

    }

    @Subscribe
    public void onChangeActionBarEvent(ChangeActionBarMenuEvent event) {
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.action_bar_menu, null);
        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    private Handler handler = new Handler();

    private Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            sbPlayer.setMax(mediaPlayer.getDuration());

            int current = mediaPlayer.getCurrentPosition();

            sbPlayer.setProgress(current);

            EventBus.getDefault().post(new MusicPlayingEvent(current));

            handler.postDelayed(this, 100);

            Log.d(TAG, String.format("%d - %d", current, mediaPlayer.getDuration()));
        }
    };

    private void addListener() {
        sbPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(updateSeekBar);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(seekBar.getProgress());
                mediaPlayer.start();
                handler.postDelayed(updateSeekBar, 100);
            }
        });
    }

    private TextView tvSName;

    private TextView tvSArtist;

    @OnClick(R.id.mini_player)
    public void goToMainPlayer() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.action_bar_main_player, null);

        tvSName = (TextView) view.findViewById(R.id.tv_song_name);
        tvSArtist = (TextView) view.findViewById(R.id.tv_song_artist);

        tvSName.setText(song.getName().getName());
        tvSArtist.setText(song.getArtist().getLabel());


        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);

        YoYo.with(Techniques.BounceInUp)
                .duration(200)
                .playOn(findViewById(R.id.fl_container));

        changeFragment(new MainPlayerFragment(), true);

        miniPlayer.setVisibility(View.GONE);
        EventBus.getDefault().postSticky(new CurrentSongEvent(song, mediaPlayer.getDuration()));

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_close);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                YoYo.with(Techniques.BounceInDown)
                        .duration(200)
                        .playOn(findViewById(R.id.fl_container));
                miniPlayer.setVisibility(View.VISIBLE);
            }
        });

    }

    @BindView(R.id.bt_play)
    CircleButton btPlay;

    private int currentPosition;

    @OnClick(R.id.bt_play)
    public void pausePlayMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            currentPosition = mediaPlayer.getCurrentPosition();
            btPlay.setImageResource(R.drawable.ic_pause);
        } else {
            mediaPlayer.seekTo(currentPosition);
            mediaPlayer.start();
            btPlay.setImageResource(R.drawable.play);
        }
    }

    @Subscribe
    public void onPauseEvent(PauseEvent event) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            currentPosition = mediaPlayer.getCurrentPosition();
            btPlay.setImageResource(R.drawable.ic_pause);
        } else {
            mediaPlayer.seekTo(currentPosition);
            mediaPlayer.start();
            btPlay.setImageResource(R.drawable.play);
        }
        EventBus.getDefault().post(new MusicPauseEvent(mediaPlayer.isPlaying()));
    }

    @Subscribe
    public void onPreviousSongEvent(PreviousSongEvent event) {

        if (songPosition == 50) {
            Toast.makeText(StartActivity.this, "No more song", Toast.LENGTH_SHORT).show();
            return;
        }

        songPosition--;
        this.song = SongContext.songs.get(songPosition);
        playMusic();
        EventBus.getDefault().postSticky(new CurrentSongEvent(song, mediaPlayer.getDuration()));
        tvSName.setText(song.getName().getName());
        tvSArtist.setText(song.getArtist().getLabel());

    }

    @Subscribe
    public void onNextSongEvent(NextSongEvent event) {

        if (songPosition == 0) {
            Toast.makeText(StartActivity.this, "No more song", Toast.LENGTH_SHORT).show();
            return;
        }

        songPosition++;
        this.song = SongContext.songs.get(songPosition);
        playMusic();
        EventBus.getDefault().postSticky(new CurrentSongEvent(song, mediaPlayer.getDuration()));
        tvSName.setText(song.getName().getName());
        tvSArtist.setText(song.getArtist().getLabel());

    }
}
