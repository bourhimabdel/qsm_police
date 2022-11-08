package com.oxey.qsmpolice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class exam extends AppCompatActivity  {

    private TextView txt_q_1,txt_q_name_1,txt_q_2,txt_q_name_2,txt_q_3,txt_q_name_3,txt_q_4,txt_q_name_4,txt_q_5,txt_q_name_5;
    private Button btn_valide,btn_close;
    private LottieAnimationView lte_1,lte_2,lte_3,lte_4,lte_5,load;
    private ConstraintLayout view_1,view_2,view_3,view_4,view_5;
    private Typewriter txt_q_name,txt_q_number;

    private int pointer,qestion_number=0,score=0;
    private ArrayList<data> all_object;
    private ArrayList<data> tous;
    private data current_object_data;
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);


        initial_proprite();
        initial_view();
        click_listner();
        up_data();
        remplire_data();




    }



    private void initial_view() {
        txt_q_number=findViewById(R.id.txt_qustion_number);
        txt_q_name=findViewById(R.id.txt_qustion_txt);
        txt_q_1=findViewById(R.id.btn_choix_1);
        txt_q_name_1=findViewById(R.id.txt_choix_num_1);
        txt_q_2=findViewById(R.id.btn_choix_2);
        txt_q_name_2=findViewById(R.id.txt_choix_num_2);
        txt_q_3=findViewById(R.id.btn_choix_3);
        txt_q_name_3=findViewById(R.id.txt_choix_num_3);
        txt_q_4=findViewById(R.id.btn_choix_4);
        txt_q_name_4=findViewById(R.id.txt_choix_num_4);
        txt_q_5=findViewById(R.id.btn_choix_5);
        txt_q_name_5=findViewById(R.id.txt_choix_num_5);
        btn_valide=findViewById(R.id.btn);
        lte_1=findViewById(R.id.lte_q_1);
        lte_2=findViewById(R.id.lte_q_2);
        lte_3=findViewById(R.id.lte_q_3);
        lte_4=findViewById(R.id.lte_q_4);
        lte_5=findViewById(R.id.lte_q_5);
        view_1=findViewById(R.id.view_q_1);
        view_2=findViewById(R.id.view_q_2);
        view_3=findViewById(R.id.view_q_3);
        view_4=findViewById(R.id.view_q_4);
        view_5=findViewById(R.id.view_q_5);
        btn_close=findViewById(R.id.btn_close);
        adView=findViewById(R.id.banner_ads);
        load=findViewById(R.id.lte_load);
    }


    private void create_ads(laoded laoded){

        load.setVisibility(View.VISIBLE);
        load.playAnimation();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                adView.setVisibility(View.GONE);
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                if(qestion_number==1) {
                    load.setVisibility(View.GONE);
                    load.cancelAnimation();
                    laoded.onCallBack();
                }
                super.onAdFailedToLoad(loadAdError);
            }


            @Override
            public void onAdLoaded() {
                if(qestion_number==1) {
                    load.setVisibility(View.GONE);
                    load.cancelAnimation();
                    laoded.onCallBack();
                    adView.setVisibility(View.VISIBLE);
                }
                super.onAdLoaded();
            }

        });

        InterstitialAd.load(this, getString(R.string.insteteril_ad), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        super.onAdLoaded(interstitialAd);

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                    }
                });


    }

    private void show_ads(){
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d("InterstitialAd", "Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    Log.d("InterstitialAd", "Ad dismissed fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                    Log.e("InterstitialAd", "Ad failed to show fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d("InterstitialAd", "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d("InterstitialAd", "Ad showed fullscreen content.");
                }
            });
            mInterstitialAd.show(exam.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }




    private void exam_end() {

        show_ads();
        adView.destroy();
        if(score>=10)
            mark_used();

        new alert().match_end(score, exam.this, new alert.go_back() {
            @Override
            public void onCallBack(int action) {
                if(action==0){
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }else {
                    finish();
                }
            }
        });
    }

    private void remplire_data() {


        int len=current_object_data.getQustion().length()*30;

        txt_q_number.setCharacterDelay(30);

        String str="السؤال رقم ";
        str+="<b> <font color=\"#ffffff\">" + (qestion_number+1) + "</font></b>";
        str+=" من ";
        str+="<b> <font color=\"#ffffff\">" + 20 + "</font></b>";
        str+=" : ";

        txt_q_number.animateText(HtmlCompat.fromHtml(str, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        sound sound=new sound();
        sound.msg(exam.this);

        new Handler().postDelayed(() -> {

            txt_q_name.setCharacterDelay(30);
            txt_q_name.animateText(current_object_data.getQustion());
            new Handler().postDelayed(() -> {

                sound.stop_clavier();
                anim_this_view(view_1);
                new Handler().postDelayed(() -> {

                    anim_this_view(view_2);
                    new Handler().postDelayed(() -> {

                        anim_this_view(view_3);
                        new Handler().postDelayed(() -> {

                            anim_this_view(view_4);
                            new Handler().postDelayed(() -> {

                                anim_this_view(view_5);
                                new Handler().postDelayed(() -> {

                                    anim_this_view(btn_valide);

                                    new Handler().postDelayed(() -> {

                                    }, 400);
                                }, 400);
                            }, 400);
                        }, 400);
                    }, 400);
                }, 400);
            }, len);
        }, 700);


        txt_q_1.setText(current_object_data.getList_choix().get(0));
        txt_q_2.setText(current_object_data.getList_choix().get(1));
        txt_q_3.setText(current_object_data.getList_choix().get(2));
        txt_q_4.setText(current_object_data.getList_choix().get(3));
        txt_q_5.setText(current_object_data.getList_choix().get(4));


    }

    private void initial_proprite() {
        all_object=new ArrayList<>();
    }

    private void up_data() {
        all_object.clear();

        tous=new convert_json_file().get_data(exam.this);

        ArrayList<Integer> used_element=get_used_elemen();
        ArrayList<data> used_object=new ArrayList<>();


        for (int i:used_element) {
            used_object.add(tous.get(i-1));
        }

        if(!(used_element.size()>80))
            tous.removeAll(used_object);
        else
            new sharedpreferences(exam.this).put_used_element(new HashSet<String>());


        data[] tab_data=new data[tous.size()];
        tous.toArray(tab_data);

        Random rand = new Random();

        for (int i = 0; i < tab_data.length; i++) {
            int randomIndexToSwap = rand.nextInt(tab_data.length);
            data temp = tab_data[randomIndexToSwap];
            tab_data[randomIndexToSwap] = tab_data[i];
            tab_data[i] = temp;
        }

        for (int i = 0; i < 20; i++) {
            all_object.add(tab_data[i]);
        }



        current_object_data=all_object.get(qestion_number);
        melange_th_choix();


    }

    private ArrayList<Integer> get_used_elemen() {
        Set<String> m= new sharedpreferences(exam.this).get_used_element();
        ArrayList<Integer> c=new ArrayList<>();

        for (String d:m){
            try {
                c.add(Integer.parseInt(d));
            }catch (NumberFormatException n){}
        }

        return c;
    }

    private void mark_used() {

        Set<String> m= new sharedpreferences(exam.this).get_used_element();
        for (data d:all_object){
            m.add(d.getId()+"");
        }
        new sharedpreferences(exam.this).put_used_element(m);

        ArrayList<Integer> l=get_used_elemen();
        Collections.sort(l);
        String c="";
        for (int i:l){
            c+=i+"\n";
        }
        Log.e("used_element",c);
    }


    private void click_listner() {

        txt_q_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pointer!=1){
                    deselect_other();
                    select_number_color(txt_q_name_1);
                    select_q_color(txt_q_1);
                    txt_color_to_white(txt_q_name_1);
                    txt_color_to_black(txt_q_1);

                    btn_enabled(1);

                }else {
                    deselect_number_color(txt_q_name_1);
                    deselect_q_color(txt_q_1);
                    txt_color_to_black(txt_q_name_1);
                    txt_color_to_white(txt_q_1);

                    btn_desabled();

                }

            }
        });

        txt_q_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pointer!=2){
                    deselect_other();
                    select_number_color(txt_q_name_2);
                    select_q_color(view);
                    txt_color_to_white(txt_q_name_2);
                    txt_color_to_black(txt_q_2);
                    btn_enabled(2);
                }else {
                    deselect_number_color(txt_q_name_2);
                    deselect_q_color(view);
                    txt_color_to_black(txt_q_name_2);
                    txt_color_to_white(txt_q_2);
                    btn_desabled();
                }
            }
        });

        txt_q_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pointer!=3){
                    deselect_other();
                    select_number_color(txt_q_name_3);
                    select_q_color(view);
                    txt_color_to_white(txt_q_name_3);
                    txt_color_to_black(txt_q_3);
                    btn_enabled(3);
                }else {
                    deselect_number_color(txt_q_name_3);
                    deselect_q_color(view);
                    txt_color_to_black(txt_q_name_3);
                    txt_color_to_white(txt_q_3);
                    btn_desabled();
                }
            }
        });

        txt_q_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pointer!=4){
                    deselect_other();
                    select_number_color(txt_q_name_4);
                    select_q_color(view);
                    txt_color_to_white(txt_q_name_4);
                    txt_color_to_black(txt_q_4);
                    btn_enabled(4);
                }else {
                    deselect_number_color(txt_q_name_4);
                    deselect_q_color(view);
                    txt_color_to_black(txt_q_name_4);
                    txt_color_to_white(txt_q_4);
                    btn_desabled();
                }
            }
        });

        txt_q_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pointer!=5){
                    deselect_other();
                    select_number_color(txt_q_name_5);
                    select_q_color(view);
                    txt_color_to_white(txt_q_name_5);
                    txt_color_to_black(txt_q_5);
                    btn_enabled(5);
                }else {
                    deselect_number_color(txt_q_name_5);
                    deselect_q_color(view);
                    txt_color_to_black(txt_q_name_5);
                    txt_color_to_white(txt_q_5);
                    btn_desabled();
                }
            }
        });


        txt_q_name_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_q_1.performClick();
            }
        });


        txt_q_name_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_q_2.performClick();
            }
        });


        txt_q_name_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_q_3.performClick();
            }
        });

        txt_q_name_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_q_4.performClick();
            }
        });

        txt_q_name_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_q_5.performClick();
            }
        });

        btn_valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pointer!=0)
                    valider_repence();
                else
                    if(qestion_number<19)
                        to_next_qustion();
                    else {
                        view_1.setVisibility(View.INVISIBLE);
                        view_2.setVisibility(View.INVISIBLE);
                        view_3.setVisibility(View.INVISIBLE);
                        view_4.setVisibility(View.INVISIBLE);
                        view_5.setVisibility(View.INVISIBLE);
                        btn_valide.setVisibility(View.INVISIBLE);
                        btn_close.setVisibility(View.INVISIBLE);
                        txt_q_number.setText("");
                        txt_q_name.setText("");
                        exam_end();
                    }
            }
        });
        
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit_alert();
            }
        });
    }

    private void to_next_qustion() {

        qestion_number++;

        view_1.setVisibility(View.INVISIBLE);
        view_2.setVisibility(View.INVISIBLE);
        view_3.setVisibility(View.INVISIBLE);
        view_4.setVisibility(View.INVISIBLE);
        view_5.setVisibility(View.INVISIBLE);
        lte_1.setVisibility(View.INVISIBLE);
        lte_2.setVisibility(View.INVISIBLE);
        lte_3.setVisibility(View.INVISIBLE);
        lte_4.setVisibility(View.INVISIBLE);
        lte_5.setVisibility(View.INVISIBLE);


        btn_valide.setVisibility(View.INVISIBLE);
        txt_q_number.setText("");
        txt_q_name.setText("");
        btn_valide.setText("إختر الإجابة الصحيحة");
        btn_valide.setEnabled(false);



        prepare_choix_style(txt_q_1,txt_q_name_1);
        prepare_choix_style(txt_q_2,txt_q_name_2);
        prepare_choix_style(txt_q_3,txt_q_name_3);
        prepare_choix_style(txt_q_4,txt_q_name_4);
        prepare_choix_style(txt_q_5,txt_q_name_5);

        txt_q_1.setEnabled(true);
        txt_q_2.setEnabled(true);
        txt_q_3.setEnabled(true);
        txt_q_4.setEnabled(true);
        txt_q_5.setEnabled(true);
        txt_q_name_1.setEnabled(true);
        txt_q_name_2.setEnabled(true);
        txt_q_name_3.setEnabled(true);
        txt_q_name_4.setEnabled(true);
        txt_q_name_5.setEnabled(true);


        current_object_data=all_object.get(qestion_number);

       melange_th_choix();

        if(qestion_number==1){
            btn_close.setVisibility(View.GONE);
            create_ads(new laoded() {
                @Override
                public void onCallBack() {

                    btn_close.setVisibility(View.VISIBLE);
                    remplire_data();

                }
            });
        }else
            remplire_data();

    }

    private void melange_th_choix() {
        String rigth_choice=current_object_data.getList_choix().get(0);

        ArrayList<String> choices=current_object_data.getList_choix();
        String[] tab_choises=new String[5];
        choices.toArray(tab_choises);

        Random rand2 = new Random();

        for (int i = 0; i < tab_choises.length; i++) {
            int randomIndexToSwap = rand2.nextInt(tab_choises.length);
            String temp = tab_choises[randomIndexToSwap];
            tab_choises[randomIndexToSwap] = tab_choises[i];
            tab_choises[i] = temp;
        }

        ArrayList<String> m=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            m.add(tab_choises[i]);
            if(tab_choises[i].equals(rigth_choice))
                current_object_data.setExact_choix(i+1);
        }
        current_object_data.setList_choix(m);
    }

    private void prepare_choix_style(TextView textView,TextView view) {
        deselect_number_color(view);
        deselect_q_color(textView);
        txt_color_to_black(view);
        txt_color_to_white(textView);
    }

    private void exit_alert() {

        new alert().oui_non_alert(exam.this, new alert.go_back() {
            @Override
            public void onCallBack(int action) {
                if(action==0){
                    finish();
                }
            }
        });
    }

    private void btn_desabled() {
        pointer=0;
        btn_valide.setEnabled(false);
        btn_valide.setText("إختر الإجابة الصحيحة");
    }

    private void btn_enabled(int po) {
        pointer=po;
        btn_valide.setEnabled(true);
        btn_valide.setText("تأكيد الاختيار");
    }

    private void valider_repence() {


        txt_q_1.setEnabled(false);
        txt_q_2.setEnabled(false);
        txt_q_3.setEnabled(false);
        txt_q_4.setEnabled(false);
        txt_q_5.setEnabled(false);
        txt_q_name_1.setEnabled(false);
        txt_q_name_2.setEnabled(false);
        txt_q_name_3.setEnabled(false);
        txt_q_name_4.setEnabled(false);
        txt_q_name_5.setEnabled(false);


        this_is_mute(txt_q_1);
        this_is_mute(txt_q_2);
        this_is_mute(txt_q_3);
        this_is_mute(txt_q_4);
        this_is_mute(txt_q_5);

        deselect_number_color(txt_q_name_1);
        deselect_number_color(txt_q_name_2);
        deselect_number_color(txt_q_name_3);
        deselect_number_color(txt_q_name_4);
        deselect_number_color(txt_q_name_5);


        txt_color_to_black(txt_q_1);
        txt_color_to_black(txt_q_2);
        txt_color_to_black(txt_q_3);
        txt_color_to_black(txt_q_4);
        txt_color_to_black(txt_q_5);
        txt_color_to_black(txt_q_name_1);
        txt_color_to_black(txt_q_name_2);
        txt_color_to_black(txt_q_name_3);
        txt_color_to_black(txt_q_name_4);
        txt_color_to_black(txt_q_name_5);

        if(pointer==current_object_data.getExact_choix()) {

            score++;
            new sound().collect(exam.this);
            switch (pointer) {
                case 1:
                    this_is_vrai(txt_q_1, lte_1);
                    break;
                case 2:
                    this_is_vrai(txt_q_2, lte_2);
                    break;
                case 3:
                    this_is_vrai(txt_q_3, lte_3);
                    break;
                case 4:
                    this_is_vrai(txt_q_4, lte_4);
                    break;
                case 5:
                    this_is_vrai(txt_q_5, lte_5);
                    break;
            }

        }else {

            new vibrateur(exam.this).vibrate(1000);

            switch (current_object_data.getExact_choix()) {
                case 1:
                    this_is_vrai(txt_q_1, lte_1);

                    break;
                case 2:
                    this_is_vrai(txt_q_2, lte_2);
                    break;
                case 3:
                    this_is_vrai(txt_q_3, lte_3);
                    break;
                case 4:
                    this_is_vrai(txt_q_4, lte_4);
                    break;
                case 5:
                    this_is_vrai(txt_q_5, lte_5);
                    break;
            }
            View view;
            switch (pointer) {
                case 1:
                    this_is_faut(txt_q_1, lte_1);
                    view=view_1;
                    break;
                case 2:
                    this_is_faut(txt_q_2, lte_2);
                    view=view_2;
                    break;
                case 3:
                    this_is_faut(txt_q_3, lte_3);
                    view=view_3;
                    break;
                case 4:
                    this_is_faut(txt_q_4, lte_4);
                    view=view_4;
                    break;
                case 5:
                    this_is_faut(txt_q_5, lte_5);
                    view=view_5;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + pointer);
            }
            
            rotate(view);
        }

        pointer=0;
        if(qestion_number<19)
            btn_valide.setText("إضغط للمرور لسؤال التالي");
        else
            btn_valide.setText("الإطلاع على النتيجة");
    }

    private void deselect_other() {
        switch (pointer){
            case 1:
                txt_q_1.performClick();
                break;
            case 2:
                txt_q_2.performClick();
                break;
            case 3:
                txt_q_3.performClick();
                break;
            case 4:
                txt_q_4.performClick();
                break;
            case 5:
                txt_q_5.performClick();
                break;
        }
    }



    private void select_number_color(View view) {
        view.setBackground(ContextCompat.getDrawable(exam.this,R.drawable.back_circle_select));
    }

    private void deselect_number_color(View view) {
        view.setBackground(ContextCompat.getDrawable(exam.this,R.drawable.back_circle));
    }

    private void select_q_color(View view) {
        view.setBackground(ContextCompat.getDrawable(exam.this,R.drawable.btn_selector_select));
    }

    private void deselect_q_color(View view) {
        view.setBackground(ContextCompat.getDrawable(exam.this,R.drawable.btn_selector_select_not));
    }

    private void txt_color_to_black(TextView view) {
        view.setTextColor(getResources().getColor(R.color.white));
    }

    private void txt_color_to_white(TextView view) {

        view.setTextColor(getResources().getColor(R.color.txt_black));
    }

    private void set_lottie_no(LottieAnimationView lottie_no){
        lottie_no.setAnimation(R.raw.no);
    }

    private void set_lottie_ok(LottieAnimationView lottie_ok){
        lottie_ok.setAnimation(R.raw.ok);
    }

    private void this_is_vrai(TextView view,LottieAnimationView lottieAnimationView) {
        view.setBackground(ContextCompat.getDrawable(exam.this,R.drawable.back_qustion_vrai));
        lottieAnimationView.setVisibility(View.VISIBLE);
        set_lottie_ok(lottieAnimationView);
        lottieAnimationView.playAnimation();
    }

    private void this_is_faut(TextView view,LottieAnimationView lottieAnimationView) {
        view.setBackground(ContextCompat.getDrawable(exam.this,R.drawable.back_qustion_faut));
        lottieAnimationView.setVisibility(View.VISIBLE);
        set_lottie_no(lottieAnimationView);
        lottieAnimationView.playAnimation();
    }

    private void this_is_mute(TextView view) {
        view.setBackground(ContextCompat.getDrawable(exam.this,R.drawable.back_qustion_mute));

    }

    private void rotate(View button_coins){
        Handler handler0=new Handler();
        handler0.postDelayed(() -> {
            button_coins.animate()
                    .setDuration(250)
                    .rotation(5)
                    .start();
            Handler handler=new Handler();
            handler.postDelayed(() -> {
                button_coins.animate()
                        .setDuration(500)
                        .rotation(-5)
                        .start();

                Handler handler2=new Handler();
                handler2.postDelayed(() -> {
                    button_coins.animate()
                            .setDuration(250)
                            .rotation(0)
                            .start();

                }, 500);
            }, 250);

        }, 250);
    }

    public void anim_this_view(final View b){
        Animation animFade = AnimationUtils.loadAnimation(this, R.anim.scale_to_0);

        animFade.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {}
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationEnd(Animation animation) {
                b.setVisibility(View.VISIBLE);
            }
        });
        b.startAnimation(animFade);

    }

    @Override
    public void onBackPressed() {
        exit_alert();
    }

    public interface laoded{
        void onCallBack();
    }


}