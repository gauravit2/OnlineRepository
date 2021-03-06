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
 * This executes network call for never ending list api
 */
public class NeverEndingListInteractor extends ApiInteractor {

    /**
     * Configuration for request
     */
    ApiConfig config;
    /**
     * Call instance of retrofit for execute request
     */
    Call<ResponseBody> call;

    /**
     * initialize config data
     * @param config data of api requires at the time of request
     */
    public NeverEndingListInteractor(ApiConfig config) {
        this.config = (ApiConfig) config;
    }

    /**
     * Load Retrofit Instance with limit of 20 records
     * Execute network call and return response
     */
    @Override
    public void execute() {

        ApiConfigInterface syncInstance = RetrofitInstance.getRetrofitInstance().create(ApiConfigInterface.class);
        call = syncInstance.requestNeverEndingListApi(config.limit);

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
