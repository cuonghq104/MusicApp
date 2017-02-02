package techkids.cuong.finallab2_remake.networks.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import techkids.cuong.finallab2_remake.networks.jsonmodels.SearchSongResponseBody;

/**
 * Created by Cuong on 1/15/2017.
 */
public interface SearchSongService {

    @GET("api/mobile/search/song")
    Call<SearchSongResponseBody> request(
            @Query("requestdata") String requestData
    );

}
