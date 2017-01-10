package techkids.cuong.finallab2.networks.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Cuong on 1/8/2017.
 */
public class Module {

    @SerializedName("id")
    private String id;

    @SerializedName("store")
    private String store;

    @SerializedName("translation_key")
    private String translationKey;

    @SerializedName("subgenres")
    private SubGenre[] subGenres;

    public Module(String id, String store, String translationKey, SubGenre[] subGenres) {
        this.id = id;
        this.store = store;
        this.translationKey = translationKey;
        this.subGenres = subGenres;
    }

    public String getId() {
        return id;
    }

    public String getStore() {
        return store;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public SubGenre[] getSubGenres() {
        return subGenres;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", store='" + store + '\'' +
                ", translationKey='" + translationKey + '\'' +
                ", subGenres=" + Arrays.toString(subGenres) +
                '}';
    }
}
