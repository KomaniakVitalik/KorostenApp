package com.korosten.www.api;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.korosten.www.R;
import com.korosten.www.model.KorostenResponse;
import com.korosten.www.model.Post;
import com.korosten.www.model.Type;
import com.korosten.www.util.Logger;
import com.korosten.www.util.Validator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Responsible for management of all the app's requests.
 * Also manages app's data here.
 */
public class DataManager {

    public static final String TAG = "DataManager";

    public static final String EMPTY_SERVER_MESSAGE = "";
    public static final int ERROR_NULL_RESPONSE_OBJECT = 1;

    public static final String FIELD_SIGHTSEENS = "dostoprimechatelnosti";

    private OnResponseListener mResponseListener;
    private RetrofitRequest requestBuilder;
    private KorostenResponse korostenResponse = null;


    public DataManager(RetrofitRequest retrofitRequest) {
        this.requestBuilder = retrofitRequest;
    }

    /**********************************************************************************************/
    /***************************************** SET UP *********************************************/
    /**********************************************************************************************/

    /**
     * Delivers response to listener, manages all the possible statuses of AppResponse object.
     * Generates response messages if failed.
     *
     * @param response - AppResponse
     */
    public boolean deliverResponse(Response<?> response, String requestTag) {
        if (Validator.isObjectValid(mResponseListener)) {
            if (Validator.isObjectValid(response)) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    mResponseListener.onResponseSuccess(response, requestTag);
                    return true;
                } else {
                    try {
                        String message = new Gson().fromJson(response.errorBody().string(), String.class);
                        if (Validator.isObjectValid(message)) {
                            notifyFailed(generateFailMessage(response.code()), message);
                        }
                    } catch (IOException | JsonSyntaxException | IllegalStateException e) {
                        notifyFailed(generateFailMessage(response.code()), EMPTY_SERVER_MESSAGE);
                        Logger.e("DataManager", e.toString());
                    }
                    return false;
                }
            } else {
                notifyFailed(ERROR_NULL_RESPONSE_OBJECT, EMPTY_SERVER_MESSAGE);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Notifies that response has failed
     *
     * @param statusIntMessage    - resources id
     * @param statusStringMessage - string message
     */
    private void notifyFailed(int statusIntMessage, String statusStringMessage) {
        if (Validator.isObjectValid(mResponseListener)) {
            mResponseListener.onResponseFail(statusIntMessage, statusStringMessage);
        }
    }

    private void notifyNoNetwork() {
        if (Validator.isObjectValid(mResponseListener)) {
            mResponseListener.onNoNetwork();
        }
    }


    /**
     * Registers Response Listener
     *
     * @param listener - Listener
     */
    public void registerResponseListener(OnResponseListener listener) {
        this.mResponseListener = listener;
    }

    /**
     * Generates string resource identifier of response basing on status code.
     *
     * @param statusCode - int status code
     */
    private int generateFailMessage(int statusCode) {
        int failMsg = -1;
        switch (statusCode) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                failMsg = R.string.http_bad_request;
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
                failMsg = R.string.http_forbidden;
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
                failMsg = R.string.http_not_found;
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                failMsg = R.string.http_server_error;
                break;
        }
        return failMsg;
    }

    /**
     * Logs onFail callback
     *
     * @param throwable - Throwable
     */
    private void logException(Throwable throwable) {
        if (Validator.isObjectValid(throwable)) {
            Logger.e(TAG, throwable.toString());
        }
    }

    /**
     * Checks whether Throwable is of IOException - meaning that there's no Internet connection
     *
     * @param t - Throwable
     * @return - true if IOException
     */
    private boolean isNoNetworkException(Throwable t) {
        if (t instanceof IOException) {
            notifyNoNetwork();
            return true;
        } else {
            logException(t);
            return false;
        }
    }

    /**
     * Listener to deliver response to
     */
    public interface OnResponseListener {
        //Notifies response is success
        void onResponseSuccess(Response<?> response, String pendingRequestTag);

        //Notifies response failed
        void onResponseFail(int statusMessage, String serverMessage);

        //Notifies listener of No Internet issue
        void onNoNetwork();
    }

