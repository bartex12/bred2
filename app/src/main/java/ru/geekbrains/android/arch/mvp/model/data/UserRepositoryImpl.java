package ru.geekbrains.android.arch.mvp.model.data;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.network.JsonPlaceHolderApi;
import ru.geekbrains.android.arch.mvp.model.repositories.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    private final UserDadaSource remoteDataSource;

    public UserRepositoryImpl(JsonPlaceHolderApi jsonPlaceHolderApi) {
        remoteDataSource = new UserRemoteDataSource(jsonPlaceHolderApi);
    }

    @Override
    public Observable<User> getUser() {
        return remoteDataSource.getUsers()
                .map(new Function<List<User>, User>() {
            @Override
            public User apply(List<User> users) throws Exception {
                return users.get(0);
            }
        });
    }
}
