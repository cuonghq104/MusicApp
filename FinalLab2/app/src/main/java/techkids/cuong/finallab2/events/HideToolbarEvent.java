package techkids.cuong.finallab2.events;

/**
 * Created by Cuong on 1/10/2017.
 */
public class HideToolbarEvent {

    private boolean hide;

    public HideToolbarEvent(boolean hide) {
        this.hide = hide;
    }

    public boolean isHide() {
        return hide;
    }
}
