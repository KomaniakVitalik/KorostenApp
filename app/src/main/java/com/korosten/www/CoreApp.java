package com.korosten.www;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.korosten.www.api.DataManager;
import com.korosten.www.api.RetrofitRequest;
import com.korosten.www.util.Validator;


/**
 * Created by vitaliy.herasymchuk on 6/25/16.
 */
public class CoreApp extends Application {

    private static CoreApp instance;
    private DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public DataManager getDataManager() {
        if (!Validator.isObjectValid(dataManager)) {
            dataManager = new DataManager(new RetrofitRequest());
        }
        return dataManager;
    }

    public static CoreApp getInstance() {
        return instance;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return false;
        } else {
            if (ni.isConnected()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
