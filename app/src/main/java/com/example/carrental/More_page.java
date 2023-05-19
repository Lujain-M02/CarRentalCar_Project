package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class More_page extends AppCompatActivity {
    Button btn_myCars;
    ImageButton btn_logout;

    TextView tv_username;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_page);

        //logged in username information
        String UserName=getIntent().getStringExtra("UserName");

        btn_myCars=findViewById(R.id.btn_myCars);
        btn_logout=findViewById(R.id.btn_logout);
        tv_username=findViewById(R.id.tv_username);
        tv_username.setText(UserName);


        btn_myCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),My_Cars.class);
                intent.putExtra( "UserName",UserName);
                startActivity(intent);
            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });
    }

}