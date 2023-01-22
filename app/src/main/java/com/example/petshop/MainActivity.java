package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
<<<<<<< HEAD
import android.database.Cursor;
=======
>>>>>>> 158427b8d51c632f2c60bf812594d40ca3321923
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
<<<<<<< HEAD
    protected Cursor cursor;
    Button btnNext;
=======

    Button btnGetStart;
>>>>>>> 158427b8d51c632f2c60bf812594d40ca3321923
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(pindah);
=======
        btnGetStart = findViewById(R.id.button2);
        btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent pindah = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(pindah);
>>>>>>> 158427b8d51c632f2c60bf812594d40ca3321923
            }
        });
    }
}