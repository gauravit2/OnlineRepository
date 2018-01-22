package jokes.test.com.onlineassignment.apiservice.util;

/**
 * Created by Gaurav on 1/17/2018.
 * Interface with success and error mothod declared
 */

public abstract class ApiCallback {

    /**
     * Method to be implemented for success handling
     * @param obj : message
     */
    public abstract void onSuccess(Object obj);

    /**
     * Method to be implemented for error handling
     * @param obj : message
     */
    public abstract void onError(Object obj);
}
