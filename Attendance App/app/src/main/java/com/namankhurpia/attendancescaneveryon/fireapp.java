package com.namankhurpia.attendancescaneveryon;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Naman Khurpia on 05-04-2018.
 */

public class fireapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
