package ru.geekbrains.android.arch.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gregory.arch_demo.R;

import ru.geekbrains.android.arch.mvp.model.data.UserRepositoryImpl;
import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.interactors.UserModel;
import ru.geekbrains.android.arch.mvp.model.interactors.UserModelImpl;
import ru.geekbrains.android.arch.mvp.model.network.JsonPlaceHolderApi;
import ru.geekbrains.android.arch.mvp.model.repositories.UserRepository;
import ru.geekbrains.android.arch.mvp.presenter.UserPresenter;
import ru.geekbrains.android.arch.mvp.presenter.UserPresenterImpl;
import ru.geekbrains.android.arch.mvp.presenter.UserView;

public class UserActivity extends AppCompatActivity implements UserView {

    private static final String TAG = "UserActivity";
    UserPresenter presenter;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // действия пользователя передаются в презентер
                presenter.onUserAction();
            }
        });

        createPresenter();
    }

    /**
     * Создание презентера
     * Можно вынести это в DI, фабрики и т.д.
     */
    private void createPresenter() {
        JsonPlaceHolderApi jsonPlaceHolderApi = new JsonPlaceHolderApi();
        UserRepository userRepository = new UserRepositoryImpl(jsonPlaceHolderApi);
        UserModel userModel = new UserModelImpl(userRepository);
        presenter = new UserPresenterImpl(this, userModel);
    }

    @Override
    public void onStart() {
        super.onStart();

        presenter.onStart();
    }

    /**
     * View выполняет максимально простые команды презентера и содержит минимум логики
     */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
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
        textView.setText("Error!");
    }

    @Override
    public void showResult() {
        textView.setText("Result");
    }
}
