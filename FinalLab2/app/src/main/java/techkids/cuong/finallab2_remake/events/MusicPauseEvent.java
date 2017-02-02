package techkids.cuong.finallab2_remake.events;

/**
 * Created by Cuong on 2/2/2017.
 */

public class MusicPauseEvent {

    private boolean pause;

    public MusicPauseEvent(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }
}
