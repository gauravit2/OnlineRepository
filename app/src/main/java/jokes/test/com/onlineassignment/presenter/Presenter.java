package jokes.test.com.onlineassignment.presenter;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.BottomSheetDialog;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import jokes.test.com.onlineassignment.R;

/**
 * Created by Gaurav on 1/17/2018.
 * Loosely coupled with view and business model
 * Also contains Utility methods
 */

public abstract class Presenter {
    /**
     * Convert HTML encoded special character to String Format
     * @param input : special character included string
     * @return formated String
     */
    public String decodeHtmlEncodedString(String input) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                return Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY).toString();

            } else {
                return Html.fromHtml(input).toString();
            }
        } catch (Exception e) {
            return input;
        }
    }

    /**
     * Check network data connection is ON or OFF
     * @param context : view context
     * @return true is internet connection is on else return false
     */
    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Create BottomSheetDialog to show jokes at bottom of activity
     * This Dialog has single button to dismiss
     * @param activity : Attached activity instance bcoze dialog load inside that view
     * @param message : message text to show inside dialog
     */
    public void showBottomSheetDialog(Activity activity, String message) {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(activity);
        final View bottomSheetLayout = activity.getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, null);
        ((TextView) bottomSheetLayout.findViewById(R.id.tv_detail)).setText(message);
        (bottomSheetLayout.findViewById(R.id.button_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.show();
    }

    /**
     * Attach this to Activity/Fragment lifecycle onCreate() method
     */
    public abstract void onCreate();

    /**
     * Attach this to Activity/Fragment lifecycle onResume() method
     */
    public abstract void onResume();

    /**
     * Attach this to Activity/Fragment lifecycle onStop() method
     */
    public abstract void onStop();

    /**
     * Attach this to Activity/Fragment lifecycle onDestroy() method
     */
    public abstract void onDestroy();
}
