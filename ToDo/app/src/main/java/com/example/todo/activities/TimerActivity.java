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
        myStartButton = findViewById(R.id.timer_button);
        myPauseButton = findViewById(R.id.pause_button);
        myContinueButton = findViewById(R.id.continue_button);
        myStopButton = findViewById(R.id.stop_button);

        String[] secondsValue = new String[60];
        for (int i = 0; i < 60; i++){
            secondsValue[i] = String.valueOf(i);
        }

        mySecondsTimerNumberPicker = findViewById(R.id.timerSeconds_numberPicker);
        mySecondsTimerNumberPicker.setMinValue(0);
        mySecondsTimerNumberPicker.setMaxValue(59);
        mySecondsTimerNumberPicker.setDisplayedValues(secondsValue);
        mySecondsTimerNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> mySecondsTimerNumber = numberPicker.getValue());

        String[] minutesValues = new String[61];
        for (int i = 0; i < 61; i++){
            minutesValues[i] = String.valueOf(i);
        }

        myMinutesTimerNumberPicker = findViewById(R.id.timerMinutes_numberPicker);
        myMinutesTimerNumberPicker.setMinValue(0);
        myMinutesTimerNumberPicker.setMaxValue(60);
        myMinutesTimerNumberPicker.setValue(25);
        myMinutesTimerNumberPicker.setDisplayedValues(minutesValues);
        myMinutesTimerNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> myMinutesTimerNumber = numberPicker.getValue());
    }

    public void startTimer(View view) {
        myStartButton.setVisibility(View.INVISIBLE);
        mySecondsTimerNumberPicker.setVisibility(View.INVISIBLE);
        myMinutesTimerNumberPicker.setVisibility(View.INVISIBLE);
        myPauseButton.setVisibility(View.VISIBLE);

        myIsPaused = false;
        myIsCanceled = false;

        long millisInFuture = (myMinutesTimerNumber * 1000 * 60) + (mySecondsTimerNumber * 1000);
        long countDownInterval = 1000;
        CountDownTimer countDownTimer = createCountDownTimer(millisInFuture, countDownInterval);
        countDownTimer.start();
    }

    public void pauseTimer(View view){
        myPauseButton.setVisibility(View.INVISIBLE);
        myContinueButton.setVisibility(View.VISIBLE);
        myStopButton.setVisibility(View.VISIBLE);

        myIsPaused = true;
    }

    public void continueTimer(View view){
        myContinueButton.setVisibility(View.INVISIBLE);
        myStopButton.setVisibility(View.INVISIBLE);
        myPauseButton.setVisibility(View.VISIBLE);

        myIsPaused = false;
        myIsCanceled = false;

        long millisInFuture = myTimeRemaining;
        long countDownInterval = 1000;
        CountDownTimer countDownTimer = createCountDownTimer(millisInFuture, countDownInterval);
        countDownTimer.start();
    }

    public void stopTimer(View view){
        myStopButton.setVisibility(View.INVISIBLE);
        myContinueButton.setVisibility(View.INVISIBLE);
        myStartButton.setVisibility(View.VISIBLE);
        myMinutesTimerNumberPicker.setVisibility(View.VISIBLE);
        mySecondsTimerNumberPicker.setVisibility(View.VISIBLE);

        myIsCanceled = true;

        myTimerTextView.setText("Timer is stopped!");
    }

    private CountDownTimer createCountDownTimer(long millisInFuture, long countDownInterval){
        return new CountDownTimer(millisInFuture, countDownInterval) {

            public void onTick(long millisUntilFinished) {
                if(myIsPaused || myIsCanceled){
                    cancel();
                }
                else {
                    setTimerText(millisUntilFinished);
                    myTimeRemaining = millisUntilFinished;
                }
            }

            public void onFinish() {
                myTimerTextView.setText("Done!");
                myPauseButton.setVisibility(View.INVISIBLE);
                myStartButton.setVisibility(View.VISIBLE);
                mySecondsTimerNumberPicker.setVisibility(View.VISIBLE);
                myMinutesTimerNumberPicker.setVisibility(View.VISIBLE);
            }
        };
    }

    private void setTimerText(long millisUntilFinished) {
        long seconds = 0;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
        if (minutes == 0) {
            seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
        } else {
            seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - (minutes * 60);
        }

        myTimerTextView.setText(minutes + ":" + seconds);
    }

    private TextView myTimerTextView;
    private Button myStartButton;
    private Button myPauseButton;
    private Button myContinueButton;
    private Button myStopButton;
    private NumberPicker mySecondsTimerNumberPicker;
    private NumberPicker myMinutesTimerNumberPicker;

    private int mySecondsTimerNumber;
    private int myMinutesTimerNumber = 25;
    private boolean myIsPaused = false;
    private boolean myIsCanceled = false;
    private long myTimeRemaining = 0;
}
