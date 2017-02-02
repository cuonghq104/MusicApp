package techkids.cuong.finallab2_remake.events;

/**
 * Created by Cuong on 2/2/2017.
 */
public class MusicPlayingEvent {
    private int current;

    public MusicPlayingEvent(int current) {
        this.current = current;
    }

    public int getCurrent() {
        return current;
    }
}
