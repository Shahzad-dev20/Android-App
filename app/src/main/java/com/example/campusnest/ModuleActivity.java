package com.example.campusnest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ModuleActivity extends AppCompatActivity {

    private String module;
    private List<Item> items;
    private ItemAdapter adapter;
    private RecyclerView rv;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        module = getIntent().getStringExtra("module");
        if (module == null) module = "notices";
        items = DataStore.load(this, module);

        ((TextView) findViewById(R.id.tvTitle)).setText(titleRes());
        tvEmpty = findViewById(R.id.tvEmpty);
        rv = findViewById(R.id.rv);

        adapter = new ItemAdapter(items);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.fabAdd).setOnClickListener(v -> showAddDialog());

        // Swipe left/right to delete, with Undo
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView r, @NonNull RecyclerView.ViewHolder v,
                                  @NonNull RecyclerView.ViewHolder t) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction) {
                int pos = holder.getBindingAdapterPosition();
                Item removed = items.remove(pos);
                adapter.notifyItemRemoved(pos);
                DataStore.save(ModuleActivity.this, module, items);
                updateEmpty();

                Snackbar.make(rv, R.string.item_deleted, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo, v -> {
                            items.add(pos, removed);
                            adapter.notifyItemInserted(pos);
                            DataStore.save(ModuleActivity.this, module, items);
                            updateEmpty();
                        }).show();
            }
        }).attachToRecyclerView(rv);

        updateEmpty();
    }

    private void updateEmpty() {
        tvEmpty.setVisibility(items.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void showAddDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_add, null);
        TextInputLayout tilA = view.findViewById(R.id.tilA);
        TextInputLayout tilB = view.findViewById(R.id.tilB);
        TextInputLayout tilC = view.findViewById(R.id.tilC);
        TextInputEditText etA = view.findViewById(R.id.etA);
        TextInputEditText etB = view.findViewById(R.id.etB);
        TextInputEditText etC = view.findViewById(R.id.etC);

        int[] h = hints();
        tilA.setHint(getString(h[0]));
        tilB.setHint(getString(h[1]));
        tilC.setHint(getString(h[2]));

        AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                .setTitle(addTitleRes())
                .setView(view)
                .setPositiveButton(R.string.btn_save, null)
                .setNegativeButton(R.string.btn_cancel, null)
                .create();
        dialog.show();

        // Override Save so the dialog stays open if validation fails
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String a = etA.getText().toString().trim();
            String b = etB.getText().toString().trim();
            String c = etC.getText().toString().trim();

            if (a.isEmpty()) {
                tilA.setError(getString(R.string.error_required));
                return;
            }
            items.add(0, new Item(a, b, c));
            adapter.notifyItemInserted(0);
            rv.scrollToPosition(0);
            DataStore.save(this, module, items);
            updateEmpty();
            dialog.dismiss();
        });
    }

    private int titleRes() {
        switch (module) {
            case "courses": return R.string.module_courses;
            case "schedule": return R.string.module_schedule;
            case "assignments": return R.string.module_assignments;
            case "messages": return R.string.module_messages;
            default: return R.string.module_notices;
        }
    }

    private int addTitleRes() {
        switch (module) {
            case "courses": return R.string.add_course;
            case "schedule": return R.string.add_class;
            case "assignments": return R.string.add_assignment;
            case "messages": return R.string.add_message;
            default: return R.string.add_notice;
        }
    }

    private int[] hints() {
        switch (module) {
            case "courses":
                return new int[]{R.string.hint_course_name, R.string.hint_teacher, R.string.hint_credits};
            case "schedule":
                return new int[]{R.string.hint_subject, R.string.hint_day, R.string.hint_time};
            case "assignments":
                return new int[]{R.string.hint_title, R.string.hint_subject, R.string.hint_due};
            case "messages":
                return new int[]{R.string.hint_sender, R.string.hint_message, R.string.hint_time};
            default:
                return new int[]{R.string.hint_title, R.string.hint_description, R.string.hint_date};
        }
    }
}