package ru.geekbrains.android.arch.mvp.model.data;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Response;
import ru.geekbrains.android.arch.mvp.model.data.models.ApiUser;
import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.network.IUser;
import ru.geekbrains.android.arch.mvp.model.network.JsonPlaceHolderApiNoRx;

public class UserRemoteDataSourceNoRx implements UserDataSourceNoRx {

    private static final String TAG = "33333";
    private final JsonPlaceHolderApiNoRx jsonPlaceHolderApiNoRx;

    public UserRemoteDataSourceNoRx(JsonPlaceHolderApiNoRx jsonPlaceHolderApiNoRx) {
        this.jsonPlaceHolderApiNoRx = jsonPlaceHolderApiNoRx;
    }

    @Override
    public List<User> getUsers() {
        Log.i(TAG, "UserRemoteDataSourceNoRx List<User> getUsers()");
        //TODO *************синхронизация нужна другая******************
        //НО почему то не получается сделать без CountDownLatch, если использовать
        //не execute(), а enqueue() в своём потоке и с обратным вызовом
        final CountDownLatch startSignal = new CountDownLatch(1);
        final List<User> users =  new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //создаём запрос с помощью ретрофита
                    IUser iUser = jsonPlaceHolderApiNoRx.getIUser();
                    Log.i(TAG, "UserRemoteDataSourceNoRx  iUser = " + iUser);
                    //выполняем запрос @GET("users") к серверу
                    Response<List<ApiUser>> response =iUser.getUsers().execute();
                    Log.i(TAG, "UserRemoteDataSourceNoRx  response = " + response);
                    //получаем модель данных как она есть на сервере
                    final List<ApiUser> apiUsers = response.body();
                    //теперь надо перейти к списку данных
                    for (int i=0; i<apiUsers.size(); i++) {
                        users.add(new User(apiUsers.get(i).getName(),
                                apiUsers.get(i).getUsername(),
                                apiUsers.get(i).getEmail(),
                                apiUsers.get(i).getPhone(),
                                apiUsers.get(i).getAddress().getCity()));
                    }
                    Log.i(TAG, "UserRemoteDataSourceNoRx  1 users.size() = " + users.size());
                    //разрешаем работу основного потока для возврата данных после окончания этого
                    startSignal.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            //заставляем основной поток ждать, пока не закончит работу другой поток - startSignal.countDown();
            startSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "UserRemoteDataSourceNoRx  2 users.size() = " + users.size());
        return users;
    }
}
