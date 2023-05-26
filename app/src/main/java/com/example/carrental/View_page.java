package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class View_page extends AppCompatActivity {

    ImageButton btn_back;
    Button btn_rent;
    String UserName;
    ImageView car_image1;
    TextView carname_v;
    TextView passenger_v;
    TextView type_v ;
    TextView price_v ;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        btn_back=findViewById(R.id.btn_back);
        UserName=getIntent().getStringExtra("UserName");
        btn_rent=findViewById(R.id.rentButton);
        //carname_v.findViewById(R.id.carname_v);





        //all the car information
        Intent intent = getIntent();
        int carID = intent.getIntExtra("carID", 0);
        String carName = intent.getStringExtra("carName");
        String carType = intent.getStringExtra("carType");
        int carPassenger = intent.getIntExtra("carPassenger", 0);
        double carPrice = intent.getIntExtra("carPrice", 0);//غيرته لانجر
        String carOwnerName = intent.getStringExtra("carOwnerName");

        byte[] byteArray = intent.getByteArrayExtra("carImage");
        Bitmap carImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        car_image1=findViewById(R.id.car_image1);
        car_image1.setImageBitmap(carImage);
        carname_v=findViewById(R.id.carname_v);
        carname_v.setText(carName);
        passenger_v=findViewById(R.id.passenger_v);
        passenger_v.setText(String.valueOf(carPassenger));
        type_v=findViewById(R.id.type_v);
        type_v.setText(carType);
        price_v=findViewById(R.id.price_v);
        price_v.setText(String.valueOf(carPrice));


        btn_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                RentalApplication rent ;
                CarDataBase DB1 = new CarDataBase(View_page.this);
                Car car = DB1.getCarObject(carID);




                    try {
                        rent = new RentalApplication(1,car.getOwner_name(),carID);
                        //car type is int!!!!!!!!!!!!!!!
                    } catch (Exception e) {
                        rent = new RentalApplication(1,car.getOwner_name(),carID);
                    }
                    CarDataBase DB2 = new CarDataBase(View_page.this);
                    boolean success = DB2.rentCar(car,rent);
                    //Toast.makeText(Add_Car.this, "mm: " + success, Toast.LENGTH_SHORT).show();
                    Toast.makeText(View_page.this, success ? "the car has been added successfully" : "Error happened", Toast.LENGTH_SHORT).show();



                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra( "UserName",UserName);
                    startActivity(intent);

            }
        });





        //this button is to go back to main activity
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra( "UserName",UserName);
                startActivity(intent);
            }
        });
    }
}