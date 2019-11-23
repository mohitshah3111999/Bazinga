package com.company.mohitshah3111999.bazinga;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import static com.company.mohitshah3111999.bazinga.mycartActivity.total_price;


public class CustomAdapterForOrderItems extends ArrayAdapter {
    Context context;
    int resource;
    List<DataItemForOrderItems> objects;

    public CustomAdapterForOrderItems(@NonNull Context context, int resource, @NonNull List<DataItemForOrderItems> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.customadapterforordereditems, parent, false);
        TextView hos, ro, na, nu, tp;
        total_price = 0;
        String number = String.valueOf(objects.get(position).number);
        String amount = String.valueOf(objects.get(position).cost);
        hos = convertView.findViewById(R.id.NumberCustom);
        ro = convertView.findViewById(R.id.RoomCustom);
        na = convertView.findViewById(R.id.NameCustom);
        nu = convertView.findViewById(R.id.HostelCustom);
        tp = convertView.findViewById(R.id.TotalBillAmountCustom);
        hos.setText(objects.get(position).hostel);
        ro.setText(objects.get(position).room);
        na.setText(objects.get(position).name);
        nu.setText(number);
        tp.setText(amount + ".00");
        return convertView;
    }
}
