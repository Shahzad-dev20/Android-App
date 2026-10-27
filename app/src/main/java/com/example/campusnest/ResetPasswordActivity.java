package com.example.campusnest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ResetPasswordActivity extends AppCompatActivity {

    TextInputLayout tilNew, tilConfirm;
    TextInputEditText etNew, etConfirm;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        tilNew = findViewById(R.id.tilNew);
        tilConfirm = findViewById(R.id.tilConfirm);
        etNew = findViewById(R.id.etNew);
        etConfirm = findViewById(R.id.etConfirm);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            tilNew.setError(null);
            tilConfirm.setError(null);

            String pass = etNew.getText().toString().trim();
            String confirm = etConfirm.getText().toString().trim();

            boolean ok = true;
            if (pass.length() < 6) {
                tilNew.setError(getString(R.string.error_password_short));
                ok = false;
            }
            if (!confirm.equals(pass)) {
                tilConfirm.setError(getString(R.string.error_password_mismatch));
                ok = false;
            }
            if (!ok) return;

            UserStore.resetPassword(this, getIntent().getStringExtra("email"), pass);
            Toast.makeText(this, R.string.msg_password_changed, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}