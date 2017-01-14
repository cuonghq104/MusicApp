package techkids.cuong.finallab2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.finallab2.R;
import techkids.cuong.finallab2.adapters.GenreAdapter;
import techkids.cuong.finallab2.events.HideToolbarEvent;
import techkids.cuong.finallab2.models.ListGenre;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenreListFragment extends Fragment {


    @BindView(R.id.rv_genres)
    RecyclerView rvGenres;

    private GenreAdapter adapter;

    public GenreListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        setupUI();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_genre_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    private void setupUI() {
        adapter = new GenreAdapter();
        rvGenres.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int mod = position % 3;
                if(position == 0)
                    return 2;
                else if(position < 3)
                    return 1;
                else if(mod == 0)
                    return 2;
                else
                    return 1;
            }
        });
        rvGenres.setLayoutManager(layoutManager);

    }

}
