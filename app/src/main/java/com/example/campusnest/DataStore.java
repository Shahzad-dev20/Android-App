package com.example.campusnest;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static final String PREFS = "campusnest_data";

    public static List<Item> load(Context c, String module) {
        SharedPreferences sp = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String json = sp.getString(module, null);

        if (json == null) {                       // first run: use dummy data
            List<Item> seeded = seed(module);
            save(c, module, seeded);
            return seeded;
        }

        List<Item> list = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                list.add(new Item(o.getString("t"), o.getString("s"), o.getString("m")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(Context c, String module, List<Item> list) {
        JSONArray arr = new JSONArray();
        try {
            for (Item it : list) {
                JSONObject o = new JSONObject();
                o.put("t", it.title);
                o.put("s", it.subtitle);
                o.put("m", it.meta);
                arr.put(o);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putString(module, arr.toString()).apply();
    }

    public static int count(Context c, String module) {
        return load(c, module).size();
    }

    private static List<Item> seed(String module) {
        List<Item> l = new ArrayList<>();
        switch (module) {
            case "courses":
                l.add(new Item("Machine Learning", "Dr. Ayesha Khan", "3 credit hours"));
                l.add(new Item("Data Structures", "Prof. Imran Malik", "3 credit hours"));
                l.add(new Item("Mobile App Development", "Ms. Sana Iqbal", "3 credit hours"));
                l.add(new Item("Database Systems", "Dr. Bilal Ahmed", "4 credit hours"));
                l.add(new Item("Linear Algebra", "Prof. Hina Raza", "3 credit hours"));
                break;
            case "schedule":
                l.add(new Item("Machine Learning", "Monday", "09:00 - 10:30"));
                l.add(new Item("Data Structures", "Tuesday", "11:00 - 12:30"));
                l.add(new Item("Mobile App Development", "Wednesday", "10:00 - 11:30"));
                l.add(new Item("Database Systems", "Thursday", "12:00 - 01:30"));
                l.add(new Item("Linear Algebra", "Friday", "09:00 - 10:30"));
                break;
            case "assignments":
                l.add(new Item("Project proposal", "Mobile App Development", "Due: 02 Nov"));
                l.add(new Item("Lab 4: Linked Lists", "Data Structures", "Due: 05 Nov"));
                l.add(new Item("ER Diagram", "Database Systems", "Due: 08 Nov"));
                l.add(new Item("Model evaluation report", "Machine Learning", "Due: 12 Nov"));
                break;
            case "messages":
                l.add(new Item("Dr. Ayesha Khan", "Please upload your dataset by Friday.", "10:15 AM"));
                l.add(new Item("Class Group", "Tomorrow's lab is moved to Room 204.", "Yesterday"));
                l.add(new Item("Ms. Sana Iqbal", "Great work on the login screens!", "Mon"));
                l.add(new Item("Admin Office", "Your fee voucher is now available.", "Sun"));
                break;
            default: // notices
                l.add(new Item("Mid-term exams", "Mid-terms start on 18 November. Check the datesheet.", "25 Oct"));
                l.add(new Item("Library timing", "The library stays open until 8 PM this week.", "24 Oct"));
                l.add(new Item("Tech Fest 2026", "Registrations are open for the annual tech fest.", "22 Oct"));
                l.add(new Item("Holiday notice", "Campus will be closed on Friday.", "20 Oct"));
                break;
        }
        return l;
    }
}