package techkids.cuong.finallab2.networks.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Cuong on 1/10/2017.
 */
public class Feed {
    @SerializedName("entry")
    private Song[] songs;

    public Feed(Song[] songs) {
        this.songs = songs;
    }

    public Song[] getSongs() {
        return songs;
    }

    @Override
    public String toString() {
        return "TopSongResponseBody{" +
                "songs=" + Arrays.toString(songs) +
                '}';
    }
}
