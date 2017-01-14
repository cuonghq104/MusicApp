package techkids.cuong.finallab2.events;

/**
 * Created by Cuong on 1/10/2017.
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
