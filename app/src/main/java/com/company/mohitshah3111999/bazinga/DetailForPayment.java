package com.company.mohitshah3111999.bazinga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailForPayment extends AppCompatActivity {
    TextView name, numbers, room, hostel;
    Button continuation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_for_payment);
        setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.Name);
        numbers = findViewById(R.id.PhoneNumber);
        room = findViewById(R.id.RoomNo);
        hostel = findViewById(R.id.Hostel);
        continuation = findViewById(R.id.Continue);

        continuation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().length() != 0 || numbers.getText().length() !=0 || room.getText().length() != 0 || hostel.getText().length() != 0) {
//                    TODO Add payment method here
                    myordersActivity.name = name.getText().toString();
                    myordersActivity.room = room.getText().toString();
                    myordersActivity.hostel = hostel.getText().toString();
                    myordersActivity.number = numbers.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), myordersActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
