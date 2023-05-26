package com.example.carrental;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RentalApplication {
    private int id ;
    private String renter_name;
    private int car_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRenter_name() {
        return renter_name;
    }

    public void setRenter_name(String renter_name) {
        this.renter_name = renter_name;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }


    public RentalApplication(int id, String renter_name, int car_id) {
    this.id = id;
    this.renter_name = renter_name;
    this.car_id = car_id;
}


}