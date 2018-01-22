package jokes.test.com.onlineassignment.apiservice.response_pojo;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gaurav on 1/17/2018.
 * POJO for Json response of API Request
 */
public class BaseResponse {
    //{ "type": "success", "value": { "id": 268, "joke": "Time waits for no man. Unless that man is Chuck Norris." } }
    @SerializedName("type")
    @Expose
    private String type;




    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
