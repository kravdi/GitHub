package com.kravdi.network;

import com.kravdi.entity.Repository;
import com.kravdi.entity.SearchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String SEARCH_QUERY = "q";

    @GET("search/users")
    Call<SearchResponse> searchUsers(@Query(SEARCH_QUERY) String search);

    @GET("users/{username}/repos")
    Call<ArrayList<Repository>> getUserReposiries(@Path("username") String userName);

}

