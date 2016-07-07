package com.korosten.www.api;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Responsible for creating Retrofit Requests
 */
public class RetrofitRequest {

    private static final String TAG = RetrofitRequest.class.getSimpleName();

    public static final int TIMEOUT = 60;
    private static final String BASE_URL = "http://korosten.top/api/";

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
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return loggingInterceptor;
    }


    public interface KorostenApi {
        @GET()
        Call<ResponseBody> getAllData();

    }

    /**********************************************************************************************/
    /************************************** Api Calls *********************************************/
    /**********************************************************************************************/

    public void getAllData(Callback<ResponseBody> callback) {
        api.getAllData().enqueue(callback);
    }


}
