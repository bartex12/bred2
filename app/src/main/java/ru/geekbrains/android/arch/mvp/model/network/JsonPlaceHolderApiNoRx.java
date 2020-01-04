package ru.geekbrains.android.arch.mvp.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.android.arch.mvp.model.network.IUser;

public class JsonPlaceHolderApiNoRx {

    public IUser getIUser() {

        Gson gson = new GsonBuilder().create();

            Retrofit adapter = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return adapter.create(IUser.class);
        }
    }

