package techkids.cuong.finallab2_remake.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.finallab2_remake.R;
import techkids.cuong.finallab2_remake.adapters.TopSongAdapter;
import techkids.cuong.finallab2_remake.databases.context.DbContext;
import techkids.cuong.finallab2_remake.databases.realmmodels.GenreContext;
import techkids.cuong.finallab2_remake.databases.realmmodels.MusicGenre;
import techkids.cuong.finallab2_remake.events.ChangeActionBarPlaylistEvent;
import techkids.cuong.finallab2_remake.events.OnFavoriteClickEvent;
import techkids.cuong.finallab2_remake.models.SongContext;
import techkids.cuong.finallab2_remake.viewcompounds.HalfScreenImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {

//    @BindView(R.id.bt_back)
//    ImageButton btBack;

//    @BindView(R.id.bt_favorite)
//    ImageButton btFavorite;
//
//    @BindView(R.id.bt_search)
//    ImageButton btSearch;

//    @BindView(R.id.bt_share)
//    ImageButton btShare;

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
    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);
        this.setHasOptionsMenu(true);
        getData();
        setupUI();
        EventBus.getDefault().post(new ChangeActionBarPlaylistEvent(GenreContext.genres.get(position).isFavorite()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void setupUI() {
        adapter = new TopSongAdapter();
        rvTopSongs.setAdapter(adapter);
        rvTopSongs.setLayoutManager(new LinearLayoutManager(getContext()));
        tvNOS.setText(String.format("%d songs", SongContext.songs.size()));
    }

    private void getData() {
        Bundle bundle = getArguments();
        position = bundle.getInt(GenreContext.POSITION);
        MusicGenre genre = GenreContext.genres.get(position);

//        if (genre.isFavorite()) {
//            btFavorite.setImageResource(R.drawable.favorite);
//        } else {
//            btFavorite.setImageResource(R.drawable.unfavorite);
//        }

        String uri = String.format("genre_2x_%s", genre.getId());

        int imageResource = getContext().getResources().getIdentifier(uri, "drawable", getContext().getPackageName());

        ivGenre.setImageResource(imageResource);

        tvGenre.setText(genre.getName().toUpperCase());

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_playlist, menu);
//    }

    @Subscribe
    public void onFavoriteClick(OnFavoriteClickEvent event) {

        Toast.makeText(ivGenre.getContext(), String.format("%s", GenreContext.genres.get(position).isFavorite()), Toast.LENGTH_SHORT).show();

        DbContext.getInstances().changeFavorite(GenreContext.genres.get(position));
    }
}
