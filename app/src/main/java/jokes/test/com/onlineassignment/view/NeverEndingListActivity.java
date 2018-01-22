package jokes.test.com.onlineassignment.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import jokes.test.com.onlineassignment.R;
import jokes.test.com.onlineassignment.adapter.JokesAdapter;
import jokes.test.com.onlineassignment.apiservice.response_pojo.Value;
import jokes.test.com.onlineassignment.presenter.NeverEndingListPresenter;

/**
 * Created by Gaurav on 1/17/2018
 * This class used for loading list of jokes and user can load it infinite time
 * Extend AppCompatActivity and implements NeverEndingListView{Basically abstract method to load ui}
 */
public class NeverEndingListActivity extends AppCompatActivity implements NeverEndingListView {

    /**
     * View instance for loading list of items
     */
    RecyclerView recyclerView;
    /**
     * Collection of jokes element
     */
    List<Value> jokes;

    /**
     * bind list elements to view
     */
    JokesAdapter adapter;
    /**
     * Attach presenter for update view
     */
    private NeverEndingListPresenter neverEndingListPresenter = new NeverEndingListPresenter(this);
    /**
     * Progress bar for showing working on action
     */
    private ProgressBar progressBar;

    /**
     * Activity lifecycle method for create and initialize layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_never_ending_list);
        recyclerView = (RecyclerView) findViewById(R.id.ActivityNeverEndingList_recyclerViewJokes);
        progressBar = (ProgressBar)findViewById(R.id.ActivityNeverEndingList_progressBar);
        jokes = new ArrayList<>();
        adapter = new JokesAdapter(this, jokes);
        adapter.setLoadMoreListener(new JokesAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        neverEndingListPresenter.LoadMoreNeverEndingList();
                    }
                });

            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalLineDecorator(2));
        recyclerView.setAdapter(adapter);

        //Load 20 elements for first time
        neverEndingListPresenter.onCreate();
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Load list with elements and update recycler view by notifyDataChanged
     * If list is null then show error toast message else load list
     * @param values : list of value elements
     */
    @Override
    public void loadNeverEndingList(List<Value> values) {
        if (values != null) {
            if (values.size() > 0) {
                //add loaded data
                jokes.addAll(values);
            } else {//result size 0 means there is no more data available at server
                adapter.setMoreDataAvailable(false);
                //telling adapter to stop calling load more as no more server data available
                Toast.makeText(NeverEndingListActivity.this, "No More Data Available", Toast.LENGTH_LONG).show();
            }
            adapter.notifyDataChanged();
        } else {
            Toast.makeText(this, "Error in loading Data", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Make visible progressbar at bottom of recycler view while network call execution
     * Add empty elements and refresh that list item which shows loader at that position
     */
    @Override
    public void showProgressBar() {
        jokes.add(null);
        adapter.notifyItemInserted(jokes.size() - 1);
    }

    /**
     * Hide progressbar at bottom of recycler view after network call execution
     * Remove added empty element previously and refresh that list item which hide loader at that position
     */
    @Override
    public void hideProgressBar() {
        //remove loading view
        jokes.remove(jokes.size() - 1);
        adapter.notifyDataChanged();
    }

    /**
     * Hide first time loader
     */
    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }
}
