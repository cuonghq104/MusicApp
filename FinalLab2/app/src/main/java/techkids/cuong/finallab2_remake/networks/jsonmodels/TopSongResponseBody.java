package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/31/2017.
 */

public class TopSongResponseBody {

   @SerializedName("feed")
   private TopSongContainer container;

    public TopSongResponseBody(TopSongContainer container) {
        this.container = container;
    }

    public TopSongContainer getContainer() {
        return container;
    }
}
