package techkids.cuong.finallab2.networks.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import techkids.cuong.finallab2.networks.models.Module;

/**
 * Created by Cuong on 1/8/2017.
 */
public interface GetGenreService {

    @GET("media-types.json")
    Call<List<Module>> getAllGenres();

}
