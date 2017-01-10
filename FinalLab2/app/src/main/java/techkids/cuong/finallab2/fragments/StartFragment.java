package techkids.cuong.finallab2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.finallab2.R;
import techkids.cuong.finallab2.adapters.MusicPageAdapter;
import techkids.cuong.finallab2.events.HideToolbarEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    @BindView(R.id.vp_music)
    ViewPager vpMusic;

    private MusicPageAdapter adapter;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new HideToolbarEvent(false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        adapter = new MusicPageAdapter(getActivity().getSupportFragmentManager());
        vpMusic.setAdapter(adapter);
    }

}
