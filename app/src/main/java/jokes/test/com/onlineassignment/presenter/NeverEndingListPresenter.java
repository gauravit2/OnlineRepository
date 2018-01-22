package jokes.test.com.onlineassignment.presenter;

import com.google.gson.Gson;

import jokes.test.com.onlineassignment.apiservice.builders.param.ApiConfig;
import jokes.test.com.onlineassignment.apiservice.interactors.ApiInteractor;
import jokes.test.com.onlineassignment.apiservice.response_pojo.ValuesResponse;
import jokes.test.com.onlineassignment.apiservice.util.ApiCallback;
import jokes.test.com.onlineassignment.apiservice.util.ApiFactory;
import jokes.test.com.onlineassignment.view.NeverEndingListView;

/**
 * Created by Gaurav on 1/17/2018.
 * Mediator between Api network call and NeverEndingList activity
 * extends Presenter for activity lifecycle access
 */

public class NeverEndingListPresenter extends Presenter {

    /**
     * Instance variable for view
     */
    private NeverEndingListView neverEndingListView;

    /**
     * Contrustor for initailization view
     * @param neverEndingListView view to be accessed here for updating view
     */
    public NeverEndingListPresenter(NeverEndingListView neverEndingListView) {
        this.neverEndingListView = neverEndingListView;
    }

    /**
     * This method invoke with activity onCreate() method
     * Initialize lifecycle related functionality
     */
    @Override
    public void onCreate() {
        loadNeverEndingList(false);
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
     * Load 20 jokes from api server and update list with data
     * @param isLoadmore : true if list is already loaded
     * Data returned to ui using NeverEndingListView methods
     */
    public void loadNeverEndingList(final boolean isLoadmore) {
        ApiConfig.Configuration config = new ApiConfig.Configuration();
        config.setType("NeverEndingList");
        config.setLimit(20);//Load 20 jokes from server
        ApiConfig apiConfig = config.configure();

        ApiInteractor assetCategoryInteractor = ApiFactory.getApiInteractor(apiConfig);
        if (assetCategoryInteractor != null) {
            assetCategoryInteractor.setCallback(new ApiCallback() {
                @Override
                public void onSuccess(Object result) {
                    //Convert json response to POJO
                    Gson gson = new Gson();
                    ValuesResponse response = gson.fromJson(result.toString(), ValuesResponse.class);


                    //First time isLoadmore is false
                    if(isLoadmore)
                        neverEndingListView.hideProgressBar();
                    else
                        neverEndingListView.hideLoader();

                    //Update UI
                    neverEndingListView.loadNeverEndingList(response.getValue());
                }

                @Override
                public void onError(Object obj) {
                    neverEndingListView.loadNeverEndingList(null);
                    neverEndingListView.hideLoader();
                }
            });

            ApiFactory.requestApi(assetCategoryInteractor);
        }
    }

    /**
     * Load next 20 jokes and show lazy loader
     */
    public void LoadMoreNeverEndingList(){
        neverEndingListView.showProgressBar();
        loadNeverEndingList(true);
    }

}
