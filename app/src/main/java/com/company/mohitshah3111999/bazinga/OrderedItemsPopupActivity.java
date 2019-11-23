package com.company.mohitshah3111999.bazinga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class OrderedItemsPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items_popup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView listView = findViewById(R.id.popsItem);
        final ArrayList<String> fullInfo = new ArrayList<>();

        final Intent intent = getIntent();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fullInfo){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position,convertView,parent);
                item.setTextColor(Color.parseColor("#FFFFFFFF"));
                item.setTypeface(item.getTypeface(), Typeface.BOLD);
                item.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
                return item;
            }
        };


        ParseQuery<ParseObject> parseQuery = new ParseQuery<>("OrderInfo");
        parseQuery.whereEqualTo("Username", ParseUser.getCurrentUser().getUsername());
        parseQuery.whereEqualTo("Time", intent.getStringExtra("time"));
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    for(ParseObject object: objects){
                        ArrayList quantity = (ArrayList) objects.get(objects.indexOf(object)).get("quantity");
                        ArrayList itemName = (ArrayList) objects.get(objects.indexOf(object)).get("itemName");

                        Log.i("quantity is ", quantity.toString());
                        for(Object name: itemName){
                            String info = itemName.get(itemName.indexOf(name)) + "          " + " Qty : " + quantity.get(itemName.indexOf(name));
                            if(Integer.valueOf(quantity.get(itemName.indexOf(name)).toString()) > 0) {
                                fullInfo.add(info);
                            }
                        }
                    }
                }
                listView.setAdapter(arrayAdapter);

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
