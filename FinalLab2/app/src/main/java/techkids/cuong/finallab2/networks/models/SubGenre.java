package techkids.cuong.finallab2.networks.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/8/2017.
 */
public class SubGenre {

    @SerializedName("id")
    private String id;

    @SerializedName("translation_key")
    private String translationKey;

    public SubGenre(String id, String translationKey) {
        this.id = id;
        this.translationKey = translationKey;
    }

    public String getId() {
        return id;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    @Override
    public String toString() {
        return "SubGenre{" +
                "id=" + id +
                ", translationKey='" + translationKey + '\'' +
                '}';
    }
}
