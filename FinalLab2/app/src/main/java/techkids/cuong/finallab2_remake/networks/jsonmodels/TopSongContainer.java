package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 2/1/2017.
 */

public class TopSongContainer {
    @SerializedName("entry")
    private Song[] songs;

    public TopSongContainer(Song[] songs) {
        this.songs = songs;
    }

    public Song[] getSongs() {
        return songs;
    }
}
