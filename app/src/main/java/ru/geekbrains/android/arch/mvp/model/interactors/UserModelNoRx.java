package ru.geekbrains.android.arch.mvp.model.interactors;

import ru.geekbrains.android.arch.mvp.model.entities.User;

public interface UserModelNoRx {
    User getUser();
    boolean isLaunchMark();
}
