package jokes.test.com.onlineassignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jokes.test.com.onlineassignment.R;
import jokes.test.com.onlineassignment.apiservice.response_pojo.Value;

/**
 * Created by Gaurav on 1/17/2018
 * Bind List Items to UI.
 * This is adapter used to load list item in recycler view.
 */
public class JokesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Flag for set message
    private final int TYPE_JOKE = 0;

    //Flag for show Loader
    private final int TYPE_LOAD = 1;

    //View Context
    private Context context;

    //Collection of value element
    private List<Value> jokes;

    //Callback Listner
    private OnLoadMoreListener loadMoreListener;

    //state of recycler view
    private boolean isLoading = false, isMoreDataAvailable = true;

    /*
    * isLoading - to set the remote loading and complete status to fix back to back load more call
    * isMoreDataAvailable - to set whether more data from server available or not.
    * It will prevent useless load more request even after all the server data loaded
    * */
    public JokesAdapter(Context context, List<Value> movies) {
        this.context = context;
        this.jokes = movies;
    }

    /* Inflate item layout by checing item type*/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_JOKE) {
            return new JokesHolder(inflater.inflate(R.layout.row_joke, parent, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.row_load, parent, false));
        }
    }

    /* bind view with data*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) == TYPE_JOKE) {
            ((JokesHolder) holder).bindData(jokes.get(position));
        }
        //No else part needed as load holder doesn't bind any data
    }

    /**
     * Get type of item
     * @param position index of list
     * @return If list item is not null then return TYPE_JOKE else TYPE_LOAD
     */
    @Override
    public int getItemViewType(int position) {
        if (jokes.get(position) != null) {
            return TYPE_JOKE;
        } else {
            return TYPE_LOAD;
        }
    }

    /**
     * Return List total elements count
     * @return list size
     */
    @Override
    public int getItemCount() {
        return jokes.size();
    }

    /* View Holder for load joke data */
    static class JokesHolder extends RecyclerView.ViewHolder {
        TextView tvJoke;

        public JokesHolder(View itemView) {
            super(itemView);
            tvJoke = (TextView) itemView.findViewById(R.id.RowJoke_tvText);
        }

        void bindData(Value model) {
            tvJoke.setText(model.getJoke());
        }
    }

    /**
     * View Holder for progress bar at bottom for lazy loading
     */
    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * flag to set more data available or not
     * @param moreDataAvailable boolean flag
     */
    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /**
     * Notify view if changes and update flag
     */
    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }


    /**
     * Contract method for callback
     */
    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    /**
     * set callbacl listner for loadmore items
     * @param loadMoreListener callback parameter
     */
    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}
