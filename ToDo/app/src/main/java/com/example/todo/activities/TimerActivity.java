package com.example.todo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
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

        String[] timerTimes = new String[60];
        for (int i = 0; i < 60; i++){
            timerTimes[i] = String.valueOf(i+1);
        }

        myTimerNumberPicker = findViewById(R.id.timer_numberPicker);
        myTimerNumberPicker.setMinValue(0);
        myTimerNumberPicker.setMaxValue(60);
        myTimerNumberPicker.setDisplayedValues(timerTimes);
        myTimerNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> myPickedNumber = numberPicker.getValue() + 1);
    }

    public void startTimer(View view) {
        myTimerButton.setEnabled(false);
        myTimerNumberPicker.setVisibility(View.INVISIBLE);
        new CountDownTimer(myPickedNumber*1000, 1000) {

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
                myTimerNumberPicker.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private TextView myTimerTextView;
    private Button myTimerButton;
    private NumberPicker myTimerNumberPicker;
    private int myPickedNumber;
}
