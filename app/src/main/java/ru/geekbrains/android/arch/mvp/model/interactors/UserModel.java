package ru.geekbrains.android.arch.mvp.model.interactors;

import io.reactivex.Observable;
import ru.geekbrains.android.arch.mvp.model.entities.User;

public interface UserModel {
    Observable<User> getUser();
}
