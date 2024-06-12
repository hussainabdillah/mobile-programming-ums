package com.project.timercountdownapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

public class CountdownService extends Service {
    private CountDownTimer countDownTimer;
    private MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int countdownValue = intent.getIntExtra("COUNTDOWN_VALUE", 0);
        startCountdown(countdownValue);
        return START_STICKY;
    }

    private void startCountdown(final int countdownValue) {
        countDownTimer = new CountDownTimer(countdownValue * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                playAlarm();
                Toast.makeText(getApplicationContext(), "Countdown finished", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
    }

    private void playAlarm() {
        mediaPlayer = MediaPlayer.create(this, android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}