    /**********************************************************************************************/
    /**************************************** Requests ********************************************/
    /**********************************************************************************************/

    private Set<Type> postTypes = new HashSet<>();

    /**
     * Checks data count on server.
     */
    public void serverGetDataCount() {
        requestBuilder.getDataCount(new Callback<KorostenResponse>() {
            @Override
            public void onResponse(Call<KorostenResponse> call, Response<KorostenResponse> response) {
                if (!Validator.isObjectValid(response)) {
                    notifyFailed(ERROR_NULL_RESPONSE_OBJECT, EMPTY_SERVER_MESSAGE);
                    return;
                }

                KorostenResponse countResponse = response.body();

                if (!Validator.isObjectValid(response.body())) {
                    notifyFailed(ERROR_NULL_RESPONSE_OBJECT, EMPTY_SERVER_MESSAGE);
                    return;
                }

                Logger.d(TAG, "serverGetDataCount :: pages " + response.body().getPages());
                Logger.d(TAG, "serverGetDataCount :: count total " + response.body().getCountTotal());
                serverGetAllData(countResponse.getCountTotal());

            }

            @Override
            public void onFailure(Call<KorostenResponse> call, Throwable t) {
                Logger.e(TAG, "serverGetDataCount :: failure " + t.toString());
                notifyFailed(ERROR_NULL_RESPONSE_OBJECT, EMPTY_SERVER_MESSAGE);
            }
        });
    }

    /**
     * Requests desired data count from server.
     */
    public static final String REQUEST_GET_ALL_DATA = "get_all_data";

    private void serverGetAllData(int count) {
        requestBuilder.getAllData(count, new Callback<KorostenResponse>() {
            @Override
            public void onResponse(Call<KorostenResponse> call, Response<KorostenResponse> response) {
                Logger.d(TAG, "serverGetAllData :: pages " + response.body().getPages());
                Logger.d(TAG, "serverGetAllData :: count total " + response.body().getCountTotal());
                if (deliverResponse(response, REQUEST_GET_ALL_DATA)) {
                    korostenResponse = response.body();
                }
            }

            @Override
            public void onFailure(Call<KorostenResponse> call, Throwable t) {
                Logger.e(TAG, "serverGetAllData :: failure " + t.toString());
                notifyFailed(ERROR_NULL_RESPONSE_OBJECT, EMPTY_SERVER_MESSAGE);
            }
        });
    }

    /**
     * Requests Posts for provided tag
     *
     * @param tag - slug
     */
    public void serverGetPostsForTag(String tag) {
        requestBuilder.getPostsForTag(tag, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * Returns Set of available Type of Posts
     */
    public Set<Type> getPostsTypesSet() {

        if (!postTypes.isEmpty()) {
            Logger.d(TAG, "getPostsTypesSet :: set is available, do not process");
            return postTypes;
        }

        Set<Type> typeSet = new HashSet<>();
        for (Post p : getKorostenResponse().getPosts()) {
            if (Validator.isListValid(p.getTaxonomyAitItemsList())) {
                if (p.getTaxonomyAitItemsList().size() > 0) {
                    typeSet.add(p.getTaxonomyAitItemsList().get(0));
                }
            }
        }
        postTypes = typeSet;
        return typeSet;
    }

    /**
     * Queries KorostenResponse object to find Posts for provided Type
     *
     * @param type - Type
     * @return - List<Post></>
     */
    public List<Post> getPostsListForType(Type type) {
        List<Post> posts = new ArrayList<>();
        if (Validator.isObjectValid(type)) {
            for (Post p : getKorostenResponse().getPosts()) {
                if (Validator.isListValid(p.getTaxonomyAitItemsList())) {
                    if (p.getTaxonomyAitItemsList().size() > 0) {
                        Type t = p.getTaxonomyAitItemsList().get(0);
                        if (Validator.isObjectValid(t)) {
                            if (t.equals(type)) {
                                posts.add(p);
                            }
                        }
                    }
                }
            }
        }
        return posts;
    }


    public KorostenResponse getKorostenResponse() {
        return korostenResponse;
    }
}
