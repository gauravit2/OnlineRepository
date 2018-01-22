package jokes.test.com.onlineassignment.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import jokes.test.com.onlineassignment.R;
import jokes.test.com.onlineassignment.presenter.ChangeTheNamePresenter;

/**
 * Created by Gaurav on 1/18/2018.
 * This activity render functionaity of get change name data from input and show changed name joke in dialog
 * Extend AppCompatActivity and implements OnClickListener and ChangeTheNameView{Basically abstract method to load ui}
 */

public class ChangeTheNameActivity extends AppCompatActivity implements View.OnClickListener, ChangeTheNameView {

    /**
     * Attach presenter for update view
     */
    private ChangeTheNamePresenter changeTheNamePresenter = new ChangeTheNamePresenter(this);
    /**
     * Progress bar for showing working on action
     */
    private ProgressBar progressBar;
    /**
     * Input view for enter text
     */
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changethename);

        initView();
    }

    /**
     * Initialize the xml views.
     * Also register listner for action
     */
    private void initView() {
        Button buttonChange = (Button) findViewById(R.id.ActivityChangetheName_buttonChange);
        buttonChange.setOnClickListener(this);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.ActivityChangetheName_autoCompleteTextViewName);

        progressBar = (ProgressBar) findViewById(R.id.ActivityChangetheName_progressBar);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ActivityChangetheName_buttonChange:
                change();
                break;
        }
    }

    /**
     * Change the name by checking name is valid or not
     * If Invalid then fire error else split name into fullname and lastname and pass to the presenter
     * Also show progress bar to show action is called
     */
    private void change() {
        // Store values at the time of the login attempt.
        String name = autoCompleteTextView.getText().toString();
        if (TextUtils.isEmpty(name)) {
            autoCompleteTextView.setError(getString(R.string.error_empty_name));
        } else if (!isNameValid(name)) {
            autoCompleteTextView.setError(getString(R.string.error_invalid_name));
        } else {
            progressBar.setVisibility(View.VISIBLE);
            String[] names = name.split(" ");
            changeTheNamePresenter.changeTheName(names[0], names[1]);
        }
    }

    /**
     * Check Name valid on basis of length of 2
     * @param name : input value to check
     * @return true if name is valid else false
     */
    private boolean isNameValid(String name) {
        if (name.split(" ").length != 2)
            return false;
        return true;
    }

    /**
     * Update Ui with passed text
     * and Hide Progress bar for action is done
     * @param text : message to show
     */
    @Override
    public void showChangedText(String text) {

        if (text != null) {
            changeTheNamePresenter.showBottomSheetDialog(this, text);
        } else {
            Toast.makeText(this, "Error in loading joke", Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.GONE);

    }
}
