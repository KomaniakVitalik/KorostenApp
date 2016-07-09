package com.korosten.www.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.korosten.www.CoreApp;
import com.korosten.www.R;

import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        registerResponseListener(this);
        getDataManager().getDataCount();
    }

    @Override
    protected void onDestroy() {
        registerResponseListener(null);
        super.onDestroy();
    }

    @Override
    public void onResponseSuccess(Response<?> response, String pendingRequestTag) {
        super.onResponseSuccess(response, pendingRequestTag);

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
