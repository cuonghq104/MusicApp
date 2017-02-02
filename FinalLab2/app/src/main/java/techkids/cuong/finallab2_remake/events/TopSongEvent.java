package techkids.cuong.finallab2_remake.events;

/**
 * Created by Cuong on 1/31/2017.
 */

public class TopSongEvent {

    private int position;

    private String id;

    public TopSongEvent(int position, String id) {
        this.position = position;
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }
}
