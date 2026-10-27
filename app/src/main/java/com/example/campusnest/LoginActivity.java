package com.example.campusnest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout tilEmail, tilPassword;
    TextInputEditText etEmail, etPassword;
    Button btnLogin;
    TextView tvForgot;
    View tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppSettings.bind(this);
        if (savedInstanceState == null) ShutterAnimator.open(this);

        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgot = findViewById(R.id.tvForgot);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            tilEmail.setError(null);
            tilPassword.setError(null);

            String id = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            boolean ok = true;
            if (id.isEmpty()) {
                tilEmail.setError(getString(R.string.error_required));
                ok = false;
            }
            if (pass.isEmpty()) {
                tilPassword.setError(getString(R.string.error_required));
                ok = false;
            }
            if (!ok) return;

            if (UserStore.login(this, id, pass)) {
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();
            } else {
                Toast.makeText(this, R.string.error_login_failed, Toast.LENGTH_SHORT).show();
            }
        });

        tvForgot.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}