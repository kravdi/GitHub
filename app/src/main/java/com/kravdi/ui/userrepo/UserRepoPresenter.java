package com.kravdi.ui.userrepo;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kravdi.entity.Repository;
import com.kravdi.repository.GetUserRepoIntercator;

import java.util.ArrayList;

@InjectViewState
public class UserRepoPresenter extends MvpPresenter<UserRepoView> implements GetUserRepoIntercator.onFinishedListener{

    private GetUserRepoIntercator getUserRepoIntercator;

    public UserRepoPresenter(GetUserRepoIntercator getUserRepoIntercator) {
        this.getUserRepoIntercator = getUserRepoIntercator;
    }

    public void getUserRepositories(String userName){
        getViewState().showLoading(View.VISIBLE);
        getUserRepoIntercator.getUserRepo(this, userName);
    }

    @Override
    public void onFinishGetUserRepo(ArrayList<Repository> userRepos) {
      getViewState().setUserRepositories(userRepos);
    }

    @Override
    public void onFailedGetUserRepo(String message) {
       getViewState().showErrorToast(message);
    }
}
