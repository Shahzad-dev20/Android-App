package com.example.campusnest;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ShutterAnimator {

    public static void open(Activity a) {
        ViewGroup root = a.findViewById(android.R.id.content);
        float d = a.getResources().getDisplayMetrics().density;

        FrameLayout overlay = new FrameLayout(a);
        overlay.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        overlay.setClickable(true); // blocks taps while the shutter is down

        LinearLayout slats = new LinearLayout(a);
        slats.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 16; i++) {
            View slat = new View(a);
            slat.setBackgroundResource(R.drawable.bg_slat);
            slats.addView(slat, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));
        }
        overlay.addView(slats, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        View handle = new View(a);
        handle.setBackgroundResource(R.drawable.bg_handle);
        FrameLayout.LayoutParams hp = new FrameLayout.LayoutParams((int) (64 * d), (int) (8 * d));
        hp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        hp.bottomMargin = (int) (56 * d);
        overlay.addView(handle, hp);

        root.addView(overlay);

        overlay.post(() -> overlay.animate()
                .translationY(-overlay.getHeight())
                .setStartDelay(500)
                .setDuration(1200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .withEndAction(() -> root.removeView(overlay))
                .start());
    }
}