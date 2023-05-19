package com.example.carrental;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    ImageButton btn_add;
    ImageButton btn_more;

    //ListView lv_carList;

    RecyclerView rv_cars;
    rv_Adapter AdapterObject;
    ArrayAdapter CarArrayAdapter;
    CarDataBase dataBaseHelper;
    //Button linksign;

    String UserName;
    ArrayList<Car> viewList=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add=findViewById(R.id.btn_add);
        btn_more=findViewById(R.id.btn_more);
        rv_cars=findViewById(R.id.rv_cars);
        //linksign = findViewById(R.id.linksign);



        //logged in username information
        UserName=getIntent().getStringExtra("UserName");
        TextView textView=findViewById(R.id.textView);
        textView.setText(UserName);

        dataBaseHelper = new CarDataBase(MainActivity.this);
        viewList=(ArrayList<Car>)dataBaseHelper.getEveryone();
        //ShowStudentsOnListView(dataBaseHelper);
        getData();



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Add_Car.class);
                intent.putExtra( "UserName",UserName);
                startActivity(intent);

            }
        });

        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),More_page.class);
                intent.putExtra( "UserName",UserName);
                startActivity(intent);
            }
        });

        /*linksign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);

            }
        });*/
    }


    //private void ShowStudentsOnListView(CarDataBase dataBaseHelper) {
    //    CarArrayAdapter = new ArrayAdapter<Car>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
    //   lv_carList.setAdapter(CarArrayAdapter);
    //}


    public void getData(){
       AdapterObject=new rv_Adapter(MainActivity.this,viewList,this);
        //AdapterObject=new rv_Adapter(MainActivity.this,(ArrayList<Car>) dataBaseHelper.getEveryone(),this);
        rv_cars.setHasFixedSize(true);
        rv_cars.setLayoutManager(new LinearLayoutManager(this));
        rv_cars.setAdapter(AdapterObject);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(),View_page.class);
        intent.putExtra( "UserName",UserName);
        intent.putExtra( "carID",viewList.get(position).getId());
        intent.putExtra( "carName",viewList.get(position).getName());
        intent.putExtra( "carType",viewList.get(position).getType());
        intent.putExtra( "carPassenger",viewList.get(position).getNumberOfPassenger());
        intent.putExtra( "carPrice",viewList.get(position).getPrice());
        intent.putExtra( "carOwnerName",viewList.get(position).getOwner_name());
        //intent.putExtra( "carImage",viewList.get(position).getImage());
        startActivity(intent);


    }
}


