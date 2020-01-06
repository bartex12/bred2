package ru.geekbrains.android.arch.mvp.model.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

//класс для определения того, нужно ли показывать надпись с предложением оценить приложение
public class LaunchRemoteDataSourceNoRx implements LaunchDataSourceNoRx {
    private static final String TAG = "33333";
    private Context context;
    private static final String NUMBER_OF_LAUNCH = "NUMBER_OF_LAUNCH";

    public LaunchRemoteDataSourceNoRx(Context context) {
        this.context = context;
    }

    @Override
    public boolean isLaunchMark() {

        SharedPreferences prefSetting = getDefaultSharedPreferences(context);
        int number = prefSetting.getInt(NUMBER_OF_LAUNCH,1);
        Log.d(TAG, "LaunchRemoteDataSourceNoRx isLaunchMark number = " + number);
        return number == 2 || ((number + 2) % 4 == 0);
    }
}
