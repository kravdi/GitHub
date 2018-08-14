package com.kravdi.ui.userrepo;

import com.kravdi.base.BaseLoadingView;
import com.kravdi.entity.Repository;

import java.util.ArrayList;

public interface UserRepoView extends BaseLoadingView {

    void setUserRepositories(ArrayList<Repository> repositories);

}
