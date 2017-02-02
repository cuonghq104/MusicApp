package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/31/2017.
 */

public class SongName {

    @SerializedName("label")
    private String name;

    public SongName(String label) {
        this.name = label;
    }

    public String getName() {
        return name;
    }
}
