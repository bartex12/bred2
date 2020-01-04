package ru.geekbrains.android.arch.mvp.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gregory.arch_demo.R;

import java.io.IOException;
import java.util.Locale;

import ru.geekbrains.android.arch.mvp.model.data.LaunchDataSource;
import ru.geekbrains.android.arch.mvp.model.data.LaunchRemoteDataSource;
import ru.geekbrains.android.arch.mvp.model.data.UserDataSourceNoRx;
import ru.geekbrains.android.arch.mvp.model.data.UserRemoteDataSourceNoRx;
import ru.geekbrains.android.arch.mvp.model.data.UserRepositoryImplNoRx;
import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.interactors.UserModelImplNoRx;
import ru.geekbrains.android.arch.mvp.model.interactors.UserModelNoRx;
import ru.geekbrains.android.arch.mvp.model.network.JsonPlaceHolderApiNoRx;
import ru.geekbrains.android.arch.mvp.model.repositories.UserRepositoryNoFx;
import ru.geekbrains.android.arch.mvp.presenter.UserActivityPresenterImplNoRx;
import ru.geekbrains.android.arch.mvp.presenter.UserPresenterNoRx;
import ru.geekbrains.android.arch.mvp.presenter.UserViewNoRx;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class UserActivity extends AppCompatActivity implements UserViewNoRx {

    private static final String TAG = "UserActivity";
    private UserPresenterNoRx presenter;
    private ProgressBar progressBar;
    private TextView textView;
    private TextView textView1;
    private int number =1;
    public static final String NUMBER_OF_LAUNCH = "NUMBER_OF_LAUNCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.text);
        textView1 = findViewById(R.id.text1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // действия пользователя передаются в презентер
                presenter.onUserAction();
            }
        });
        // извлекаем number, чтобы в onStop() записать правильное значение
        SharedPreferences prefSetting = getDefaultSharedPreferences(this);
        number = prefSetting.getInt(NUMBER_OF_LAUNCH,1);

        createPresenter();
    }
    /**
     * Создание презентера
     * Можно вынести это в DI, фабрики и т.д.
     */
    private void createPresenter() {

        JsonPlaceHolderApiNoRx jsonPlaceHolderApiNoRx = new JsonPlaceHolderApiNoRx();
        UserDataSourceNoRx userDataSourceNoRx = new UserRemoteDataSourceNoRx(jsonPlaceHolderApiNoRx);
        LaunchDataSource launchDataSource = new LaunchRemoteDataSource(this);
        UserRepositoryNoFx userRepositoryNoFx = new UserRepositoryImplNoRx(userDataSourceNoRx,launchDataSource);
        UserModelNoRx userInteractorNoRx = new UserModelImplNoRx(userRepositoryNoFx);
        presenter = new UserActivityPresenterImplNoRx(this, userInteractorNoRx);
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            presenter.onStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "UserActivity  onStop");
        //пишем в Preferences ++number
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putInt(NUMBER_OF_LAUNCH, ++number)
                .apply();

        presenter.onStop();
    }

    /**
     * View выполняет максимально простые команды презентера и содержит минимум логики
     */
    @Override
    public void showProgress() {
        //
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //
        progressBar.setVisibility(View.GONE);
    }

    /**
     * здесь презентер не знает, каким образом View будет отображать данные
     * но можно сделать более подробные методы, например showUserName()
     */
    @Override
    public void showUser(User user) {
        Log.i(TAG, "showUser " + user);
        textView.setText(user.getName());
    }

    @Override
    public void showError() {
        textView.setText(getResources().getString(R.string.Error));
    }

    @Override
    public void showResult() {
        textView.setText(getResources().getString(R.string.Result));
    }

    @Override
    public void showNumberLaunch() {
        textView1.setText(String.format(Locale.ENGLISH,"Launch = %d Оценить.", number));
        Log.i(TAG, "UserActivity  showNumberLaunch");
    }

    @Override
    public void showNo() {
        Log.i(TAG, "UserActivity  showNo");
        textView1.setText("*****");
    }

}
