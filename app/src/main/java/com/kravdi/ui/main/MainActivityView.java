package com.kravdi.ui.main;

import com.kravdi.base.BaseLoadingView;
import com.kravdi.entity.User;

import java.util.ArrayList;

public interface MainActivityView extends BaseLoadingView {

    void setUserList(ArrayList<User> organizations);

}
