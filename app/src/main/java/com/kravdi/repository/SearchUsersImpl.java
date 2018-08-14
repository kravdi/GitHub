package com.kravdi.repository;

import com.kravdi.entity.SearchResponse;
import com.kravdi.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchUsersImpl implements SearchUsersInteractor {

    @Override
    public void searchOrganizations(final onFinishedListener onFinishedListener, String type, String searchQuery, int per_page) {

        String query = searchQuery + type;

        Call<SearchResponse> call = RetrofitClient.getInstance().getApiService().searchUsers(query);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()){
                    //TODO: save results to DB
                    onFinishedListener.onFinishSearchOrganizations(response.body());
                } else {
                    //TODO: Handle error and generate message
                    onFinishedListener.onFailedSearchOrganizations(response.message());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                //TODO: Handle error and generate message
                onFinishedListener.onFailedSearchOrganizations(t.getMessage());
            }
        });
    }
}