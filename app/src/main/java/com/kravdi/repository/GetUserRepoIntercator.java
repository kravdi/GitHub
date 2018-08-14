package com.kravdi.repository;

import com.kravdi.entity.Repository;

import java.util.ArrayList;

public interface GetUserRepoIntercator {

    interface onFinishedListener{
        void onFinishGetUserRepo(ArrayList<Repository> userRepos);
        void onFailedGetUserRepo(String message);
    }

    void getUserRepo(onFinishedListener onFinishedListener, String userName);
}
