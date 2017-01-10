package techkids.cuong.finallab2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import techkids.cuong.finallab2.fragments.GenreListFragment;
import techkids.cuong.finallab2.fragments.OfflineFragment;
import techkids.cuong.finallab2.fragments.PlaylistFragment;

/**
 * Created by Cuong on 1/9/2017.
 */
public class MusicPageAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;

    public MusicPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
                return new GenreListFragment();
            case 1:
                return new PlaylistFragment();
            case 2:
                return new OfflineFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
                return "GENRES";
            case 1:
                return "PLAYLIST";
            case 2:
                return "OFFLINE";
        }
    }
}
