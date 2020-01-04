package ru.geekbrains.android.arch.mvp.presenter;

import android.util.Log;

import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.interactors.UserModelNoRx;

public class UserActivityPresenterImplNoRx implements UserPresenterNoRx {
    private static final String TAG = "33333";
    private final UserViewNoRx view;
    private final UserModelNoRx interactorNoRx;

    public UserActivityPresenterImplNoRx(UserViewNoRx view, UserModelNoRx interactorNoRx) {
        this.view = view;
        this.interactorNoRx = interactorNoRx;
    }

    @Override
    public void onStart() {
        view.showProgress();

        if(interactorNoRx.isLaunchMark()){
            view.showNumberLaunch();
            Log.i(TAG, "UserActivityPresenterImplNoRx  isLaunchMark = true ");
        }else {
            Log.i(TAG, "UserActivityPresenterImplNoRx  isLaunchMark = false ");
            view.showNo();
        }
        User user = interactorNoRx.getUser();
        Log.i(TAG, "UserActivityPresenterImplNoRx  getUser user.getName()=" + user.getName());
        //это не работает так как  в UserRemoteDataSourceNoRx  поток тормозмтся для синхронизации
        view.hideProgress();
        view.showUser(user);
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onUserAction() {
        // some logic
        view.showResult();
    }
}
