package com.manav.allinoneandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreenActivity extends AppCompatActivity {

    Button popupMenuBtn;
    Button contextMenu;
    TextView tvGoToRCView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //getSupportActionBar().hide();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       popupMenuBtn = findViewById(R.id.popupMenuBtn);
       contextMenu = findViewById(R.id.showContextualMenuBtn);


        tvGoToRCView =findViewById(R.id.tvGoToRCView);
       //etInput.addTextChangedListener(......);

       popupMenuBtn.setOnClickListener(view -> {
           showPopupMenu(view);
       });

       contextMenu.setOnClickListener(view -> {
           Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
           registerForContextMenu(findViewById(R.id.showContextualMenuBtn));
       });

       //go to recycler view
        tvGoToRCView.setOnClickListener(v -> startActivity(new Intent(this, RecyclerViewActivity.class)));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.downloaded) {
            Toast.makeText(this, "this was clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu_item);
        popupMenu.show();
     }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contextual_menu_item, menu);
        menu.setHeaderTitle("Select Menu Item");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }
}

