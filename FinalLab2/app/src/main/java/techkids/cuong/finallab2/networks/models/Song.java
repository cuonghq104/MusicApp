package techkids.cuong.finallab2.networks.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cuong on 1/10/2017.
 */



public class Song {

    @SerializedName("im:name")
    SongName songName;

    @SerializedName("im:artist")
    SongArtist songArtist;

    @SerializedName("im:image")
    SongImage[] songImage;

    public Song(SongName songName, SongArtist songArtist, SongImage[] songImage) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.songImage = songImage;
    }

    public SongName getSongName() {
        return songName;
    }

    public SongArtist getSongArtist() {
        return songArtist;
    }

    public SongImage[] getSongImage() {
        return songImage;
    }


    @Override
    public String toString() {
        return "Song{" +
                "songName=" + songName +
                ", songArtist=" + songArtist +
                ", songImage=" + Arrays.toString(songImage) +
                '}';
    }
}
