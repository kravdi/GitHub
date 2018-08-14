package com.kravdi.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.kravdi.organizations.R;
import com.kravdi.entity.User;
import com.kravdi.repository.SearchUsersImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView, ButterKnife.Setter<View, Integer> {

    private final int TYPING_TIMEOUT = 1000; // 1 seconds timeout before starting search after stop typing
    private final int SEARCH_QUERY_LENGHT = 2; // start search after query lenght


    private TypingTimeout timeout;
    private UserAdapter userAdapter;
    final Handler timeoutHandler = new Handler();

    @InjectPresenter
    MainActivityPresenter presenter;

    @ProvidePresenter
    MainActivityPresenter mainActivityPresenter() {
        return new MainActivityPresenter(new SearchUsersImpl());
    }

    @BindViews({R.id.progressbar, R.id.view_clickable})
    List<View> progress;
    @BindView(R.id.rv_users)
    RecyclerView rvListOrganizations;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_empty_results)
    TextView tvEmptyResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        timeout = new TypingTimeout();
        rvListOrganizations.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this);
        rvListOrganizations.setAdapter(userAdapter);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String query = charSequence.toString();
                timeoutHandler.removeCallbacks(timeout);
                if (query.length() >= SEARCH_QUERY_LENGHT) {
                    timeout.setQuery(query);
                    timeoutHandler.postDelayed(timeout, TYPING_TIMEOUT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        timeoutHandler.removeCallbacks(timeout);
    }

    @Override
    public void setUserList(ArrayList<User> users) {
        userAdapter.clearSearchList();
        if(users != null && users.size() > 0){
            tvEmptyResults.setVisibility(View.GONE);
            userAdapter.addUsers(users);
        } else {
            tvEmptyResults.setVisibility(View.VISIBLE);
        }

        showLoading(View.GONE);
    }

    @Override
    public void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        showLoading(View.GONE);
    }

    @Override
    public void showLoading(int visibility) {
        ButterKnife.apply(progress, this, visibility);
    }

    @Override
    public void set(@NonNull View view, Integer value, int index) {
        view.setVisibility(value);
    }


    private class TypingTimeout implements Runnable {

        private String query;

        void setQuery(String query) {
            this.query = query;
        }

        @Override
        public void run() {
            showLoading(View.VISIBLE);
            presenter.startSearch(query);
        }
    }
}
