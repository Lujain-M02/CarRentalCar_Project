package com.example.carrental;

import android.graphics.Bitmap;

public class Car {

    private int id;
    private String Name;
    private int NumberOfPassenger;
    private String type;

    private int price;

    private Bitmap image;

    private String owner_name;
    public Car(int id, String name, int numberOfPassenger, String type, int price, Bitmap image, String owner_name) {
        this.id = id;
        Name = name;
        NumberOfPassenger = numberOfPassenger;
        this.type = type;
        this.price = price;
        this.image = image;
        this.owner_name = owner_name;
    }



    /*public Car(int id, String name, int numberOfPassenger, String type, int price, Bitmap image) {
        this.id = id;
        Name = name;
        NumberOfPassenger = numberOfPassenger;
        this.type = type;
        this.price = price;
        this.image = image;
    }*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumberOfPassenger() {
        return NumberOfPassenger;
    }

    public void setNumberOfPassenger(int numberOfPassenger) {
        NumberOfPassenger = numberOfPassenger;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }
    @Override
    public String toString() {
        return "" + Name;
    }
}
