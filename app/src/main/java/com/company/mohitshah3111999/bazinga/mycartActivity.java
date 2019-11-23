package com.company.mohitshah3111999.bazinga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.company.mohitshah3111999.bazinga.Main3Activity.itemName;

public class mycartActivity extends AppCompatActivity {

    static ArrayList<String> names;
    static ArrayList<Integer> quantity;

    ArrayList<String> fullInfo = new ArrayList<>();
    static int total_price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);
        setTitle("Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = findViewById(R.id.textView7);
        TextView foodTotal = findViewById(R.id.FoodpriceTotal);
        TextView delCharge = findViewById(R.id.DeliverychargeTotal);
        TextView total = findViewById(R.id.TotalPrice);
        ListView listView = findViewById(R.id.orders);
        final Intent intent = getIntent();
        names = intent.getStringArrayListExtra("itemname");
        quantity = intent.getIntegerArrayListExtra("quantity");
        ArrayList<Integer> price = new ArrayList<>();
        total_price = 0;
        if (names.size() > 0 || quantity.size() > 0){
            textView.setVisibility(View.INVISIBLE);
            for(String name: names){
                String currentItemName = name.split("\n")[0];
                names.set(names.indexOf(name), currentItemName);
                price.add(Integer.valueOf((name.split("\n")[1]).split(" ")[1]));
            }
//            fullInfo = new ArrayList<>();
            for(String name : names){
                String info = name + "          " + " Qty : " +quantity.get(names.indexOf(name));
                if(quantity.get(names.indexOf(name)) >= 1){
                    fullInfo.add(info);
                }
            }

            for(String name: names){
                int currentPrice = price.get(names.indexOf(name));
                int currentQuantity = quantity.get(names.indexOf(name));
                total_price += currentPrice*currentQuantity;
            }
            if (total_price > 0) {
                foodTotal.setText("Rs. " + total_price + ".00");
                delCharge.setText("Rs. 10.00");
                total.setText("Rs. " + Integer.valueOf(total_price + 10) + ".00");
            }
            if(names.size() == 0 || quantity.size() == 0){
                total.setText("Rs. 0.00");
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fullInfo){
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    TextView item = (TextView) super.getView(position,convertView,parent);
                    item.setTextColor(Color.parseColor("#FFFFFFFF"));
                    item.setTypeface(item.getTypeface(), Typeface.BOLD);
                    item.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
                    return item;
                }
            };
            listView.setAdapter(arrayAdapter);

        }else{
            textView.setVisibility(View.VISIBLE);
            delCharge.setText("Rs. 0.00");
            foodTotal.setText("Rs. 0.00");
            total.setText("Rs. 0.00");
        }

        Button placeOrder = findViewById(R.id.PlaceOrder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullInfo.size() > 0){
                    Intent intent1 = new Intent(getApplicationContext(), DetailForPayment.class);
                    Main3Activity.quantity.clear();
                    itemName.clear();
                    Main3Activity.quantity.clear();
                    startActivity(intent1);
                    finish();
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
