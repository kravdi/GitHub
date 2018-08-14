package com.kravdi.ui.userrepo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.kravdi.organizations.R;
import com.kravdi.entity.Repository;
import com.kravdi.repository.GetUserRepoImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class UserRepoActivity extends MvpAppCompatActivity implements UserRepoView, ButterKnife.Setter<View, Integer> {

    private String userName;

    private RepoAdapter repoAdapter;

    @InjectPresenter
    UserRepoPresenter presenter;

    @ProvidePresenter
    UserRepoPresenter userRepoPresenter() {
        return new UserRepoPresenter(new GetUserRepoImpl());
    }

    @BindViews({R.id.progressbar, R.id.view_clickable})
    List<View> progress;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_user_repo)
    RecyclerView rvRepoList;
    @BindView(R.id.tv_no_repositories)
    TextView tvNoRepositories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            userName = getIntent().getStringExtra("user_login");
        }
        initToolbar();

        rvRepoList.setLayoutManager(new LinearLayoutManager(this));
        repoAdapter = new RepoAdapter();
        rvRepoList.setAdapter(repoAdapter);

        presenter.getUserRepositories(userName);
    }

    private void initToolbar() {
        toolbar.setTitle(userName);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setUserRepositories(ArrayList<Repository> repositories) {
        if(repositories.size() > 0){
            repoAdapter.addRepositories(repositories);
            tvNoRepositories.setVisibility(View.GONE);
        } else {
            tvNoRepositories.setVisibility(View.VISIBLE);
        }
        showLoading(View.GONE);
    }

    @Override
    public void showLoading(int visibility) {
        ButterKnife.apply(progress, this, visibility);
    }

    @Override
    public void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        showLoading(View.GONE);
    }

    @Override
    public void set(@NonNull View view, Integer value, int index) {
        view.setVisibility(value);
    }


}
