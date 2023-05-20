package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class My_Cars extends AppCompatActivity {

    RecyclerView rv_MyCar;
    MyCar_rv_Adapter MyCarAdapterObject;
    CarDataBase dataBaseHelper;

    ImageButton BackToHome;
    String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);

        BackToHome = findViewById(R.id.BackToHome);
        rv_MyCar=findViewById(R.id.rv_MyCar);

        //logged in username
        UserName=getIntent().getStringExtra("UserName");

        dataBaseHelper = new CarDataBase(My_Cars.this);
        //ShowStudentsOnListView(dataBaseHelper);
        getDataToDelete();


        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),More_page.class);
                intent.putExtra( "UserName",UserName);
                startActivity(intent);
            }
        });

    }


    //////===============this method will change in the future to represent car List to the same owner ==================================
    /////////this change has been implemented succesfully
    public void getDataToDelete(){
        List<Car> ViewList = new ArrayList<>();
        for(int i=0;i<dataBaseHelper.getEveryone().size();i++){//this for loop to represent a cars that owned by the logged in user
            if(dataBaseHelper.getEveryone().get(i).getOwner_name().equals(UserName)){
                ViewList.add(dataBaseHelper.getEveryone().get(i));
            }
        }
        MyCarAdapterObject=new MyCar_rv_Adapter((ArrayList<Car>) ViewList , My_Cars.this);

        //MyCarAdapterObject=new MyCar_rv_Adapter((ArrayList<Car>) dataBaseHelper.getEveryone() , My_Cars.this);
        rv_MyCar.setHasFixedSize(true);
        rv_MyCar.setLayoutManager(new LinearLayoutManager(this));
        rv_MyCar.setAdapter(MyCarAdapterObject);
    }
}

