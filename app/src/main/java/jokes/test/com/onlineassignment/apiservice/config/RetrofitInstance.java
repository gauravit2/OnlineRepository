package jokes.test.com.onlineassignment.apiservice.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gaurav on 1/17/2018.
 * Return retrofit instance
 */
public class RetrofitInstance {


    //Base url
    private static String BASE_URL = "http://api.icndb.com";


    /**
     * Create OkHttpClient instance with timeout
     * @return Instance of OkHttpClient
     */
    public static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        return client;
    }


    /**
     * Configure retrofit instance
     * @return retrofit object
     */
    public static Retrofit getRetrofitInstance() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = getClient();
        Dispatcher dispatcher = client.dispatcher();
        dispatcher.setMaxRequests(3);
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(client)
                .build();

    }


}
