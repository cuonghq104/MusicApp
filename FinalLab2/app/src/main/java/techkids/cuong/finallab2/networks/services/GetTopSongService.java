package techkids.cuong.finallab2.networks.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import techkids.cuong.finallab2.networks.models.TopSongResponseBody;

/**
 * Created by Cuong on 1/10/2017.
 */
public interface GetTopSongService {

    @GET("us/rss/topsongs/limit=50/genre={genreId}/explicit=true/json")
    Call<TopSongResponseBody> call(@Path("genreId") String id);

}
