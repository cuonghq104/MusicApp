package techkids.cuong.finallab2_remake.events;

/**
 * Created by Cuong on 2/1/2017.
 */
public class ChangeActionBarPlaylistEvent {

    boolean favorite;

    public ChangeActionBarPlaylistEvent(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
