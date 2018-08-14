package com.kravdi.ui.main;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kravdi.entity.SearchResponse;
import com.kravdi.repository.SearchUsersInteractor;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> implements SearchUsersInteractor.onFinishedListener {

    final String SEARCH_TYPE = "type:user";
    final int per_page = 100;

    private SearchUsersInteractor searchUsersInteractor;

    public MainActivityPresenter(SearchUsersInteractor searchUsersInteractor) {
        this.searchUsersInteractor = searchUsersInteractor;
    }

    public void startSearch(String searchQuery) {
        searchUsersInteractor.searchOrganizations(this, SEARCH_TYPE, searchQuery, per_page);
    }

    @Override
    public void onFinishSearchOrganizations(SearchResponse response) {
        getViewState().setUserList(response.getUsers());
//        Log.d("TAG", "search results: " + response.getUsers().size());
    }

    @Override
    public void onFailedSearchOrganizations(String message) {
        getViewState().showErrorToast(message);
        Log.d("TAG", "fail request: " + message);
    }
}
