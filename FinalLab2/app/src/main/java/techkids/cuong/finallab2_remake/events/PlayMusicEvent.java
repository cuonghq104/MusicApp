package techkids.cuong.finallab2_remake.events;

/**
 * Created by Cuong on 2/2/2017.
 */
public class PlayMusicEvent {

    private boolean playMusic;

    private int position;

    public PlayMusicEvent(boolean playMusic, int position) {
        this.playMusic = playMusic;
        this.position = position;
    }

    public boolean isPlayMusic() {
        return playMusic;
    }

    public int getPosition() {
        return position;
    }
}
