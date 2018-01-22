package jokes.test.com.onlineassignment.apiservice.config;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gaurav on 1/17/2018.
 * Methods which used at load data with api context api path , parameters
 */
public interface ApiConfigInterface {


    /**
     * Request for random joke api with POST request
     * @return response
     */
    @POST("/jokes/random")
    Call<ResponseBody> requestRandomJokeApi();

    /**
     * Request for Change the Name api with GET request
     * @param firstName : firstname as parameter
     * @param lastName : lastname as parameter
     * @return respose
     */
    @GET("/jokes/random")
    Call<ResponseBody> requestChangingTheLastNameApi(@Query("firstName") String firstName , @Query("lastName") String lastName);


    /**
     * Request for load never ending list with POST request
     * @param limit number of items to be load
     * @return response
     */
    @POST("/jokes/random/{limit}")
    Call<ResponseBody> requestNeverEndingListApi(@Path("limit") int limit);
}
