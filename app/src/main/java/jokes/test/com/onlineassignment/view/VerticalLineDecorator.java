package jokes.test.com.onlineassignment.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Gaurav on 1/17/2018
 * DIvider line for adapter views
 */
public class VerticalLineDecorator extends RecyclerView.ItemDecoration {
    private int space=0;

    /**
     * Constructor for initialize size
     * @param space
     */
    public VerticalLineDecorator(int space) {
        this.space = space;
    }

    /**
     * Initialise Top and Bottom size
     * @param outRect instance of Rect
     * @param view rect is loading inside this view
     * @param parent parent view
     * @param state Contains useful information about the current RecyclerView state like target scroll
     * position or view focus. State object can also keep arbitrary data, identified by resource
     * ids
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildAdapterPosition(view) == 0)
            outRect.top = space;

        outRect.bottom = space;
    }
}
