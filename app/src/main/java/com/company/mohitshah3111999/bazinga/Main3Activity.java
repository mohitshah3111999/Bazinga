package com.company.mohitshah3111999.bazinga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.provider.FontsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseUser;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

// TODO Change the size of all the images after coming back to the college.

public class Main3Activity extends AppCompatActivity {
    public static ArrayList<Integer> quantity = new ArrayList<>();
    public static ArrayList<String> itemName = new ArrayList<>();


    public void fabClick(View view){
        Intent intent = new Intent(getApplicationContext(), mycartActivity.class);
        intent.putExtra("quantity", quantity);
        intent.putExtra("itemname", itemName);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu:
                return true;
            case R.id.mycart:
                Intent intent1 = new Intent(getApplicationContext(), mycartActivity.class);
                intent1.putExtra("quantity", quantity);
                intent1.putExtra("itemname", itemName);
                startActivity(intent1);
                return true;
            case R.id.myorders:
                Intent intent2 = new Intent(getApplicationContext(), myordersActivity.class);
                startActivity(intent2);
                return true;
            case R.id.offers:
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.cocacola);
                String message = "\t\t\t\t\t\t\t\t\t\t\t\t\t\tOFFER\n\n\t\t\t\t\t\t\tGet a free 750ml\n\t\t\t\t\tcoca-cola on a minimum\n\t\t\t\t\t\t\torder of Rs.500 or a\n\t\t\t\t\t\t250ml coca-cola on a\n\t\t\t\tminimum order of Rs.250";
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                        alert
                        .setMessage(message)
                        .setView(imageView);
                AlertDialog dialog = alert.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GREEN));
                dialog.getWindow().setLayout(800, 1200);
                return true;
            case R.id.contactus:
                Intent intent3 = new Intent(getApplicationContext(), contactActivity.class);
                startActivity(intent3);
                return true;
            case R.id.signout:
                ParseUser.logOut();
                Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent4);
                finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageView imageView = findViewById(R.id.slideshow);

//        Code for rounded corner.
        Resources res = getResources();
        Bitmap src = BitmapFactory.decodeResource(res, R.drawable.manchurian);
        RoundedBitmapDrawable dr =
                RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(150);
        imageView.setImageDrawable(dr);

        TextView textView = findViewById(R.id.Closed);
        textView.setAlpha(0);

        List<Dataitem> list = new ArrayList<>();
        ListView listView = findViewById(R.id.all_items);
        list.add(new Dataitem(R.drawable.maggie, "Maggie", getResources()));
        list.add(new Dataitem(R.drawable.manchurian, "Manchurian", getResources()));
        list.add(new Dataitem(R.drawable.vegcheesesandwich, "Sandwich", getResources()));

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.formain3activity, list);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AllitemsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }
}
