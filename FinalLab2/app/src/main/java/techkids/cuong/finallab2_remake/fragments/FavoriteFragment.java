package techkids.cuong.finallab2_remake.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import techkids.cuong.finallab2_remake.R;
import techkids.cuong.finallab2_remake.databases.context.DbContext;
import techkids.cuong.finallab2_remake.databases.realmmodels.GenreContext;
import techkids.cuong.finallab2_remake.databases.realmmodels.MusicGenre;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    @BindView(R.id.lv_favorite)
    ListView lvFavorite;

    List<String> favorite;

    ArrayAdapter<String> adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        RealmResults<MusicGenre> genres = DbContext.getInstances().getFavoriteGenres();
        for (MusicGenre genre : genres) {
            GenreContext.genresFavorite.add(genre);
        }

        favorite = new ArrayList<>();
        for (MusicGenre genre : GenreContext.genresFavorite) {
            favorite.add(genre.getName().toUpperCase());
        }
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, favorite);
        lvFavorite.setAdapter(adapter);
    }

}
