package jokes.test.com.onlineassignment.view;

/**
 * Created by Gaurav on 1/17/2018.
 * Contains contracts method to pass message to view
 */

public interface HomeView {
    /**
     * Show random text on UI
     * @param jokesText text to show
     */
    public void showRandomJokes(String jokesText);

    /**
     * contact method to open activity
     */
    public void openTextInput();

    /**
     * contact method to open activity
     */
    public void openNeverEndingListView();


    /**
     * contact method to show preogressbar
     */
    public void showProgressBar();


}
