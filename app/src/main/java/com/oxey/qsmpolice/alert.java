package com.oxey.qsmpolice;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.text.HtmlCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

public class alert {

    public alert() {
    }

    public void show_gude_to_user(Context context, AppCompatActivity activity){
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetDialogKeyboardTheme);
        View snackView = activity.getLayoutInflater().inflate(R.layout.advice, null);


        final Button button1=snackView.findViewById(R.id.btn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,exam.class));
            }
        });

        mBottomSheetDialog.setContentView(snackView);
        mBottomSheetDialog.show();
    }

    public void show_advice_to_user(Context context, AppCompatActivity activity){
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetDialogKeyboardTheme);
        View snackView = activity.getLayoutInflater().inflate(R.layout.gide, null);
        mBottomSheetDialog.setContentView(snackView);
        mBottomSheetDialog.show();
    }

    @SuppressLint("DefaultLocale")
    public void oui_non_alert(Context context,go_back go_back){
        LayoutInflater factory = LayoutInflater.from(context);

        //text_entry is an Layout XML file containing two text field to display in alert dialog
        final View textEntryView = factory.inflate(R.layout.oui_non_alert, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(textEntryView);
        alert.setCancelable(true);
        AlertDialog alertDialog=alert.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        final Button btn_oui=textEntryView.findViewById(R.id.btn_oui);
        final Button btn_non=textEntryView.findViewById(R.id.btn_non);


        btn_oui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_back.onCallBack(0);
                alertDialog.cancel();
            }
        });

        btn_non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_back.onCallBack(1);
                alertDialog.cancel();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    public void match_end(int score,Context context,go_back go_back){
        LayoutInflater factory = LayoutInflater.from(context);

        //text_entry is an Layout XML file containing two text field to display in alert dialog
        final View textEntryView = factory.inflate(R.layout.score, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(textEntryView);
        alert.setCancelable(false);
        AlertDialog alertDialog=alert.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        final Button btn_oui=textEntryView.findViewById(R.id.btn_oui);
        final Button btn_non=textEntryView.findViewById(R.id.btn_non);
        LottieAnimationView lottieAnimationView=textEntryView.findViewById(R.id.lte);
        TextView title=textEntryView.findViewById(R.id.title);

        if(score>=10){
            title.setText("أجبت على "+score+" من أصل 20 سؤال \nاذا أنت ناجح في الاختبار");
            lottieAnimationView.setAnimation(R.raw.happy);
        }else {
            title.setText("أجبت على "+score+" من أصل 20 سؤال");
            btn_oui.setText("حاول مرة اخرى");
            lottieAnimationView.setAnimation(R.raw.sad);
        }


        btn_oui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_back.onCallBack(0);
                alertDialog.cancel();
            }
        });

        btn_non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_back.onCallBack(1);
                alertDialog.cancel();
            }
        });
    }

    public interface go_back{
        void onCallBack(int action);
    }

    public void show_preparation(Context context, AppCompatActivity activity){
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetDialogKeyboardTheme);
        View snackView = activity.getLayoutInflater().inflate(R.layout.preparation, null);

        final TextView textView=snackView.findViewById(R.id.txt_qustion_name);

        ArrayList<data> tous=new convert_json_file().get_data(context);

        int a=1;
        String m = "";
        for (data d:tous){
            m+="<b>" + (a+". ") + "</b>";
            m+=d.getQustion()+" ";
            m+="<b> <font color=\"#ffffff\">"+ d.getList_choix().get(0)+"</font> </b>";
            m+="<br>";
            a++;
        }

        textView.setText(HtmlCompat.fromHtml(m, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        mBottomSheetDialog.setContentView(snackView);
        mBottomSheetDialog.show();
    }




    public void remind_user(Context context){

        String channel =context.getResources().getString(R.string.channel_remind_user);
        int number=1;

        data d=get_data(context);
        String m="";
        m+=d.getQustion()+" ";
        m+=d.getList_choix().get(0);


        String title="معلومة اليوم";

        push(title, m, number, channel,context);
    }

    private data get_data(Context context) {
        ArrayList<data> tous=new convert_json_file().get_data(context);

        data[] tab_data=new data[tous.size()];
        tous.toArray(tab_data);

        Random rand = new Random();

        for (int i = 0; i < tab_data.length; i++) {
            int randomIndexToSwap = rand.nextInt(tab_data.length);
            data temp = tab_data[randomIndexToSwap];
            tab_data[randomIndexToSwap] = tab_data[i];
            tab_data[i] = temp;
        }
        return tab_data[0];
    }


    private void push(String title, String description, int number, String chanel_name,Context context){

        Intent intent;
        PendingIntent pendingIntent;
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        intent = new Intent(context, splach_screen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(chanel_name, chanel_name, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        Notification notification =
                new NotificationCompat.Builder(context)
                        .setContentTitle(title)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(description))
                        .setContentText(description)
                        .setNumber(number)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setChannelId(chanel_name)
                        .build();


        //notification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear  the notification
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
        notification.defaults |= Notification.DEFAULT_VIBRATE;//Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound


        notificationManager.notify(number,notification);
    }

}
