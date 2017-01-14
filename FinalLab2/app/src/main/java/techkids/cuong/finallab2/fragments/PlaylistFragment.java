package techkids.cuong.finallab2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.finallab2.R;
import techkids.cuong.finallab2.models.Genre;
import techkids.cuong.finallab2.models.ListGenre;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {


    @BindView(R.id.lv_favorite)
    ListView lvFavorite;

    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);
        setpUI();
        return view;
    }

    private void setpUI() {
        ArrayList<String> favorite = new ArrayList<>();
        for (Genre genre : ListGenre.getList()) {
            if (genre.isFavorite()) {
                favorite.add(genre.getName());
            }
        }
        Toast.makeText(getContext(), String.format("%d", favorite.size()), Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, favorite);

        lvFavorite.setAdapter(adapter);
    }

}
