package techkids.cuong.finallab2_remake.events;

import techkids.cuong.finallab2_remake.networks.jsonmodels.Song;

/**
 * Created by Cuong on 2/2/2017.
 */
public class CurrentSongEvent {
    private Song song;

    private int duration;

    public CurrentSongEvent(Song song, int duration) {
        this.song = song;
        this.duration = duration;
    }

    public Song getSong() {
        return song;
    }

    public int getDuration() {
        return duration;
    }
}
