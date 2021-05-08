package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manav.allinoneandroidapp.sqlite_db.DatabaseHandler;

import java.util.List;

public class SQLiteDemoActivity extends AppCompatActivity {

    private EditText etInputText;
    private TextView etAllItems;
    private Button btnAdd_, btnGet_;
    String text = "";


    private DatabaseHandler myDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_lite_demo);

        //initialize widget
        etInputText = findViewById(R.id.etInputText);
        etAllItems = findViewById(R.id.etAllItems);
        btnAdd_ = findViewById(R.id.btnAdd_);
        btnGet_ = findViewById(R.id.btnGet_);
        myDatabaseHandler = new DatabaseHandler(this);

        btnAdd_.setOnClickListener(v -> {
            String description = etInputText.getText().toString();
            if(description.length()<3) {
                Toast.makeText(this, "provide some values", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "adding ", Toast.LENGTH_SHORT).show();

            boolean isCreated = myDatabaseHandler.addItem(description);
            if(isCreated) {
                Toast.makeText(this, "Item added to DB", Toast.LENGTH_SHORT).show();
                etInputText.setText("");
            }
            else {
                Toast.makeText(this, "Item wasn't created.", Toast.LENGTH_SHORT).show();
            }
        });

        btnGet_.setOnClickListener(v -> {
            Toast.makeText(this, "getting ", Toast.LENGTH_SHORT).show();
            etAllItems.setText("");
            text = "";
            List<String> allItems = myDatabaseHandler.getItems();
            allItems.forEach((item) -> {
                text += item +"\n";
            });
            etAllItems.setText(text);
        });
    }
}