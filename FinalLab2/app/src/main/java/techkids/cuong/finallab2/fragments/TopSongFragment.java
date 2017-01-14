package techkids.cuong.finallab2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import techkids.cuong.finallab2.R;
import techkids.cuong.finallab2.adapters.TopSongAdapter;
import techkids.cuong.finallab2.databases.DbContext;
import techkids.cuong.finallab2.events.ChangeFragmentEvent;
import techkids.cuong.finallab2.events.HideToolbarEvent;
import techkids.cuong.finallab2.models.Genre;
import techkids.cuong.finallab2.models.ListGenre;
import techkids.cuong.finallab2.models.SongList;
import techkids.cuong.finallab2.networks.models.Song;
import techkids.cuong.finallab2.networks.models.SongName;
import techkids.cuong.finallab2.networks.models.TopSongResponseBody;
import techkids.cuong.finallab2.networks.services.GetTopSongService;
import techkids.cuong.finallab2.viewcompounds.HalfScreenImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {

    @BindView(R.id.bt_back)
    ImageButton btBack;

    @BindView(R.id.bt_favorite)
    ImageButton btFavorite;

    @BindView(R.id.bt_search)
    ImageButton btSearch;

    @BindView(R.id.bt_share)
    ImageButton btShare;

    @BindView(R.id.iv_genre_ts)
    HalfScreenImageView ivGenre;

    @BindView(R.id.tv_genre)
    TextView tvGenre;

    @BindView(R.id.tv_number_of_songs)
    TextView tvNOS;

    @BindView(R.id.rv_top_songs)
    RecyclerView rvTopSongs;

    private TopSongAdapter adapter;

    private int position;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new HideToolbarEvent(true));

    }


    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);
        ButterKnife.bind(this, view);
        getData();
        getTopSong();
        return view;
    }

    private void getTopSong() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetTopSongService getTopSongService = retrofit.create(GetTopSongService.class);

        Call<TopSongResponseBody> call = getTopSongService.call(ListGenre.getList().get(position).getId());

        call.enqueue(new Callback<TopSongResponseBody>() {
            @Override
            public void onResponse(Call<TopSongResponseBody> call, Response<TopSongResponseBody> response) {



                SongList.list = Arrays.asList(response.body().getFeed().getSongs());

                setupUI();
            }

            @Override
            public void onFailure(Call<TopSongResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupUI() {
        adapter = new TopSongAdapter();
        rvTopSongs.setAdapter(adapter);
        rvTopSongs.setLayoutManager(new LinearLayoutManager(getContext()));
        tvNOS.setText(String.format("%d songs", SongList.list.size()));
    }

    private void getData() {
        Bundle bundle = getArguments();
        position = bundle.getInt(ListGenre.POSITION);
        Genre genre = ListGenre.getList().get(position);

        if (genre.isFavorite()) {
            btFavorite.setImageResource(R.drawable.favorite);
        } else {
            btFavorite.setImageResource(R.drawable.unfavorite);
        }

        String uri = String.format("genre_2x_%s", genre.getId());

        int imageResource = getContext().getResources().getIdentifier(uri, "drawable", getContext().getPackageName());

        ivGenre.setImageResource(imageResource);

        tvGenre.setText(genre.getName().toUpperCase());

    }

    @OnClick(R.id.bt_back)
    public void backToGenreFragment() {
        EventBus.getDefault().post(new ChangeFragmentEvent(new StartFragment(), false, position));
    }

    @OnClick(R.id.bt_favorite)
    public void onFavoriteClick() {
        Genre genre = ListGenre.getList().get(position);
        DbContext.getInstance().changeFavorite(genre);
        if (genre.isFavorite()) {
            btFavorite.setImageResource(R.drawable.favorite);
        } else {
            btFavorite.setImageResource(R.drawable.unfavorite);
        }
    }
}
