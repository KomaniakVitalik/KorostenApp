package com.korosten.www.api;


import com.korosten.www.model.KorostenResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Responsible for creating Retrofit Requests
 */
public class RetrofitRequest {

    private static final String TAG = RetrofitRequest.class.getSimpleName();

    public static final int TIMEOUT = 60;
    private static final String BASE_URL = "http://korosten.top/api/";
    public static final String KOROSTEN_TOKEN = "v=3943d8795e03";
    private static final String GET_POSTS_COUNT = "get_recent_posts/?page=1&post_type=any&" + KOROSTEN_TOKEN;
    private static final String GET_RECENT_ALL_POSTS = "get_recent_posts/?page=1&post_type=any";

    private KorostenApi api;

    public RetrofitRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(KorostenApi.class);

    }

    private OkHttpClient createHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(createLoggingInterceptor());
        return builder.build();
    }

    private HttpLoggingInterceptor createLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return loggingInterceptor;
    }


    public interface KorostenApi {
        @GET(GET_POSTS_COUNT)
        Call<KorostenResponse> checkAmountOfData();

        @GET(GET_RECENT_ALL_POSTS)
        Call<KorostenResponse> getAllData(@Query("count") String count);

    }

    /**********************************************************************************************/
    /************************************** Api Calls *********************************************/
    /**********************************************************************************************/

    public void getDataCount(Callback<KorostenResponse> callback) {
        api.checkAmountOfData().enqueue(callback);
    }

    public void getAllData(int totalDataCount, Callback<KorostenResponse> callback) {
        api.getAllData(String.valueOf(totalDataCount)).enqueue(callback);
    }


}
