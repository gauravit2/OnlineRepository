package jokes.test.com.onlineassignment.presenter;

import android.os.Build;
import android.text.Html;

import com.google.gson.Gson;

import jokes.test.com.onlineassignment.apiservice.builders.param.ApiConfig;
import jokes.test.com.onlineassignment.apiservice.interactors.ApiInteractor;
import jokes.test.com.onlineassignment.apiservice.response_pojo.BaseResponse;
import jokes.test.com.onlineassignment.apiservice.response_pojo.Value;
import jokes.test.com.onlineassignment.apiservice.response_pojo.ValueResponse;
import jokes.test.com.onlineassignment.apiservice.util.ApiCallback;
import jokes.test.com.onlineassignment.apiservice.util.ApiFactory;
import jokes.test.com.onlineassignment.view.ChangeTheNameView;
import jokes.test.com.onlineassignment.view.HomeView;

/**
 * Created by Gaurav on 1/17/2018.
 */

public class ChangeTheNamePresenter extends Presenter {

    private ChangeTheNameView view;

    public ChangeTheNamePresenter(ChangeTheNameView view) {
        this.view = view;
    }

    /**
     * This method invoke with activity onCreate() method
     * Initialize lifecycle related functionality
     */
    @Override
    public void onCreate() {

    }
    /**
     * This method invoke with activity onResume() method
     * This is lifecycle related functionality
     */
    @Override
    public void onResume() {

    }
    /**
     * This method invoke with activity onStop() method
     * This is lifecycle related functionality
     */
    @Override
    public void onStop() {

    }
    /**
     * This method invoke with activity onDestroy() method
     * Release registered listners here.
     *
     */
    @Override
    public void onDestroy() {

    }

    /**
     * Call network api and result update to UI
     * @param fname : firstname which used for input
     * @param lname : lastname which used for input
     */
    public void changeTheName(String fname , String lname) {
        ApiConfig.Configuration config = new ApiConfig.Configuration();
        config.setType("ChangeTheName");
        config.setFname(fname/*"John"*/);
        config.setLname(lname/*"Doe"*/);
        ApiConfig apiConfig = config.configure();

        ApiInteractor assetCategoryInteractor = ApiFactory.getApiInteractor(apiConfig);
        if (assetCategoryInteractor != null) {
            assetCategoryInteractor.setCallback(new ApiCallback() {
                @Override
                public void onSuccess(Object result) {
                    //Convert Json response to POJO
                    Gson gson = new Gson();
                    ValueResponse response = gson.fromJson(result.toString(), ValueResponse.class);
                    //Update text on UI
                    view.showChangedText(decodeHtmlEncodedString(response.getValue().getJoke()));
                }

                @Override
                public void onError(Object obj) {
                    view.showChangedText(null);
                }
            });

            ApiFactory.requestApi(assetCategoryInteractor);
        }
    }


}
