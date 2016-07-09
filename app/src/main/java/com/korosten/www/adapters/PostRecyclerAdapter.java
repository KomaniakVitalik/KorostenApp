package com.korosten.www.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.korosten.www.R;
import com.korosten.www.model.Attachment;
import com.korosten.www.model.Image;
import com.korosten.www.model.Post;
import com.korosten.www.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy.herasymchuk on 7/9/16.
 */
public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder> {

    private List<Post> mPostList = new ArrayList<>();

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(this, v);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bindPost(mPostList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        Context context;
        PostRecyclerAdapter adapter;
        TextView tvPostBody;
        ImageView ivPost;

        public PostViewHolder(PostRecyclerAdapter adapter, View itemView) {
            super(itemView);
            this.adapter = adapter;
            this.tvPostBody = (TextView) itemView.findViewById(R.id.tv_post_body);
            this.context = itemView.getContext();
            this.ivPost = (ImageView) itemView.findViewById(R.id.iv_post_thumb);
        }

        public void bindPost(Post p) {
            if (Validator.isObjectValid(p)) {

                if (Validator.isStringValid(p.getContent())) {
                    tvPostBody.setText(Html.fromHtml(p.getContent()));
                } else {
                    tvPostBody.setText("");
                }

                ivPost.setImageDrawable(null);
                if (Validator.isListValid(p.getAttachmentList())) {
                    if (p.getAttachmentList().size() > 0) {
                        Attachment attachment = p.getAttachmentList().get(0);
                        if (Validator.isObjectValid(attachment)) {
                            Image im = attachment.getImageTypes().getMedium();
                            if (Validator.isObjectValid(im)) {
                                String url = im.getUrl();
                                if (Validator.isStringValid(url)) {
                                    Glide.with(context).load(url).centerCrop().into(ivPost);
                                }
                            }
                        }
                    }
                }


            }
        }
    }

    public List<Post> getPostList() {
        return mPostList;
    }
}
