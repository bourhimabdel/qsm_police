package com.oxey.qsmpolice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new alert().show_gude_to_user(Activity_principal.this,Activity_principal.this);
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new alert().show_advice_to_user(Activity_principal.this,Activity_principal.this);
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new alert().show_preparation(Activity_principal.this,Activity_principal.this);
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String share="هنا سوف تجد كافة الاسئلة التي سوف تساعدك في اجتياز المباراة بنجاح";
                share +="\n\n";
                share+="الرابط يأخدك لمتجر بلاي ستور";
                share+="\n\n";
                share+="https://play.google.com/store/apps/details?id=" + getPackageName();

                ShareCompat.IntentBuilder
                        .from(Activity_principal.this)
                        .setText(share)
                        .setType("text/plain")
                        .setChooserTitle(R.string.app_name)
                        .startChooser();
            }
        });
    }
}