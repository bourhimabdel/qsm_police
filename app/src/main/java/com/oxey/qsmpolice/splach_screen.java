package com.oxey.qsmpolice;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.os.ConfigurationCompat;

import com.oxey.qsmpolice.Remind_user.remind_Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class splach_screen extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Splash_screen);
        super.onCreate(savedInstanceState);


        final String PREFS_NAME = "damas_App_PrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

        if (settings.getBoolean("damas_App_first_time", true)) {
            startService(new Intent(this, remind_Service.class));
            settings.edit().putBoolean("damas_App_first_time", false).apply();
        }

       startActivity(new Intent( this,Activity_principal.class));
       this.finish();
    }
}
