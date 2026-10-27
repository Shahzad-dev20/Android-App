package com.example.campusnest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        AppSettings.bind(this);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int greetingRes = hour < 12 ? R.string.greeting_morning
                : hour < 17 ? R.string.greeting_afternoon
                : R.string.greeting_evening;

        TextView tvGreeting = findViewById(R.id.tvGreeting);
        tvGreeting.setText(getString(R.string.greeting_format,
                getString(greetingRes), UserStore.name));

        openModule(R.id.cardCourses, "courses");
        openModule(R.id.cardSchedule, "schedule");
        openModule(R.id.cardAssignments, "assignments");
        openModule(R.id.cardMessages, "messages");
        openModule(R.id.cardNotices, "notices");

        findViewById(R.id.cardProfile).setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class)));
    }

    // Refresh the counts every time we come back to this screen
    @Override
    protected void onResume() {
        super.onResume();
        setCount(R.id.countCourses, "courses");
        setCount(R.id.countSchedule, "schedule");
        setCount(R.id.countAssignments, "assignments");
        setCount(R.id.countMessages, "messages");
        setCount(R.id.countNotices, "notices");
    }

    private void openModule(int cardId, String module) {
        findViewById(cardId).setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ModuleActivity.class);
            intent.putExtra("module", module);
            startActivity(intent);
        });
    }

    private void setCount(int textId, String module) {
        ((TextView) findViewById(textId))
                .setText(getString(R.string.items_count, DataStore.count(this, module)));
    }
}