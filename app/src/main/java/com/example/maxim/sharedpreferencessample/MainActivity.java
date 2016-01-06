package com.example.maxim.sharedpreferencessample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int currentRate=0;
    private int highestRate=0;
    private TextView currentRateView;
    private TextView highestRateView;

    public static final String PREFS_NAME = "SharedPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        currentRate = storedData.getInt("currentRate", 0);
        highestRate = storedData.getInt("highestRate", 0);

        currentRateView = (TextView) findViewById(R.id.textView);
        highestRateView = (TextView) findViewById(R.id.textView2);

        currentRateView.setText("Current Rating: " + currentRate);
        highestRateView.setText("Max Rating: " + highestRate);
    }

    public void addCurrentRate(View view) {
        currentRate++;
        currentRateView.setText("Current Rating: " + currentRate);
        if (highestRate < currentRate) {
            highestRate = currentRate;
        }
        highestRateView.setText("Max Rating: " + highestRate);
    }

    public void clearCurrentRate(View view) {
        currentRate = 0;
        saveRates();
        currentRateView.setText("Current Rating: " + currentRate);
    }

    public void clearAllRates(View view) {
        currentRate = 0;
        highestRate = 0;
        saveRates();
        currentRateView.setText("Current Rate: " + currentRate);
        highestRateView.setText("Highest Rate: " + highestRate);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.saveRates();
    }

    protected void saveRates() {
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor storedDataEditor = storedData.edit();
        storedDataEditor.putInt("currentRate", currentRate);
        storedDataEditor.putInt("highestRate", highestRate);
        storedDataEditor.commit();

    }

}