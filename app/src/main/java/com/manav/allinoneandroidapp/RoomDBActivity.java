package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manav.allinoneandroidapp.model.CustomerModel;
import com.manav.allinoneandroidapp.roomdb.MyRoomDatabase;

import java.util.List;

public class RoomDBActivity extends AppCompatActivity {

    private static final String DB_NAME = "CustomerDB";
    private EditText etInputTextRoom, etCustomerID;
    private TextView etAllItemsRoom;
    private Button btnAddRoom_, btnGetRoom_;
    String text = "";

    private CustomerModel customerModel;
    private MyRoomDatabase myRoomDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_d_b);

        //initialize widget
        etInputTextRoom = findViewById(R.id.etInputTextRoom);
        etCustomerID = findViewById(R.id.etCustomerID);
        etAllItemsRoom = findViewById(R.id.etAllItemsRoom);
        btnAddRoom_ = findViewById(R.id.btnAddRoom_);
        btnGetRoom_ = findViewById(R.id.btnGetRoom_);

        myRoomDatabase = Room.databaseBuilder(getApplicationContext(), MyRoomDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();

        btnAddRoom_.setOnClickListener(v -> {
            String description = etInputTextRoom.getText().toString();
            if(description.length()<3) {
                Toast.makeText(this, "provide some values", Toast.LENGTH_SHORT).show();
                return;
            }
            if(etCustomerID.getText().toString().length()<3) {
                Toast.makeText(this, "provide some values", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "adding ... ", Toast.LENGTH_SHORT).show();
            customerModel = new CustomerModel();
            customerModel.setCustomerID(Integer.parseInt(etCustomerID.getText().toString()));
            customerModel.setCustomerName(description);
           // customerModel.setId(23333);

            myRoomDatabase.customerDao().insertCustomer(customerModel);
        });

        btnGetRoom_.setOnClickListener(v -> {
            etAllItemsRoom.setText("");
            text = "";
            Toast.makeText(this, "getting ... ", Toast.LENGTH_SHORT).show();
            List<CustomerModel> customerModelList = myRoomDatabase.customerDao().getAllCustomers();

            customerModelList.forEach(customer -> {
                text += customer.getCustomerName() + " " + customer.getCustomerID() + "\n";
            });
            etAllItemsRoom.setText(text);
        });
    }
}