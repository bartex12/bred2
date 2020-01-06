package ru.geekbrains.android.arch.mvp.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonPlaceHolderApiNoRx {

    public IUserNoRx getIUser() {

        Gson gson = new GsonBuilder().create();

            Retrofit adapter = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return adapter.create(IUserNoRx.class);
        }
    }

