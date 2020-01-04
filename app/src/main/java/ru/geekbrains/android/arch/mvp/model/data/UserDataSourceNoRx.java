package ru.geekbrains.android.arch.mvp.model.data;

import java.util.List;
import ru.geekbrains.android.arch.mvp.model.entities.User;

public interface UserDataSourceNoRx {
    List<User> getUsers();
}
