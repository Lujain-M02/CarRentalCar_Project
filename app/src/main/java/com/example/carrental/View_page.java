package com.example.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class View_page extends AppCompatActivity {

    ImageButton btn_back;
    String UserName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        btn_back=findViewById(R.id.btn_back);
        UserName=getIntent().getStringExtra("UserName");




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