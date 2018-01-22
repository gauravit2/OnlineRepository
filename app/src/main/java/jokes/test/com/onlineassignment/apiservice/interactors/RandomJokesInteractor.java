package jokes.test.com.onlineassignment.apiservice.interactors;

import java.io.IOException;

import jokes.test.com.onlineassignment.apiservice.builders.param.ApiConfig;
import jokes.test.com.onlineassignment.apiservice.config.ApiConfigInterface;
import jokes.test.com.onlineassignment.apiservice.config.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gaurav on 1/17/2018.
 */
public class RandomJokesInteractor extends ApiInteractor {

    /**
     * Configuration for request
     */
    ApiConfig config;
    /**
     * Call instance of retrofit for execute request
     */
    Call<ResponseBody> call;

    /**
     * initialize api config data
     * @param config api data
     */
    public RandomJokesInteractor(ApiConfig config) {
        this.config = (ApiConfig) config;
    }

    /**
     * Load Retrofit Instance with random joke api configuration
     * Execute network call and return response
     */
    @Override
    public void execute() {

        ApiConfigInterface syncInstance = RetrofitInstance.getRetrofitInstance().create(ApiConfigInterface.class);
        call = syncInstance.requestRandomJokeApi();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    try {
                        onSuccess(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        onError(null);
                    }


                } else {
                    onError(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onError(null);
            }
        });


    }

    /*cancel existing request*/
    @Override
    public void cancelRequest() {

        try {
            if (call != null && call.isExecuted()) {
                call.cancel();
                callback.onError(null);
            }
        } catch (Exception e) {
            callback.onError(null);
        }

    }
}
