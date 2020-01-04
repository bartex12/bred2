package ru.geekbrains.android.arch.mvp.presenter;

import java.io.IOException;

public interface UserPresenterNoRx {

    void onStart() throws IOException;
    void onStop();
    void onUserAction();
}
