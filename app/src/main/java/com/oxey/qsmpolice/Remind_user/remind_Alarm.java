package com.oxey.qsmpolice.Remind_user;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;


import com.oxey.qsmpolice.alert;

import java.util.Calendar;

public class remind_Alarm extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myapp:mywakelocktag");

        wl.acquire(10*60*1000L /*10 minutes*/);

        // Put here YOUR code.
        //new noti_upload_data(context).notification_morning();
        Log.e("reminde","up");
        new alert().remind_user(context);

        wl.release();
    }

    public void setReminder(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, remind_Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,9);
        calendar.set(Calendar.MINUTE,30);


       // am.setRepeating(AlarmManager.RTC_WAKEUP,10000, AlarmManager.INTERVAL_DAY, pi);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi); // Millisec * Second * Minute
    }


}
