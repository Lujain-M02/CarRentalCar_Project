package com.example.carrental;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Add_Car extends AppCompatActivity {

    Button btn_addcar;
    EditText et_name,et_type,et_passenger, et_price;

    ImageView iv_image;

    final static int PICK_IMAGE_REQUEST=100;

    Uri ImageFilePath;
//lll2
    Bitmap ImageToStore;
    ImageButton btn_back;


    ArrayAdapter<Car> CostumerArray;
    CarDataBase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        btn_addcar=findViewById(R.id.btn_addcar);
        et_name=findViewById(R.id.et_name);
        et_type=findViewById(R.id.et_type);
        et_passenger=findViewById(R.id.et_passenger);
        et_price=findViewById(R.id.et_price);
        iv_image=findViewById(R.id.iv_image);
        btn_back=findViewById(R.id.btn_back);


        //logged in username
        String UserName=getIntent().getStringExtra("UserName");



        btn_addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String CarName = et_name.getText().toString();
                String PassNum = et_passenger.getText().toString();
                String type = et_type.getText().toString();
                String price = et_price.getText().toString();


                Car c1 ;




                if(CarName.equals("") || PassNum.equals("") || type.equals("") || price.equals("") || ImageToStore==null) {
                    Toast.makeText(Add_Car.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                /*}else if (PassNum.equals("")) {
                    Toast.makeText(Add_Car.this, "Please enter the Number of Passenger", Toast.LENGTH_SHORT).show();
                } else if (type.equals("")) {
                    Toast.makeText(Add_Car.this, "Please enter the Type of the car", Toast.LENGTH_SHORT).show();
                }else if (price.equals("")) {
                    Toast.makeText(Add_Car.this, "Please enter the Price of the car", Toast.LENGTH_SHORT).show();
                }else if (ImageToStore==null) {
                    Toast.makeText(Add_Car.this, "Please upload image", Toast.LENGTH_SHORT).show();*/
                }else {


                    try {
                        c1 = new Car(1, et_name.getText().toString(), Integer.parseInt(et_passenger.getText().toString()), et_type.getText().toString(), Integer.parseInt(et_price.getText().toString()), ImageToStore,UserName);
                        //Toast.makeText(Add_Car.this, c1.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(Add_Car.this, "fill the information", Toast.LENGTH_SHORT).show();
                        c1 = new Car(-1, "none", 0, "none", 0, null,"none");

                    }
                    CarDataBase DB = new CarDataBase(Add_Car.this);
                    boolean success = DB.addOne(c1);
                    //Toast.makeText(Add_Car.this, "mm: " + success, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Add_Car.this, success ? "the car has been added successfully" : "Error happened", Toast.LENGTH_SHORT).show();



                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra( "UserName",UserName);
                    startActivity(intent);
                }

            }
        } );
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra( "UserName",UserName);
                startActivity(intent);
            }
        });
    }


///this 2 method for uploading image from gallary on the Emulator **************************************************************
    public void ChooseImage(View ObjectView){
        try {
            Intent ObjectIntent=new Intent();
            ObjectIntent.setType("image/*");
            ObjectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(ObjectIntent,PICK_IMAGE_REQUEST);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK ){
                ImageFilePath=data.getData();
                ImageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),ImageFilePath);
                iv_image.setImageBitmap(ImageToStore);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}