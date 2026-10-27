package com.example.campusnest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View logo = findViewById(R.id.imgLogo);
        if (logo != null) {
            logo.setAlpha(0f);
            logo.setScaleX(0.6f);
            logo.setScaleY(0.6f);
            logo.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(1200).start();
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Class<?> next = UserStore.isLoggedIn(this)
                    ? DashboardActivity.class : LoginActivity.class;
            startActivity(new Intent(MainActivity.this, next));
            finish();
        }, 2500);
    }
}