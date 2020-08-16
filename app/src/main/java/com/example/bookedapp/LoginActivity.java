package com.example.bookedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class LoginActivity extends AppCompatActivity {

    //Declare Variables
    TwitterLoginButton loginButton;
    EditText et_userName, et_passWord;
    Button btn_login, btn_sign;
    DBHelp db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_login);

        db = new DBHelp(this);


        et_userName = findViewById(R.id.et_userName);
        et_passWord = findViewById(R.id.et_passWord);
        btn_login = findViewById(R.id.btn_login);
        btn_sign = findViewById(R.id.btn_sign);


             btn_login.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     String user = et_userName.getText().toString().trim();
                     String pwd = et_passWord.getText().toString().trim();
                     Boolean res = db.checkUser(user,pwd);
                     if(res == true){
                         Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                         startActivity(loginSuccess);
                     }
                     else{
                         Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                     }
                 }
             });

                btn_sign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                });

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;

                login(session);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(LoginActivity.this, "Twitter Login Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void login(TwitterSession session){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }



}