package com.example.sumuppro;

import android.graphics.Bitmap;

public class Resturant {
    private String name;
    private String chefName;
    private double avgPrice;
    private Bitmap photo;

    public Resturant(String name, String chefName, double avgPrice, Bitmap photo) {
        this.name = name;
        this.chefName = chefName;
        this.avgPrice = avgPrice;
        this.photo = photo;
    }
    public String getName() {
        return name;
    }

    public String getChefName() {
        return chefName;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public Bitmap getPhoto() {
        return photo;
    }
}
