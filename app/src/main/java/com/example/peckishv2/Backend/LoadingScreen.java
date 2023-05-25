package com.example.peckishv2.Backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.peckishv2.R;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingScreen extends AppCompatActivity {

    ProgressBar bar;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        progressBar();
    }

    public void progressBar()
    {
        bar = findViewById(R.id.progressBar);
        final Timer timer = new Timer();
        TimerTask delay = new TimerTask() {
            @Override
            public void run() {
                progress += 2;
                bar.setProgress(progress);

                if(progress == 100)
                {
                    timer.cancel();
                    startActivity(new Intent(LoadingScreen.this, Preferences.class));
                }
            }
        };
        timer.schedule(delay, 0, 100);
    }
}