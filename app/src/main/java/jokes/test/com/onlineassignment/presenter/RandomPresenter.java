package jokes.test.com.onlineassignment.presenter;

import android.content.Intent;
import android.os.Build;
import android.text.Html;

import com.google.gson.Gson;

import jokes.test.com.onlineassignment.apiservice.builders.param.ApiConfig;
import jokes.test.com.onlineassignment.apiservice.interactors.ApiInteractor;
import jokes.test.com.onlineassignment.apiservice.response_pojo.BaseResponse;
import jokes.test.com.onlineassignment.apiservice.response_pojo.ValueResponse;
import jokes.test.com.onlineassignment.apiservice.util.ApiCallback;
import jokes.test.com.onlineassignment.apiservice.util.ApiFactory;
import jokes.test.com.onlineassignment.view.*;

/**
 * Created by Gaurav on 1/17/2018.
 * This class is mediator between HomeActivity and Apiservice.
 * View is attached here for callback purpose so that is used to update ui in side presenter.
 * Also extend presenter abstract class for getting access of view lifecycle method also override abtract methods.
 */

public class RandomPresenter extends Presenter {

    private HomeView homeView;

    /**
     * create object of RandomPresenter
     * @param homeView : instance of view which is loosely coupled with presenter
     */
    public RandomPresenter(HomeView homeView) {
        this.homeView = homeView;
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
     * load random jokes from api server to show on ui.
     * this method configure network data and call retrofit request and return API response of random jokes
     */
    public void loadRandomJokes() {
        homeView.showProgressBar();
        ApiConfig.Configuration config = new ApiConfig.Configuration();
        config.setType("RandomJokes");
        ApiConfig apiConfig = config.configure();

        ApiInteractor assetCategoryInteractor = ApiFactory.getApiInteractor(apiConfig);
        if (assetCategoryInteractor != null) {
            assetCategoryInteractor.setCallback(new ApiCallback() {
                @Override
                public void onSuccess(Object result) {
                    //Convert JSON response to Reponse POJO using Gson
                    Gson gson = new Gson();
                    ValueResponse response = gson.fromJson(result.toString(), ValueResponse.class);

                    //Update UI
                    homeView.showRandomJokes(decodeHtmlEncodedString(response.getValue().getJoke()));
                }

                @Override
                public void onError(Object obj) {
                    //
                    homeView.showRandomJokes(null);
                }
            });

            ApiFactory.requestApi(assetCategoryInteractor);
        }
    }


    /**
     * pass intent to open new activity which shows input text for change the name
     */
    public void changeTheName() {
       homeView.openTextInput();
    }

    /**
     * pass intent to open new activity which shoes never ending list
     */
    public void loadNeverEndingList() {
        homeView.openNeverEndingListView();
    }
}
