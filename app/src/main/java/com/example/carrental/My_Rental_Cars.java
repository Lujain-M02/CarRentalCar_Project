package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class My_Rental_Cars extends AppCompatActivity {

    ImageButton btn_back;
    Rental_Adapter rentalAdapter;
    RecyclerView rv_MyCar;
    CarDataBase dataBaseHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rental_cars);

        btn_back=findViewById(R.id.btn_back);
        rv_MyCar=findViewById(R.id.rv_MyCar);
        String UserName=getIntent().getStringExtra("UserName");
        dataBaseHelper = new CarDataBase(this);
        getReturned();


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),More_page.class);
                intent.putExtra( "UserName",UserName);
                startActivity(intent);
            }
        });


    }

    public void getReturned(){
        List<RentalApplication> ViewList = new ArrayList<>();
        for(int i=0;i<dataBaseHelper.getRentals().size();i++){//this for loop to represent a cars that owned by the logged in user
                ViewList.add(dataBaseHelper.getRentals().get(i));
        }
        rentalAdapter=new Rental_Adapter((ArrayList<RentalApplication>) ViewList , this);

        //MyCarAdapterObject=new MyCar_rv_Adapter((ArrayList<Car>) dataBaseHelper.getEveryone() , My_Cars.this);
        rv_MyCar.setHasFixedSize(true);
        rv_MyCar.setLayoutManager(new LinearLayoutManager(this));
        rv_MyCar.setAdapter(rentalAdapter);
    }

}

