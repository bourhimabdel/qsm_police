package com.oxey.qsmpolice;

import android.content.Context;
import android.os.Vibrator;



public class vibrateur {
    Context context;
    public vibrateur(Context context) {
        this.context = context;
    }

    public void vibrate(int millis){

            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(millis);
    }
}
