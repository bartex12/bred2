package ru.geekbrains.android.arch.mvp.presenter;

import ru.geekbrains.android.arch.mvp.model.entities.User;

/**
 * Интерфейс View. Скрывает реализацию от презентера - убирает зависимости от
 * Android-фреймворка
 * методы View тоже не возвращают значения, только принимают
 */
public interface UserView {
    void showProgress();
    void hideProgress();
    void showUser(User user);
    void showError();
    void showResult();
}
