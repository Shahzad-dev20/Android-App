package com.example.campusnest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputLayout tilEmail;
    TextInputEditText etEmail;
    Button btnSend;
    TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tilEmail = findViewById(R.id.tilEmail);
        etEmail = findViewById(R.id.etEmail);
        btnSend = findViewById(R.id.btnSend);
        tvBack = findViewById(R.id.tvBack);

        btnSend.setOnClickListener(v -> {
            tilEmail.setError(null);
            String email = etEmail.getText().toString().trim();

            if (email.isEmpty()) {
                tilEmail.setError(getString(R.string.error_required));
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.setError(getString(R.string.error_email_invalid));
                return;
            }
            if (!UserStore.emailExists(email)) {
                tilEmail.setError(getString(R.string.msg_email_not_found));
                return;
            }

            // Simulated: pretend a reset link was emailed, then open Reset screen
            Toast.makeText(this, R.string.msg_reset_sent, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        });

        tvBack.setOnClickListener(v -> finish());
    }
}