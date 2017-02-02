package techkids.cuong.finallab2_remake.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Cuong on 1/15/2017.
 */
public class SearchSongResponseBody {

    private int numFound;

    @SerializedName("docs")
    private Docs[] docses;

    public SearchSongResponseBody(Docs[] docses) {
        this.docses = docses;
    }

    public SearchSongResponseBody(int numFound, Docs[] docses) {
        this.numFound = numFound;
        this.docses = docses;
    }

    public Docs[] getDocses() {
        return docses;
    }

    @Override
    public String toString() {
        return "SearchSongResponseBody{" +
                "numFound=" + numFound +
                ", docses=" + Arrays.toString(docses) +
                '}';
    }
}
