package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/31/2017.
 */

public class Artist {

    @SerializedName("label")
    private String label;

    public Artist(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
