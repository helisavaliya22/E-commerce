package com.example.e_commerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.e_commerce.R;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    boolean IsLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("heli",0);
        editor = preferences.edit();

        IsLogin = preferences.getBoolean("LoginStatus",false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (IsLogin)
                {
                    startActivity(new Intent(MainActivity.this, HomePage_Activity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, Login_Activity.class));
                    finish();
                }
            }
        },1000);
    }
}