package com.company.mohitshah3111999.bazinga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignLogActivity extends AppCompatActivity {

    TextView email, password, username;
    Button signupuser, loginuser;

    public void signup(View view){
        final ParseUser user = new ParseUser();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setUsername(username.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.i("Success", "Done");
                    Toast.makeText(SignLogActivity.this, "Successfully Signed up", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SignLogActivity.this, "User Already exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(View view){
        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null){
                    Log.i("Success", "Log in");
                    Toast.makeText(SignLogActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SignLogActivity.this, "Try sign up", Toast.LENGTH_SHORT).show();
                    Log.i("Error", e.toString());
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_log);

        signupuser = findViewById(R.id.Signin2);
        loginuser = findViewById(R.id.Login2);
        username = findViewById(R.id.username);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
    }
}
