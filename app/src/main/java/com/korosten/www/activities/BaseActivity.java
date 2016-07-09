package com.korosten.www.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.korosten.www.CoreApp;
import com.korosten.www.R;
import com.korosten.www.api.DataManager;

import retrofit2.Response;

/**
 * Created by vitaliy.herasymchuk on 7/9/16.
 */
public class BaseActivity extends AppCompatActivity
        implements DataManager.OnResponseListener {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onDestroy() {
        getDataManager().registerResponseListener(null);
        super.onDestroy();
    }

    protected DataManager getDataManager() {
        return CoreApp.getInstance().getDataManager();
    }

    protected String getResString(int id) {
        return getResources().getString(id);
    }

    protected Drawable getResDrawable(int id) {
        return ContextCompat.getDrawable(this, id);
    }

    protected void TOAST(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void TOAST(int msg) {
        TOAST(getResString(msg));
    }

    protected void registerResponseListener(DataManager.OnResponseListener l) {
        getDataManager().registerResponseListener(l);
    }

    @Override
    public void onResponseSuccess(Response<?> response, String pendingRequestTag) {

    }

    @Override
    public void onResponseFail(int statusMessage, String serverMessage) {
        if (statusMessage == DataManager.ERROR_NULL_RESPONSE_OBJECT) {
            TOAST(R.string.http_unexpected);
        }
    }

    @Override
    public void onNoNetwork() {
        TOAST(R.string.error_internet);
    }
}
