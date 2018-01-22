package jokes.test.com.onlineassignment.view;

import java.util.List;

import jokes.test.com.onlineassignment.apiservice.response_pojo.Value;

/**
 * Created by Gaurav on 1/17/2018.
 * Contract methods for view updation of never ending list activity
 */

public interface NeverEndingListView {

    /**
     * Contract to load never ending list
     * @param jokes list of elements of Type Value
     */
    public void loadNeverEndingList(List<Value> jokes);

    /**
     * Contract to show lazy loader
     */
    public void showProgressBar();

    /**
     * Contract to hide lazy loader
     */
    public void hideProgressBar();

    /**
     * Contract to show first time loader
     */
    public void hideLoader();
}
