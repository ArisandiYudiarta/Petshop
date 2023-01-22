package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petshop.database.DataHelpers;

public class AddAnimalActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelpers dataHelpers;
    Button btnSimpan,btnCancel;
    EditText etPetsName,type,gender,age,detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        dataHelpers = new DataHelpers(this);
        etPetsName = findViewById(R.id.etPetsName);
        type = findViewById(R.id.etPetsType);
        gender = findViewById(R.id.etPetsGeender);
        age = findViewById(R.id.etPetsAge);
        detail = findViewById(R.id.etPetsDetail);
        btnSimpan = findViewById(R.id.btnSimpanAdd);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dataHelpers.getWritableDatabase();
                db.execSQL("INSERT INTO pet_animals_table (Pet_name, Pet_category, Pet_gender, Pet_age, Pet_desc) values('"+
                        etPetsName.getText().toString()+"','" +
                        type.getText().toString()+"','" +
                        gender.getText().toString()+"','" +
                        age.getText().toString()+"','" +
                        detail.getText().toString()+"')");
                Toast.makeText(AddAnimalActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                DashboardActivity.dashboardActivity.RefreshList();
                finish();
            }
        });
        btnCancel = findViewById(R.id.btnCancelAdd);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(AddAnimalActivity.this, DashboardActivity.class);
                startActivity(pindah);
            }
        });
    }
}