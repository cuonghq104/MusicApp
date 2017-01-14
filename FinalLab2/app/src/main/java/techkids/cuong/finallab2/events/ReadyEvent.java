package techkids.cuong.finallab2.events;

/**
 * Created by Cuong on 1/10/2017.
 */
public class ReadyEvent {

    private boolean ready;

    public ReadyEvent(boolean ready) {
        this.ready = ready;
    }

    public boolean isReady() {
        return ready;
    }
}
