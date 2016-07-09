package com.korosten.www.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.korosten.www.CoreApp;
import com.korosten.www.R;
import com.korosten.www.adapters.PostRecyclerAdapter;
import com.korosten.www.model.Post;

import java.util.List;

public class TestCategoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_category_list);
        setUpRecyclerView(CoreApp.getInstance().getPostsStub());
    }

    private void setUpRecyclerView(List<Post> posts) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_view_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PostRecyclerAdapter adapter = new PostRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        adapter.getPostList().clear();
        adapter.getPostList().addAll(posts);
        adapter.notifyDataSetChanged();
    }
}
