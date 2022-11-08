package com.oxey.qsmpolice;

import android.content.Context;
import android.media.MediaPlayer;



public class sound {

    MediaPlayer son_collect,song_msg;


    public void collect(Context context){
        if(context!=null)

            son_collect = MediaPlayer.create(context, R.raw.son);
            son_collect.start();
            son_collect.setOnCompletionListener(MediaPlayer::release);

    }

    public void msg(Context context){
            song_msg = MediaPlayer.create(context, R.raw.clavier);
            song_msg.setLooping(true);
            song_msg.start();
            song_msg.setOnCompletionListener(MediaPlayer::release);
    }

    public void stop_clavier(){
        if(song_msg!=null) {
            if(song_msg.isPlaying()) {
                song_msg.stop();
                song_msg.reset();
                song_msg.release();
                song_msg=null;
            }
        }

    }


}
