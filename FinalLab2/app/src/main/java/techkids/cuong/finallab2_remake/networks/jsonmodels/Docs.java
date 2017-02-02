package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/15/2017.
 */
public class Docs {

    @SerializedName("song_id")
    private int songId;

    @SerializedName("title")
    private String title;

    @SerializedName("artist")
    private String artist;

    @SerializedName("source")
    private MusicSource source;

    public Docs(int songId, String title, String artist, MusicSource source) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.source = source;
    }

    public int getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public MusicSource getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Docs{" +
                "songId=" + songId +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", source=" + source +
                '}';
    }
}
