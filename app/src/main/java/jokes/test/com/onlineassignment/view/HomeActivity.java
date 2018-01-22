package jokes.test.com.onlineassignment.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import jokes.test.com.onlineassignment.R;
import jokes.test.com.onlineassignment.presenter.RandomPresenter;

/**
 * Created by Gaurav on 1/17/2018.
 * This class used for loading jokes content in three categories like random joke, edit joke, never ending list of jokes
 * Extend AppCompatActivity and implements OnClickListener and HomeView{Basically abstract method to load ui}
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, HomeView {

    /**
     * Instance of button for three categories
     */
    private Button randomJokesButton, textInputButton, neverEndingListButton;

    /**
     * Attach presenter for update view
     */
    private RandomPresenter randomPresenter = new RandomPresenter(this);

    /**
     * Progress bar for showing working on action
     */
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        //invoke lifecycle method with activity lifecycle
        randomPresenter.onCreate();

    }

    /**
     * initialize the views and attach listners for action handling
     */
    private void init() {
        randomJokesButton = (Button) findViewById(R.id.ActivityHome_buttonRandomJokes);
        textInputButton = (Button) findViewById(R.id.ActivityHome_buttonTextInput);
        neverEndingListButton = (Button) findViewById(R.id.ActivityHome_buttonNeverEndingList);
        progressBar = (ProgressBar)findViewById(R.id.ActivityHome_progressBar);

        randomJokesButton.setOnClickListener(this);
        textInputButton.setOnClickListener(this);
        neverEndingListButton.setOnClickListener(this);
    }

    /**
     * Click actions get called here
     * @param view : respected views action get called
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ActivityHome_buttonRandomJokes:
                randomPresenter.loadRandomJokes();
                break;
            case R.id.ActivityHome_buttonTextInput:
                randomPresenter.changeTheName();
                break;
            case R.id.ActivityHome_buttonNeverEndingList:
                randomPresenter.loadNeverEndingList();
                break;
        }
    }

    /**
     * Activity lifecycle method to attach in presenter
     */
    @Override
    protected void onResume() {
        super.onResume();
        randomPresenter.onResume();
    }

    /**
     * Activity lifecycle method to attach in presenter
     */
    @Override
    protected void onStop() {
        super.onStop();
        randomPresenter.onStop();
    }

    /**
     * Activity lifecycle method to attach in presenter
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        randomPresenter.onDestroy();
    }

    /**
     * Shows random jokes on UI which is loaded from network call
     * close progress bar
     * @param jokesText message to show
     */
    @Override
    public void showRandomJokes(String jokesText) {
        if(jokesText!=null)
            randomPresenter.showBottomSheetDialog(this,jokesText);
        else
            Toast.makeText(this,"Error in loading joke",Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Call new Activity {@ChangeTheNameActivity}
     */
    @Override
    public void openTextInput() {
        Intent in = new Intent(this,ChangeTheNameActivity.class);
        startActivity(in);
    }

    /**
     * Call new Activity {@NeverEndingListActivity}
     */
    @Override
    public void openNeverEndingListView() {
        Intent in = new Intent(this,NeverEndingListActivity.class);
        startActivity(in);
    }

    /**
     * Make visible progressbar
     */
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }




}
