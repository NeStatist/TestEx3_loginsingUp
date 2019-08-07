package com.example.testex3_loginsingup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText editEmail, editUsername, editPassword;
    Button btnRegisterOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editEmail = findViewById(R.id.email);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        btnRegisterOk = findViewById(R.id.buttonRegisterOk);

        btnRegisterOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    signUp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void signUp() throws Exception{
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email",editEmail.getText().toString());
        jsonObject.put("username", editUsername.getText().toString());
        jsonObject.put("password", editPassword.getText().toString());
        jsonObject.put("device_token", "HGWYUsnd83bdsjdfwoi4c");
        jsonObject.put("os_type", "ios");

        String s = RequestHandler.sendPost("https://api.screenlife.com/api-mobile/user/create", jsonObject);
        Log.i("PostRequest", s);
    }

    @Override
    public void onClick(View view) {

    }
}