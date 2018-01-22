package jokes.test.com.onlineassignment.apiservice.response_pojo;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gaurav on 1/17/2018.
 * POJO for JsonObject of Value of response
 */
public class ValueResponse extends BaseResponse{


    @SerializedName("value")
    @Expose
    private Value value;


    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
