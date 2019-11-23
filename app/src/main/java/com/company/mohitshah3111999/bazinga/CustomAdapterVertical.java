package com.company.mohitshah3111999.bazinga;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import java.util.List;

import static com.company.mohitshah3111999.bazinga.Main3Activity.itemName;
import static com.company.mohitshah3111999.bazinga.Main3Activity.quantity;

public class CustomAdapterVertical extends BaseAdapter {
    Context context;
    List<DataItemForVerticalAdapter> items;

    public CustomAdapterVertical(Context context, List<DataItemForVerticalAdapter> list){
        this.context = context;
        this.items = list;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        for(String name: itemName){
            for(DataItemForVerticalAdapter nameOfItems: items){
                if(name.equals(nameOfItems.infoAboutitem)){
                    nameOfItems.num = quantity.get(itemName.indexOf(name));
                }
            }
        }
        Log.i("list is", itemName.toString());
        if (items.get(position).num >= 1){
            items.get(position).increment = items.get(position).num + "+";
            items.get(position).decrement = "-";
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int[] p = {0};
        convertView = LayoutInflater.from(context).inflate(R.layout.verticaldataitem, parent, false);
        final Button incre = convertView.findViewById(R.id.incre);
        final Button decre = convertView.findViewById(R.id.decre);
        final TextView information = convertView.findViewById(R.id.information);
        ImageView pic = convertView.findViewById(R.id.itemImage);

        if (items.get(position).num >= 1) {
            incre.setText(items.get(position).increment);
            decre.setText(items.get(position).decrement);
        }

        information.setText(items.get(position).infoAboutitem);

        Resources res = items.get(position).resources;
        Bitmap src = BitmapFactory.decodeResource(res, items.get(position).imageid);
        RoundedBitmapDrawable dr =
                RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(Math.min(dr.getMinimumWidth(),dr.getMinimumHeight()));
        pic.setImageDrawable(dr);

        incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.get(position).num += 1;
                items.get(position).increment = items.get(position).num + "+";
                items.get(position).decrement = "-";
                incre.setText(items.get(position).increment);
                decre.setText(items.get(position).decrement);
                if(itemName.size() == 0){
                    itemName.add(items.get(position).infoAboutitem);
                    quantity.add(items.get(position).num);
                }
                for(String name: itemName){
                    Log.i("Name is", String.valueOf(name.equals(items.get(position).infoAboutitem)));
                    if(name.equals(items.get(position).infoAboutitem)){
                        quantity.set(itemName.indexOf(name), items.get(position).num);
                        p[0] = 0;
                        break;
                    }else{
                        p[0] = 1;
                    }
                }
                if(p[0] == 1){
                    itemName.add(items.get(position).infoAboutitem);
                    quantity.add(items.get(position).num);
                    p[0] = 0;
                }
            }
        });
        decre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(decre.getText().toString().equals("A")){
                    decre.setText("A");
                }else if (incre.getText().toString().equals("1+")){
                    items.get(position).num -= 1;
                    decre.setText("A");
                    incre.setText("dd");
                }else{
                    items.get(position).num -= 1;
                    decre.setText("-");
                    incre.setText(items.get(position).num + "+");
                }
                if(itemName.size() > 0){
                    for(String name: itemName){
                        if(items.get(position).infoAboutitem.equals(name)){
                            quantity.set(itemName.indexOf(name), items.get(position).num);
                        }
                    }
                }
            }
        });
        return convertView;
    }
}
