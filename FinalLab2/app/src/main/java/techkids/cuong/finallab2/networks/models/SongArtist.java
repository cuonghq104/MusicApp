package techkids.cuong.finallab2.networks.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/10/2017.
 */
public class SongArtist {

    private String label;

    public SongArtist(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "SongArtist{" +
                "label='" + label + '\'' +
                '}';
    }
}
