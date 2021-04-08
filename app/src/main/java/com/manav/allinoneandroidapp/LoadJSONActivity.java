package com.manav.allinoneandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.manav.allinoneandroidapp.model.UserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadJSONActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    private String jsonRes;

    List<UserModel> userModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_json);

        userModelList = new ArrayList<>();

        jsonRes = loadJSONFromAsset();

        try {
            JSONObject jsonObject = new JSONObject(jsonRes);

            String perPage = jsonObject.getString("per_page"); //.getString("page/total..")
            Log.i(TAG, "per_page = ? : "+ perPage);

            JSONArray jsonArray = jsonObject.getJSONArray("data");

            //Toast.makeText(this, "data array ? " + jsonArray.toString(), Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                //Toast.makeText(this, " data obj inside of json array ? " + jsonObject1, Toast.LENGTH_SHORT).show();

                String id = jsonObject1.getString("id");
                String email = jsonObject1.getString("email");
                Toast.makeText(this, "email and id " + email + " " + id, Toast.LENGTH_SHORT).show();

             //Gson converts json to pojo like jackson binding..
                Gson gson = new Gson();

                UserModel model = gson.fromJson(String.valueOf(jsonObject1), UserModel.class);

                userModelList.add(model);

                // ... manipulate the userlist now... into recycler view... or something...
                //Toast.makeText(this, "usermodel list ? " + userModelList.get(0).getEmail(), Toast.LENGTH_SHORT).show();


            }

        }
        catch (Exception e) {
            Toast.makeText(this, "Exception while loading json : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();

            byte [] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "UTF-8");
        }

        catch (Exception e) {
            Toast.makeText(this, "Exception occurred : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
        return json;
    }
}