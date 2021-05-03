package com.manav.allinoneandroidapp.model;

public class DataItem {

    private int image; // from drawable we're going to access images as static so they have interger value
    private String itemName;

    public DataItem() {
    }

    public DataItem(int image, String itemName) {
        this.image = image;
        this.itemName = itemName;
    }

    public int getImageURL() {
        return image;
    }

    public void setImageURL(int image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
