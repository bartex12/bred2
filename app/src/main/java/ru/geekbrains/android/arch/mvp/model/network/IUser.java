package ru.geekbrains.android.arch.mvp.model.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.geekbrains.android.arch.mvp.model.data.models.ApiUser;

public interface IUser {

    //пришлось сделать List<ApiUser> так как в ответе - массив, а не объект
    @GET("users")
    Call<List<ApiUser>> getUsers();
}
