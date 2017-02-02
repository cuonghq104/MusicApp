package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/31/2017.
 */

public class SongImage {
    @SerializedName("label")
    private String url;

    public SongImage(String label) {
        this.url = label;
    }

    public String getUrl() {
        return url;
    }

}
