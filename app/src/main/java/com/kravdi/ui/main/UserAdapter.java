package com.kravdi.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kravdi.organizations.R;
import com.kravdi.entity.User;

import java.util.ArrayList;

/**
 * Created by kravdi on 5/15/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Context context;
    private ArrayList<User> users;

    public UserAdapter(Context context){
        this.context = context;
        users = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_user, parent, false);
        return  new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bindUser(context, users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void clearSearchList(){
        users.clear();
    }

    public void addUsers(ArrayList<User> users){
        this.users.addAll(users);
        notifyDataSetChanged();
    }
}
