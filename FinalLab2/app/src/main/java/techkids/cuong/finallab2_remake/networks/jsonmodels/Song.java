package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/31/2017.
 */

public class Song {

    @SerializedName("im:name")
    private SongName name;

    @SerializedName("im:image")
    private SongImage[] songImages;

    @SerializedName("im:artist")
    private Artist artist;

    public Song(SongName name, SongImage[] songImages, Artist artist) {
        this.name = name;
        this.songImages = songImages;
        this.artist = artist;
    }

    public SongName getName() {
        return name;
    }

    public SongImage[] getSongImages() {
        return songImages;
    }

    public Artist getArtist() {
        return artist;
    }
}
