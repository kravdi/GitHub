package com.kravdi.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kravdi.organizations.R;
import com.kravdi.entity.User;
import com.kravdi.ui.userrepo.UserRepoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kravdi on 5/15/18.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_user_avatar)
    ImageView ivUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_repository_count)
    TextView tvReositoryCount;
    @BindView(R.id.cl_user_card_layout)
    CardView clUserLayout;

    public UserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindUser(final Context context, final User user){
        Glide.with(context).load(user.getAvatarUrl()).into(ivUserAvatar);
        tvUserName.setText(user.getLogin());
        //TODO: Can't find info about repositories count of user, requires additional request(Github rate limit).
        tvReositoryCount.setText(user.getReposUrl());

        clUserLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserRepoActivity.class);
                intent.putExtra("user_login", user.getLogin());
                context.startActivity(intent);
            }
        });
    }
}
