package com.korosten.www.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.korosten.www.CoreApp;
import com.korosten.www.R;
import com.korosten.www.model.Type;
import com.korosten.www.util.Logger;

import java.util.Set;

public class TestDataActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = TestDataActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);

        generateUiBasingOnAvailablePostTypes(getDataManager().getPostsTypesSet());
    }


    //TODO Stub
    private void generateUiBasingOnAvailablePostTypes(Set<Type> postTypes) {
        Logger.d(TAG, "Set size " + postTypes.size());
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        for (Type type : postTypes) {
            Button button = new Button(this);
            button.setText(type.getTitle());
            button.setTag(type);
            button.setOnClickListener(this);
            container.addView(button);
        }
        Logger.d(TAG, "Added size " + container.getChildCount());
    }

    @Override
    public void onClick(View v) {
        Type type = (Type) v.getTag();
        CoreApp.getInstance().setPostsStub(getDataManager().getPostsListForType(type));
//        CoreApp.getInstance().getDataManager().serverGetPostsForTag(type.getSlug());
        startActivity(new Intent(this, TestCategoryListActivity.class));
    }
}
