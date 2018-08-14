package com.kravdi.repository;

import com.kravdi.entity.Repository;
import com.kravdi.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserRepoImpl implements GetUserRepoIntercator {

    @Override
    public void getUserRepo(final onFinishedListener onFinishedListener, String userName) {
        Call<ArrayList<Repository>> call = RetrofitClient.getInstance().getApiService().getUserReposiries(userName);
        call.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                if(response.isSuccessful()){
                    //TODO: save results to DB
                    onFinishedListener.onFinishGetUserRepo(response.body());
                } else {
                    //TODO: Handle error and generate message
                    onFinishedListener.onFailedGetUserRepo(response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
                //TODO: Handle error and generate message
                onFinishedListener.onFailedGetUserRepo(t.getMessage());
            }
        });
    }
}
