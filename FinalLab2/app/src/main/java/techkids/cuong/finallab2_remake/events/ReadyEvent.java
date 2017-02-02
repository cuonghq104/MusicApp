package techkids.cuong.finallab2_remake.events;

import android.support.v4.app.Fragment;

/**
 * Created by Cuong on 1/31/2017.
 */
public class ReadyEvent {

    Fragment fragment;

    boolean addToBackStack;

    public ReadyEvent(Fragment fragment, boolean addToBackStack) {
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }
}
