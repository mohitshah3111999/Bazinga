package com.company.mohitshah3111999.bazinga;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Dataitem> {

    private Context context;
    private int layoutResourceId;
    private List<Dataitem> data;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Dataitem> objects) {
        super(context, resource, objects);

        this.context = context;
        this.data = objects;
        this.layoutResourceId = resource;
    }


    static class Dataholder{

        ImageView titleImage;
        TextView titleTag;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Dataholder holder;
        if (convertView == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(layoutResourceId, parent, false);

            holder = new Dataholder();
            holder.titleImage = convertView.findViewById(R.id.imageforHead);
            holder.titleTag = convertView.findViewById(R.id.textForHead);

            convertView.setTag(holder);
        }else{
            holder = (Dataholder) convertView.getTag();
        }

        Dataitem dataitemForCustomAdapter = data.get(position);
        holder.titleTag.setText(dataitemForCustomAdapter.ordername);
        holder.titleImage.setImageResource(dataitemForCustomAdapter.image);

        Resources res = data.get(position).resources;
        Bitmap src = BitmapFactory.decodeResource(res, data.get(position).image);
        RoundedBitmapDrawable dr =
                RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(200);
        holder.titleImage.setImageDrawable(dr);

        return convertView;
    }

}
