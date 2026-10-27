package com.example.campusnest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    private static final int[] CARDS = {
            R.id.cardCourses, R.id.cardSchedule, R.id.cardAssignments,
            R.id.cardMessages, R.id.cardNotices, R.id.cardProfile
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        AppSettings.bind(this);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int greetingRes = hour < 12 ? R.string.greeting_morning
                : hour < 17 ? R.string.greeting_afternoon
                : R.string.greeting_evening;

        ((TextView) findViewById(R.id.tvGreeting)).setText(
                getString(R.string.greeting_format, getString(greetingRes), UserStore.name));

        openModule(R.id.cardCourses, "courses");
        openModule(R.id.cardSchedule, "schedule");
        openModule(R.id.cardAssignments, "assignments");
        openModule(R.id.cardMessages, "messages");
        openModule(R.id.cardNotices, "notices");
        openModule(R.id.btnNextOpen, "schedule");

        findViewById(R.id.cardProfile).setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class)));

        if (savedInstanceState == null) animateIn();
    }

    // Smooth, lightweight entrance animation
    private void animateIn() {
        float d = getResources().getDisplayMetrics().density;

        View next = findViewById(R.id.cardNext);
        next.setAlpha(0f);
        next.setTranslationY(16 * d);
        next.animate().alpha(1f).translationY(0)
                .setDuration(300)
                .setInterpolator(new DecelerateInterpolator()).start();

        for (int i = 0; i < CARDS.length; i++) {
            View c = findViewById(CARDS[i]);
            c.setAlpha(0f);
            c.setTranslationY(16 * d);
            c.animate().alpha(1f).translationY(0)
                    .setStartDelay(100 + i * 40L).setDuration(350)
                    .setInterpolator(new DecelerateInterpolator()).start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCount(R.id.countCourses, "courses");
        setCount(R.id.countSchedule, "schedule");
        setCount(R.id.countAssignments, "assignments");
        setCount(R.id.countMessages, "messages");
        setCount(R.id.countNotices, "notices");
    }

    private void openModule(int viewId, String module) {
        findViewById(viewId).setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ModuleActivity.class);
            intent.putExtra("module", module);
            startActivity(intent);
        });
    }

    private void setCount(int textId, String module) {
        ((TextView) findViewById(textId))
                .setText(String.valueOf(DataStore.count(this, module)));
    }
}