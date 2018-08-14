package com.kravdi.repository;

import com.kravdi.entity.SearchResponse;

public interface SearchUsersInteractor {

    interface onFinishedListener{
        void onFinishSearchOrganizations(SearchResponse searchResultList);
        void onFailedSearchOrganizations(String message);
    }

    void searchOrganizations(onFinishedListener onFinishedListener,  String type, String searchQuery, int per_page);
}
