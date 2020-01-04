package ru.geekbrains.android.arch.mvp.presenter;

import ru.geekbrains.android.arch.mvp.model.entities.User;

public interface UserViewNoRx {
    void showProgress();
    void hideProgress();
    void showUser(User user);
    void showError();
    void showResult();
    //добавлено для отображения 2-6-10... запуска
    void showNumberLaunch();
    void showNo();
}
