package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class My_Cars extends AppCompatActivity {

    RecyclerView rv_MyCar;
    MyCar_rv_Adapter MyCarAdapterObject;
    CarDataBase dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);


        rv_MyCar=findViewById(R.id.rv_MyCar);

        dataBaseHelper = new CarDataBase(My_Cars.this);
        //ShowStudentsOnListView(dataBaseHelper);
        getDataToDelete();

    }


    //////===============this method will change in the future to represent car List to the same owner ==================================
    public void getDataToDelete(){
        MyCarAdapterObject=new MyCar_rv_Adapter((ArrayList<Car>) dataBaseHelper.getEveryone());
        rv_MyCar.setHasFixedSize(true);
        rv_MyCar.setLayoutManager(new LinearLayoutManager(this));
        rv_MyCar.setAdapter(MyCarAdapterObject);
    }
}