package com.example.todo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todo.R;

import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity);

        myTimerTextView = findViewById(R.id.timer_textView);
        myTimerButton = findViewById(R.id.timer_button);
    }

    public void startTimer(View view) {
        myTimerButton.setEnabled(false);
        new CountDownTimer(125000, 1000) {

            public void onTick(long millisUntilFinished) {
                long seconds = 0;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                if(minutes == 0){
                    seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                }else{
                    seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - (minutes * 60);
                }

                myTimerTextView.setText(minutes + ":" + seconds);
            }

            public void onFinish() {
                myTimerTextView.setText("Done!");
                myTimerButton.setEnabled(true);
            }
        }.start();
    }

    private TextView myTimerTextView;
    private Button myTimerButton;
}