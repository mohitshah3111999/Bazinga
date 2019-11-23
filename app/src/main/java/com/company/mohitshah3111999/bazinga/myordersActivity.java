package com.company.mohitshah3111999.bazinga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.company.mohitshah3111999.bazinga.mycartActivity.names;
import static com.company.mohitshah3111999.bazinga.mycartActivity.quantity;
import static com.company.mohitshah3111999.bazinga.mycartActivity.total_price;


public class myordersActivity extends AppCompatActivity {
    static String name, room, hostel;
    static String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        final ArrayList<String> time = new ArrayList<>();
        final ArrayList<Integer> cost = new ArrayList<>();

        setTitle("Orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat("hh:mm:ss a");
        Date date = new Date();

        final ParseObject orderInfo = new ParseObject("OrderInfo");

        final ListView orderListView = findViewById(R.id.orderListView);
        final ArrayList<DataItemForOrderItems> list = new ArrayList<>();
        final CustomAdapterForOrderItems customAdapterForOrderItems = new CustomAdapterForOrderItems(this, R.layout.customadapterforordereditems, list);
        final TextView textView, textView1;
        textView = findViewById(R.id.textView5);
        textView1 = findViewById(R.id.textView6);

        if(number != null) {
            textView.setVisibility(View.INVISIBLE);
            textView1.setVisibility(View.INVISIBLE);
            orderInfo.put("Username", ParseUser.getCurrentUser().getUsername());
            orderInfo.put("Date", dateFormat.format(date));
            orderInfo.put("Time", dateFormat1.format(date));
            orderInfo.put("itemName", names);
            orderInfo.put("quantity", quantity);
            orderInfo.put("Hostel", hostel);
            orderInfo.put("Room", room);
            orderInfo.put("Cost", total_price + 10);
            time.add(dateFormat1.format(date));
            orderInfo.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e != null){
                        Log.i("Error", e.toString());
                    }
                }
            });
            Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show();
            list.add(new DataItemForOrderItems(dateFormat.format(date), room, hostel, dateFormat1.format(date), total_price+10));
            orderListView.setAdapter(customAdapterForOrderItems);
            number = null;
        }else{
            ParseQuery<ParseObject> parseQuery = new ParseQuery<>("OrderInfo");
            parseQuery.whereEqualTo("Username", ParseUser.getCurrentUser().getUsername());
            parseQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e == null) {
                        for (ParseObject object : objects) {
                            list.add(new DataItemForOrderItems(objects.get(objects.indexOf(object)).get("Date").toString(), objects.get(objects.indexOf(object)).get("Room").toString(), objects.get(objects.indexOf(object)).get("Hostel").toString(), objects.get(objects.indexOf(object)).get("Time").toString(), Integer.valueOf(objects.get(objects.indexOf(object)).get("Cost").toString())));
                            time.add(objects.get(objects.indexOf(object)).get("Time").toString());
                            cost.add(Integer.valueOf(objects.get(objects.indexOf(object)).get("Cost").toString()));
                            customAdapterForOrderItems.notifyDataSetChanged();
                        }
                    }else{
                        Log.i("Problem", e.toString());
                    }

                    orderListView.setAdapter(customAdapterForOrderItems);
                    if(list.size() > 0){
                        textView.setVisibility(View.INVISIBLE);
                        textView1.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), OrderedItemsPopupActivity.class);
                intent.putExtra("time", time.get(position));
                startActivity(intent);
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
