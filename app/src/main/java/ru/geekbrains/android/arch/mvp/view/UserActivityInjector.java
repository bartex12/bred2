package ru.geekbrains.android.arch.mvp.view;

import ru.geekbrains.android.arch.mvp.model.data.LaunchDataSource;
import ru.geekbrains.android.arch.mvp.model.data.LaunchRemoteDataSource;
import ru.geekbrains.android.arch.mvp.model.data.UserDataSourceNoRx;
import ru.geekbrains.android.arch.mvp.model.data.UserRemoteDataSourceNoRx;
import ru.geekbrains.android.arch.mvp.model.data.UserRepositoryImplNoRx;
import ru.geekbrains.android.arch.mvp.model.interactors.UserModelImplNoRx;
import ru.geekbrains.android.arch.mvp.model.interactors.UserModelNoRx;
import ru.geekbrains.android.arch.mvp.model.network.JsonPlaceHolderApiNoRx;
import ru.geekbrains.android.arch.mvp.model.repositories.UserRepositoryNoFx;
import ru.geekbrains.android.arch.mvp.presenter.UserActivityPresenterImplNoRx;
import ru.geekbrains.android.arch.mvp.presenter.UserPresenterNoRx;

public class UserActivityInjector {

    public void inject(UserActivity userActivity){
        JsonPlaceHolderApiNoRx jsonPlaceHolderApiNoRx = new JsonPlaceHolderApiNoRx();
        UserDataSourceNoRx userDataSourceNoRx = new UserRemoteDataSourceNoRx(jsonPlaceHolderApiNoRx);
        LaunchDataSource launchDataSource = new LaunchRemoteDataSource(userActivity);
        UserRepositoryNoFx userRepositoryNoFx = new UserRepositoryImplNoRx(userDataSourceNoRx,launchDataSource);
        UserModelNoRx userInteractorNoRx = new UserModelImplNoRx(userRepositoryNoFx);
        UserPresenterNoRx presenter = new UserActivityPresenterImplNoRx(userActivity, userInteractorNoRx);

        userActivity.setPresenter(presenter);
    }
}