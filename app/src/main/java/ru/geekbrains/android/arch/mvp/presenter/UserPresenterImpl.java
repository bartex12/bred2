package ru.geekbrains.android.arch.mvp.presenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ru.geekbrains.android.arch.mvp.model.entities.User;
import ru.geekbrains.android.arch.mvp.model.interactors.UserModel;

/**
 * Реализация презентера
 */
public class UserPresenterImpl implements UserPresenter {

    // презентер работает и view и c моделью
    // в данном случае презентер имеет одинаковую продолжительность жизни с активити
    // поэтому мы можем хранить ссылку на view-активити в презентере и не очищать её
    // но если презентер должен жить между пересозданиями активити, то view нужно будет очищать
    private final UserView view;
    private final UserModel model;

    // disposable - мы храним RxJava подписку,
    // чтобы отменить запрос, если пользователь уйдёт с экрана
    private Disposable disposable;

    // View и Model внедряются в презентер как интерфейсы
    // презентер не знает их деталей реализации и не зависит от них напрямую
    public UserPresenterImpl(UserView view, UserModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onStart() {
        // при старте экрана показываем прогресс
        view.showProgress();
        // и начинаем получать данные у модели с помощью RxJava
        model.getUser().subscribe(new UserObserver());
    }

    @Override
    public void onStop() {
        // при уходе с экрана отменяем RxJava запрос
        // это важно, чтобы не делать лишней работы
        // и не допускать утечек памяти - подписка создает связь с моделью,
        // жизненный цикл которой может превышать жизненный цикл презентера
        if (disposable != null) {
            disposable.dispose();
        }
    }

    /**
     * презентер обрабатывает действия пользователя
     */
    @Override
    public void onUserAction() {
        // some logic

        // в ответ на действия пользователя презентер просит view отобразить результат
        view.showResult();
    }

    /**
     * RxJava Observer -- обрабатывает ошибки и результат, пришедшие из модели
     */
    private class UserObserver implements Observer<User> {

        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(User user) {
            // при получении данных от модели говорим view убрать прогресс и отобразить данные
            view.hideProgress();
            view.showUser(user);
        }

        @Override
        public void onError(Throwable e) {
            // просим view отобразить состояние ошибки
            view.hideProgress();
            view.showError();
        }

        @Override
        public void onComplete() {
            // empty
        }
    }
}
