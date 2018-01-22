package jokes.test.com.onlineassignment.apiservice.response_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gaurav on 1/18/2018.
 * POJO for Value key in Json Response
 */

public class Value {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("joke")
    @Expose
    private String joke;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
