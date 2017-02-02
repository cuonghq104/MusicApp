package techkids.cuong.finallab2_remake.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import techkids.cuong.finallab2_remake.fragments.FavoriteFragment;
import techkids.cuong.finallab2_remake.fragments.GenresListFragment;
import techkids.cuong.finallab2_remake.fragments.OfflineFragment;

/**
 * Created by Cuong on 1/31/2017.
 */

public class MenuPagerAdapter extends FragmentStatePagerAdapter {

    public MenuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GenresListFragment();
            case 1:
                return new FavoriteFragment();
            case 2:
                return new OfflineFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "GENRES";
            case 1:
                return "PLAYLIST";
            case 2:
                return "OFFLINE";
        }
        return null;
    }
}
