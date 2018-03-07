package com.example.kailashpatel.smartkrishi;

import android.app.Application;
import android.content.Context;

import Helper.LocaleHelper;

/**
 * Created by User on 3/3/2018.
 */

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}
