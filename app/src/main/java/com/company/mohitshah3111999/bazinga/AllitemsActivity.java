package com.company.mohitshah3111999.bazinga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.company.mohitshah3111999.bazinga.Main3Activity.itemName;
import static com.company.mohitshah3111999.bazinga.Main3Activity.quantity;

public class AllitemsActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_allitems);
        ImageView imageView = findViewById(R.id.newdp);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mycartActivity.class);
                intent.putExtra("quantity", quantity);
                intent.putExtra("itemname", itemName);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int obj = intent.getIntExtra("position", 0);
        List<DataItemForVerticalAdapter> list = new ArrayList<>();
        listView = findViewById(R.id.similaritems);

        if (obj == 0) {
            setTitle("Maggie");
            Resources res = getResources();
            Bitmap src = BitmapFactory.decodeResource(res, R.drawable.maggie);
            RoundedBitmapDrawable dr =
                    RoundedBitmapDrawableFactory.create(res, src);
            dr.setCornerRadius(200);
            imageView.setImageDrawable(dr);
            list.add(new DataItemForVerticalAdapter("Veg Maggie\nRs. 50", R.drawable.maggie, getResources()));
            list.add(new DataItemForVerticalAdapter("Bread Maggie\nRs. 60", R.drawable.breadmaggie, getResources()));
            list.add(new DataItemForVerticalAdapter("Cheese Maggie\nRs. 60", R.drawable.cheesemaggie, getResources()));
            list.add(new DataItemForVerticalAdapter("Masala Maggie\nRs. 40", R.drawable.masalamaggie, getResources()));
            list.add(new DataItemForVerticalAdapter("Soupy Maggie\nRs. 50", R.drawable.soupymaggie, getResources()));
            list.add(new DataItemForVerticalAdapter("Egg Bhurji Maggie\nRs. 70", R.drawable.eggbhurjimaggie, getResources()));
            list.add(new DataItemForVerticalAdapter("Chicken Maggie\nRs. 80", R.drawable.chickenmaggie, getResources()));
        }else if(obj == 1){
            Resources res = getResources();
            Bitmap src = BitmapFactory.decodeResource(res, R.drawable.manchurian);
            RoundedBitmapDrawable dr =
                    RoundedBitmapDrawableFactory.create(res, src);
            dr.setCornerRadius(200);
            imageView.setImageDrawable(dr);
            setTitle("Manchurian");
            list.add(new DataItemForVerticalAdapter("Dry Chicken   Manchurian\nRs. 100", R.drawable.chickenmanchurian, getResources()));
            list.add(new DataItemForVerticalAdapter("Chilli Paneer\nRs. 80", R.drawable.chillipaneer, getResources()));
            list.add(new DataItemForVerticalAdapter("Chowmein\nRs. 60", R.drawable.chowmein, getResources()));
            list.add(new DataItemForVerticalAdapter("Hakka Noodles\nRs. 50", R.drawable.hakkanoodles, getResources()));
            list.add(new DataItemForVerticalAdapter("Manchow Soup\nRs. 30", R.drawable.manchowsoup, getResources()));
            list.add(new DataItemForVerticalAdapter("Spring Rolls\nRs. 50", R.drawable.springrolls, getResources()));
        }else if (obj == 2){
            Resources res = getResources();
            Bitmap src = BitmapFactory.decodeResource(res, R.drawable.vegcheesesandwich);
            RoundedBitmapDrawable dr =
                    RoundedBitmapDrawableFactory.create(res, src);
            dr.setCornerRadius(200);
            imageView.setImageDrawable(dr);
            setTitle("Sandwich");
            list.add(new DataItemForVerticalAdapter("Veg Cheese Sandwich\nRs. 50", R.drawable.vegcheesesandwich, getResources()));
            list.add(new DataItemForVerticalAdapter("Corn & Capsicum Sandwich\nRs. 50", R.drawable.corncapcicumsandwich, getResources()));
            list.add(new DataItemForVerticalAdapter("Egg Sandwich\nRs. 70", R.drawable.eggsandwich, getResources()));
            list.add(new DataItemForVerticalAdapter("Grilled Sandwich\nRs. 60", R.drawable.grilledsandwich, getResources()));
            list.add(new DataItemForVerticalAdapter("Paneer Sandwich\nRs. 80", R.drawable.paneersandwich, getResources()));
            list.add(new DataItemForVerticalAdapter("Mayonnaise Sandwich\nRs. 75", R.drawable.mayonnaisesandwich, getResources()));
        }
        CustomAdapterVertical customAdapterVertical = new CustomAdapterVertical(this, list);
        listView.setAdapter(customAdapterVertical);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
