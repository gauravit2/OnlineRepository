package jokes.test.com.onlineassignment.apiservice.util;


import jokes.test.com.onlineassignment.apiservice.builders.param.ApiConfig;
import jokes.test.com.onlineassignment.apiservice.interactors.ApiInteractor;
import jokes.test.com.onlineassignment.apiservice.interactors.ChangeLastNameInteractor;
import jokes.test.com.onlineassignment.apiservice.interactors.NeverEndingListInteractor;
import jokes.test.com.onlineassignment.apiservice.interactors.RandomJokesInteractor;

/**
 * Created by Gaurav on 1/17/2018.
 * Bifurcate the api type and request execution
 */

public class ApiFactory {

    /**
     * get Interactor by checking type of api
     * @param api APIConfig which holds type of request call
     * @return APIInteractor for submitting task
     */
    public static ApiInteractor getApiInteractor(ApiConfig api) {
        ApiInteractor interactor = null;
        switch (api.type) {
            case "RandomJokes":
                interactor = new RandomJokesInteractor(api);
                interactor.setApiConfig(api);
                return interactor;
            case "ChangeTheName":
                interactor = new ChangeLastNameInteractor(api);
                interactor.setApiConfig(api);
                return interactor;
            case "NeverEndingList":
                interactor = new NeverEndingListInteractor(api);
                interactor.setApiConfig(api);
                return interactor;

        }
        return null;
    }

    /**
     * Task to exceute
     * @param interactor request executor for http call
     */
    public static void requestApi(ApiInteractor interactor) {
        interactor.execute(); //For using Retrofit's created thread
    }


}
