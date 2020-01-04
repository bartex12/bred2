package ru.geekbrains.android.arch.mvp.model.repositories;

import io.reactivex.Observable;
import ru.geekbrains.android.arch.mvp.model.entities.User;

public interface UserRepository {
    Observable<User> getUser();
}
