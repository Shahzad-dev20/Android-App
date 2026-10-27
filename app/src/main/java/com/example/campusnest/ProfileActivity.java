package com.example.campusnest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppSettings.bind(this);

        ((TextView) findViewById(R.id.tvName)).setText(UserStore.name);
        ((TextView) findViewById(R.id.tvEmail)).setText(UserStore.email);

        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            UserStore.logout(this);
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}