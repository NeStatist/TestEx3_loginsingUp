package com.example.testex3_loginsingup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

        private EditText editUser, editPass;
        private Button btnLogin, btnSignUp;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            editUser = findViewById(R.id.edit_user);
            editPass = findViewById(R.id.edit_password);
            btnLogin = findViewById(R.id.button_login);
            btnSignUp = findViewById(R.id.button_singUP);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        signIn();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                }
            });
        }

        public void signIn() throws Exception{
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("email", editUser.getText().toString());
            jsonObject.put("password", editPass.getText().toString());
            jsonObject.put("device_token", "HGWYUsnd83bdsjdfwoi4c");
            jsonObject.put("os_type", "ios");

            String s = RequestHandler.sendPost("https://api.screenlife.com/api-mobile/user/login", jsonObject);
            Log.i("PostRequest", s);
        }
    }


