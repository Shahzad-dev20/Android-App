package com.example.campusnest;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserStore {

    private static final String PREFS = "campusnest_users";

    public static String name = "";
    public static String email = "";
    private static JSONArray accounts = new JSONArray();

    // Load saved accounts (creates the demo account the first time)
    public static void init(Context c) {
        SharedPreferences sp = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String json = sp.getString("accounts", null);
        try {
            if (json == null) {
                accounts = new JSONArray();
                accounts.put(make("Demo Student", "student", "student@campusnest.com", "123456"));
                persist(c);
            } else {
                accounts = new JSONArray(json);
            }
        } catch (JSONException e) {
            accounts = new JSONArray();
        }

        String current = sp.getString("current", null);
        if (current != null) {
            JSONObject o = find(current);
            if (o != null) {
                name = o.optString("name");
                email = o.optString("email");
            }
        }
    }

    private static JSONObject make(String n, String u, String e, String p) throws JSONException {
        JSONObject o = new JSONObject();
        o.put("name", n);
        o.put("username", u);
        o.put("email", e);
        o.put("password", p);
        return o;
    }

    private static JSONObject find(String id) {
        for (int i = 0; i < accounts.length(); i++) {
            JSONObject o = accounts.optJSONObject(i);
            if (o == null) continue;
            if (o.optString("email").equalsIgnoreCase(id)
                    || o.optString("username").equalsIgnoreCase(id)) {
                return o;
            }
        }
        return null;
    }

    private static void persist(Context c) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putString("accounts", accounts.toString()).apply();
    }

    public static boolean emailExists(String e) {
        return find(e) != null;
    }

    public static boolean register(Context c, String n, String e, String p) {
        if (find(e) != null) return false;
        try {
            accounts.put(make(n, n, e, p));
        } catch (JSONException ex) {
            return false;
        }
        persist(c);
        return true;
    }

    public static boolean login(Context c, String id, String pass) {
        JSONObject o = find(id);
        if (o == null || !o.optString("password").equals(pass)) return false;
        name = o.optString("name");
        email = o.optString("email");
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putString("current", email).apply();
        return true;
    }

    public static boolean isLoggedIn(Context c) {
        String current = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getString("current", null);
        return current != null && !email.isEmpty();
    }

    public static void resetPassword(Context c, String e, String newPass) {
        JSONObject o = find(e);
        if (o == null) return;
        try {
            o.put("password", newPass);
        } catch (JSONException ex) {
            return;
        }
        persist(c);
    }

    public static void logout(Context c) {
        name = "";
        email = "";
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().remove("current").apply();
    }
}