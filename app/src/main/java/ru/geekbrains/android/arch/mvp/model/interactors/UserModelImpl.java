package ru.geekbrains.android.arch.mvp.model.interactors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.repositories.UserRepository;

public class UserModelImpl implements UserModel {
    private final UserRepository userRepository;

    public UserModelImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Observable<User> getUser() {
        return userRepository.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
