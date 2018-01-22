package jokes.test.com.onlineassignment.apiservice.interactors;


import jokes.test.com.onlineassignment.apiservice.builders.param.ApiConfig;
import jokes.test.com.onlineassignment.apiservice.response_pojo.BaseResponse;
import jokes.test.com.onlineassignment.apiservice.util.ApiCallback;

/**
 * Created by Gaurav on 1/17/2018
 * This class uses for initialize api callback , Check success or error
 */
public abstract class ApiInteractor {

    /* callback instace*/
    public ApiCallback callback;
    /* ApiConfig instance*/
    ApiConfig config;

    /**
     * get callback instance for handling response
     * @return apicallback instance
     */
    public ApiCallback getCallback() {
        return callback;
    }

    /* Method to be excuted in future*/
    public abstract void execute();

    /* Method to be canelled in future*/
    public abstract void cancelRequest();


    /**
     * Initialize Callback
     * @param callback : api callback instance
     */
    public void setCallback(ApiCallback callback) {
        this.callback = callback;
    }

    /**
     * Initialize ApiConfig Instance
     * @param config : ApiConfig instance
     */
    public void setApiConfig(ApiConfig config) {
        this.config = config;
    }

    /**
     * Invoke on success message
     * @param object input object
     */
    public void onSuccess(Object object){
        callback.onSuccess(object);
    }

    /**
     * Invoke on error message
     * @param object error object
     */
    public void onError(Object object){
        callback.onError(object);
    }

    /**
     * Check respose contains success or not
     * @param response : Json response of type BaseResponse
     * @return true if response contains success else false
     */
    public boolean isRequestSuccess(retrofit2.Response<BaseResponse> response){

        boolean isSuccess = false;
        if (response.isSuccessful()) {
            BaseResponse res = response.body();
            String header = res.getType();
            if (header.equalsIgnoreCase("success")) {
               isSuccess = true;
            }
        }
        return isSuccess;
    }
}
