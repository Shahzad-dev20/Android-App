package com.example.campusnest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

public class AppSettings {

    private static final String PREFS = "campusnest_prefs";

    // To add a language: add its name and code here, and create values-xx/strings.xml
    private static final String[] LANG_NAMES = {"English", "اردو"};
    private static final String[] LANG_TAGS = {"en", "ur"};

    public static void applySavedTheme(Context c) {
        SharedPreferences sp = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        if (sp.contains("dark")) {
            AppCompatDelegate.setDefaultNightMode(sp.getBoolean("dark", false)
                    ? AppCompatDelegate.MODE_NIGHT_YES
                    : AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static boolean isNight(Context c) {
        return (c.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }

    // Call this after setContentView on any screen that includes view_top_actions
    public static void bind(Activity a) {
        ImageButton btnTheme = a.findViewById(R.id.btnTheme);
        ImageButton btnLang = a.findViewById(R.id.btnLang);

        if (btnTheme != null) {
            btnTheme.setImageResource(isNight(a) ? R.drawable.ic_sun : R.drawable.ic_moon);
            btnTheme.setOnClickListener(v -> {
                boolean goDark = !isNight(a);
                a.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                        .edit().putBoolean("dark", goDark).apply();
                AppCompatDelegate.setDefaultNightMode(goDark
                        ? AppCompatDelegate.MODE_NIGHT_YES
                        : AppCompatDelegate.MODE_NIGHT_NO);
            });
        }

        if (btnLang != null) {
            btnLang.setOnClickListener(v -> {
                PopupMenu menu = new PopupMenu(a, v);
                for (int i = 0; i < LANG_NAMES.length; i++) {
                    menu.getMenu().add(0, i, i, LANG_NAMES[i]);
                }
                menu.setOnMenuItemClickListener(item -> {
                    AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags(LANG_TAGS[item.getItemId()]));
                    return true;
                });
                menu.show();
            });
        }
    }
}