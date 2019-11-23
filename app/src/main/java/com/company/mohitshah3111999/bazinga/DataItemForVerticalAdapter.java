package com.company.mohitshah3111999.bazinga;

import android.content.res.Resources;

public class DataItemForVerticalAdapter {
    String infoAboutitem;
    int imageid;
    int num = 0;
    String decrement = "A";
    String increment = "dd";
    Resources resources;

    public DataItemForVerticalAdapter(String text, int imageId, Resources resources){
        this.infoAboutitem = text;
        this.imageid = imageId;
        this.resources = resources;
    }
}
