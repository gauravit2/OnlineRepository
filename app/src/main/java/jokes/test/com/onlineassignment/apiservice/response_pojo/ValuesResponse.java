package jokes.test.com.onlineassignment.apiservice.response_pojo;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gaurav on 1/17/2018.
 * POJO for Arraylist of value in json response
 */
public class ValuesResponse extends BaseResponse{


    @SerializedName("value")
    @Expose
    private ArrayList<Value> value;

    public ArrayList<Value> getValue() {
        return value;
    }

    public void setValue(ArrayList<Value> value) {
        this.value = value;
    }
}
