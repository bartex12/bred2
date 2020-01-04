package ru.geekbrains.android.arch.mvp.presenter;

/**
 * Презентер работающей с View User
 * В нашем примере это активити, но для презентера это не важно
 */
public interface UserPresenter {
    // в специфике Android-приложений у презентеров часто делают методы жизненного цикла
    void onStart();
    void onStop();

    // презентер обрабатывает действия пользователя
    // методы презентера не могут возвращать результат, только принимать параметры
    void onUserAction();
}
