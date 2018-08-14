package com.kravdi.ui.userrepo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kravdi.organizations.R;
import com.kravdi.entity.Repository;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private ArrayList<Repository> repositories;

    public RepoAdapter(){
        repositories = new ArrayList<>();
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_repository, parent, false);
        return  new RepoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bindRepository(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void addRepositories(ArrayList<Repository> repositories){
        this.repositories.addAll(repositories);
        notifyDataSetChanged();
    }
}
