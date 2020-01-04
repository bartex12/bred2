package ru.geekbrains.android.arch.mvp.model.interactors;

import android.util.Log;

import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.repositories.UserRepositoryNoFx;

public class UserModelImplNoRx implements UserModelNoRx {
    private static final String TAG = "33333";
    private final UserRepositoryNoFx userRepositoryNoFx;

    public UserModelImplNoRx(UserRepositoryNoFx userRepositoryNoFx) {
        this.userRepositoryNoFx = userRepositoryNoFx;
    }

    //это метод имплементированного интерфейса UserModelNoRx
    @Override
    public User getUser(){
        User user = userRepositoryNoFx.getUser();
        Log.i(TAG, "UserModelImplNoRx  getUser user.getName()=" + user.getName());
        return user;
    }

    @Override
    public boolean isLaunchMark() {
        Log.i(TAG, "UserModelImplNoRx  isLaunchMark");
        return userRepositoryNoFx.isLaunchMark();
    }
}
