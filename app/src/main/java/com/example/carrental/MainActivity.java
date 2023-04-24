package com.example.carrental;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_add;
    Button btn_more;

    //ListView lv_carList;

    RecyclerView rv_cars;
    rv_Adapter AdapterObject;
    ArrayAdapter CarArrayAdapter;
    CarDataBase dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add=findViewById(R.id.btn_add);
        btn_more=findViewById(R.id.btn_more);
        rv_cars=findViewById(R.id.rv_cars);


        dataBaseHelper = new CarDataBase(MainActivity.this);
        //ShowStudentsOnListView(dataBaseHelper);
        getData();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Add_Car.class);
                startActivity(intent);

            }
        });

        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),More_page.class);
                startActivity(intent);
            }
        });

    }


    //private void ShowStudentsOnListView(CarDataBase dataBaseHelper) {
    //    CarArrayAdapter = new ArrayAdapter<Car>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
    //   lv_carList.setAdapter(CarArrayAdapter);
    //}

    public void getData(){
        AdapterObject=new rv_Adapter((ArrayList<Car>) dataBaseHelper.getEveryone());
        rv_cars.setHasFixedSize(true);
        rv_cars.setLayoutManager(new LinearLayoutManager(this));
        rv_cars.setAdapter(AdapterObject);
    }

}


