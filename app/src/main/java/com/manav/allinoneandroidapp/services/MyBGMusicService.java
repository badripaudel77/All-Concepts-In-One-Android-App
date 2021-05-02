package com.manav.allinoneandroidapp.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MyBGMusicService extends Service {

    private MediaPlayer mediaPlayer;

    @Override

    // execution of service will start
    // on calling this method
    public int onStartCommand(Intent intent, int flags, int startId) {

        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

        // providing the boolean
        // value as true to play
        // the audio on loop
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(2.0f, 3.0f);

        // starting the process
        mediaPlayer.start();

        // returns the status
        // of the program
        return START_STICKY;
    }


    @Override

    // execution of the service will
    // stop on calling this method
    public void onDestroy() {
        super.onDestroy();

        // stopping the process
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
