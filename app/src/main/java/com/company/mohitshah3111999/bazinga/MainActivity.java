package com.company.mohitshah3111999.bazinga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    Button signin;

    public void register(View view){
        Intent intent = new Intent(getApplicationContext(), SignLogActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        signin = findViewById(R.id.Signin);

        if (ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(intent);
            finish();
        }

    }
}
