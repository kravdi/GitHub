package com.kravdi.ui.userrepo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kravdi.organizations.R;
import com.kravdi.entity.Repository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_repository_name)
    TextView tvRepositoryName;
    @BindView(R.id.tv_repository_description)
    TextView tvRepositoryDescription;

    public RepoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindRepository(Repository repository){
        tvRepositoryName.setText(repository.getName());

        if(repository.getDescription() != null){
            tvRepositoryDescription.setVisibility(View.VISIBLE);
            tvRepositoryDescription.setText(repository.getDescription());
        } else {
            tvRepositoryDescription.setVisibility(View.GONE);
        }
    }
}
