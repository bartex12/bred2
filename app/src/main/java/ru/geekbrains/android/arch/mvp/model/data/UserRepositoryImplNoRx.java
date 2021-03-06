package ru.geekbrains.android.arch.mvp.model.data;

import android.util.Log;

import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.repositories.UserRepositoryNoFx;

public class UserRepositoryImplNoRx implements UserRepositoryNoFx {
    private static final String TAG = "33333";

    //репозиторий получает данные из источника данных UserDataSource
    private final UserDataSourceNoRx userDataSourceNoRx;
    private final LaunchDataSourceNoRx launchDataSourceNoRx;

    public UserRepositoryImplNoRx(UserDataSourceNoRx userDataSourceNoRx, LaunchDataSourceNoRx launchDataSourceNoRx) {
        this.userDataSourceNoRx = userDataSourceNoRx;
        this.launchDataSourceNoRx = launchDataSourceNoRx;
    }

    @Override
    public User getUser() {
        User user = userDataSourceNoRx.getUsers().get(0);
        Log.i(TAG, "UserRepositoryImplNoRx  getUser user.getName()=" + user.getName());
        return user;
    }

    @Override
    public boolean isLaunchMark() {
        Log.i(TAG, "UserRepositoryImplNoRx  isLaunchMark");
        return launchDataSourceNoRx.isLaunchMark();
    }
}
