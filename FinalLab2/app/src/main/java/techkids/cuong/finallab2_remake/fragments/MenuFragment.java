package techkids.cuong.finallab2_remake.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.cuong.finallab2_remake.R;
import techkids.cuong.finallab2_remake.adapters.MenuPagerAdapter;
import techkids.cuong.finallab2_remake.events.ChangeActionBarMenuEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    @BindView(R.id.vp_music)
    ViewPager vpMusic;

    @BindView(R.id.tl_music)
    TabLayout tlMusic;

    private MenuPagerAdapter adapter;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_menu, container, false);
        EventBus.getDefault().post(new ChangeActionBarMenuEvent());
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        adapter = new MenuPagerAdapter(getActivity().getSupportFragmentManager());
        vpMusic.setAdapter(adapter);
    }

}
