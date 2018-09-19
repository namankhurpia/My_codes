package com.namankhurpia.jalodhyaan;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Naman Khurpia on 21-03-2018.
 */

public class fireapp extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
