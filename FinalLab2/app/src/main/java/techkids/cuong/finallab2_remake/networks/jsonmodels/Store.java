package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cuong on 1/30/2017.
 */

public class Store {

    @SerializedName("id")
    private String id;

    @SerializedName("subgenres")
    private Genre[] genres;

    public Store(String id, Genre[] genres) {
        this.id = id;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public Genre[] getGenres() {
        return genres;
    }
}
