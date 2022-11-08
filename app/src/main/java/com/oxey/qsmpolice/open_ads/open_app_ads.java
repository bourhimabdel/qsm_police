package com.oxey.qsmpolice.open_ads;







import android.support.multidex.MultiDexApplication;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.oxey.qsmpolice.sharedpreferences;


public class open_app_ads extends MultiDexApplication {

    private static AppOpenManager appOpenManager;
    private static com.oxey.qsmpolice.sharedpreferences sharedpreferences;
    public int w = 0;
    public int str = 2;

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(
                this, initializationStatus -> {});
        appOpenManager = new AppOpenManager(this);


    }


}
