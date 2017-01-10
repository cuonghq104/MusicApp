package techkids.cuong.finallab2.networks.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Cuong on 1/10/2017.
 */
public class TopSongResponseBody {

    Feed feed;

    public TopSongResponseBody(Feed feed) {
        this.feed = feed;
    }

    public Feed getFeed() {
        return feed;
    }

    @Override
    public String toString() {
        return "TopSongResponseBody{" +
                "feed=" + feed +
                '}';
    }
}
