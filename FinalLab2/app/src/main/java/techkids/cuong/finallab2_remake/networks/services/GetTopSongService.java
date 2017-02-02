package techkids.cuong.finallab2_remake.networks.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import techkids.cuong.finallab2_remake.networks.jsonmodels.TopSongResponseBody;

/**
 * Created by Cuong on 1/31/2017.
 */

public interface GetTopSongService {

    @GET("topsongs/limit={limitation}/genre={genreId}/explicit=true/json")
    Call<TopSongResponseBody> getTopSong(@Path("limitation") String limit, @Path("genreId") String genreId);

}
