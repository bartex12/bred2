package ru.geekbrains.android.arch.mvp.model.repositories;

import ru.geekbrains.android.arch.mvp.model.entities.User;

public interface UserRepositoryNoFx {
    User getUser();
    boolean isLaunchMark();
}
