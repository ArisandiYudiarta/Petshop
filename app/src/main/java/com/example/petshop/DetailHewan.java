package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.petshop.database.DataHelpers;

public class DetailHewan extends AppCompatActivity {

    DataHelpers database;
    TextView nama, umur, deskripsi, kategori, gender, berat, tinggi, id;
    AppCompatButton btnAdopsi;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hewan);

//        Intent pindah = getIntent();

        database = new DataHelpers(this);
        id = findViewById(R.id.displayID);
        nama = findViewById(R.id.displayName);
        umur = findViewById(R.id.textViewValueUmur);
        deskripsi = findViewById(R.id.textViewPetStory);
        kategori = findViewById(R.id.displayBreed);
        gender = findViewById(R.id.textViewGender);
        berat = findViewById(R.id.textViewValueBerat);
        tinggi = findViewById(R.id.textViewTinggi);
        btnAdopsi = findViewById(R.id.btnAdopt);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pet_animals_table WHERE Pet_name = '" +
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount() >0){
            id.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            umur.setText(cursor.getString(2).toString() + " Years");
            deskripsi.setText(cursor.getString(3).toString());
            kategori.setText(cursor.getString(4).toString());
            gender.setText(cursor.getString(5).toString());
            berat.setText(cursor.getString(6).toString() + "Kg");
            tinggi.setText(cursor.getString(6).toString() + "Cm");
        }
    }
}