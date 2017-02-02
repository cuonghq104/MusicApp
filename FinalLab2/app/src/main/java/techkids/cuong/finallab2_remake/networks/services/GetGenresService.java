package techkids.cuong.finallab2_remake.networks.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import techkids.cuong.finallab2_remake.networks.jsonmodels.Store;

/**
 * Created by Cuong on 1/30/2017.
 */

public interface GetGenresService {

    @GET("media-types.json")
    Call<List<Store>> getAllGenres();

}
