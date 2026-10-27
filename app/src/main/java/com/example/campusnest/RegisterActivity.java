package com.example.campusnest;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout tilName, tilEmail, tilPassword, tilConfirm;
    TextInputEditText etName, etEmail, etPassword, etConfirm;
    Button btnRegister;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirm = findViewById(R.id.tilConfirm);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> {
            tilName.setError(null);
            tilEmail.setError(null);
            tilPassword.setError(null);
            tilConfirm.setError(null);

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            String confirm = etConfirm.getText().toString().trim();

            boolean ok = true;

            if (name.isEmpty()) {
                tilName.setError(getString(R.string.error_required));
                ok = false;
            }
            if (email.isEmpty()) {
                tilEmail.setError(getString(R.string.error_required));
                ok = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.setError(getString(R.string.error_email_invalid));
                ok = false;
            }
            if (pass.length() < 6) {
                tilPassword.setError(getString(R.string.error_password_short));
                ok = false;
            }
            if (!confirm.equals(pass)) {
                tilConfirm.setError(getString(R.string.error_password_mismatch));
                ok = false;
            }
            if (!ok) return;

            if (!UserStore.register(this, name, email, pass)) {
                tilEmail.setError(getString(R.string.msg_email_exists));
                return;
            }

            Toast.makeText(this, R.string.msg_register_success, Toast.LENGTH_SHORT).show();
            finish(); // goes back to Login
        });

        tvLogin.setOnClickListener(v -> finish());
    }
}