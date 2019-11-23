package com.company.mohitshah3111999.bazinga;

import android.content.res.Resources;

class Dataitem {
    int image;
    String ordername;
    Resources resources;

    Dataitem(int image, String ordername, Resources resources) {
        this.image = image;
        this.ordername = ordername;
        this.resources = resources;
    }
}
