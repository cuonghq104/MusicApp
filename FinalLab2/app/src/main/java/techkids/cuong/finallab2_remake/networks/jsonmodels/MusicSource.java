package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/15/2017.
 */
public class MusicSource {

    @SerializedName("128")
    private String link;

    public MusicSource(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "MusicSource{" +
                "link='" + link + '\'' +
                '}';
    }
}
