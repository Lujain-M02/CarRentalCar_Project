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
import android.view.View;

public class View_page extends AppCompatActivity {

    ImageButton btn_back;
    Button btn_rent;
    String UserName;
    ImageView car_image1;
    TextView carname_v;
    TextView passenger_v;
    TextView type_v ;
    TextView price_v ;
    TextView tv_msg;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        btn_back=findViewById(R.id.btn_back);
        UserName=getIntent().getStringExtra("UserName");
        btn_rent=findViewById(R.id.rentButton);
        //carname_v.findViewById(R.id.carname_v);
        tv_msg=findViewById(R.id.tv_msg);



        //all the car information
        Intent intent = getIntent();
        int carID = intent.getIntExtra("carID", 0);
        //String ScarID = intent.getStringExtra("carID");/////no need
        //String carName = intent.getStringExtra("carName");
        //String carType = intent.getStringExtra("carType");
        //int carPassenger = intent.getIntExtra("carPassenger", 0);
        //double carPrice = intent.getIntExtra("carPrice", 0);//غيرته لانجر
        //String carOwnerName = intent.getStringExtra("carOwnerName");

        //byte[] byteArray = intent.getByteArrayExtra("carImage");
        //Bitmap carImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        CarDataBase car= new CarDataBase(View_page.this);
        Car viewCar=car.getCarObject(carID);
        String carName=viewCar.getName();
        String carType=viewCar.getType();
        int carPassenger=viewCar.getNumberOfPassenger();
        double carPrice=viewCar.getPrice();
        Bitmap carImage=viewCar.getImage();

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


        CarDataBase DB2 = new CarDataBase(View_page.this);
        //boolean yours = DB2.isYours(carOwnerName,ScarID);
        boolean isrenBefore = DB2.isRented(carID);

        /*if(yours){
            btn_rent.setVisibility(View.INVISIBLE);
            Toast.makeText(View_page.this, yours ? "You can't rent your car!!":"", Toast.LENGTH_SHORT).show();
        }else*/ if(isrenBefore) {
            btn_rent.setEnabled(false);
            btn_rent.getBackground().setAlpha(64);
            tv_msg.setVisibility(View.VISIBLE);
        }

        btn_rent.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (!isrenBefore) {
                    RentalApplication rent;
                    CarDataBase DB1 = new CarDataBase(View_page.this);



                    try {
                        //rent = new RentalApplication(1,car.getOwner_name(),carID);
                        rent = new RentalApplication(1, View_page.this.UserName, carID);


                        //car type is int!!!!!!!!!!!!!!!
                    } catch (Exception e) {
                        rent = new RentalApplication(1, View_page.this.UserName, carID);
                    }

                    CarDataBase DB2 = new CarDataBase(View_page.this);
                    boolean isrenBefore = DB2.isRented(carID);

                    if (!isrenBefore) {
                        boolean success = DB2.rentCar(rent);
                        //Toast.makeText(Add_Car.this, "mm: " + success, Toast.LENGTH_SHORT).show();
                        Toast.makeText(View_page.this, success ? "you have rented the car" : "Error happened", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(getApplicationContext(), rent_conf.class);
                        intent.putExtra("UserName", UserName);
                        startActivity(intent);
                    } else {
                        btn_rent.setEnabled(false);
                    }


                }
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