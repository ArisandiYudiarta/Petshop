package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.petshop.database.DataHelpers;

public class DetailHewan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelpers dataHelpers;
    Button button;
    TextView name,type,gender,age,detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hewan);
        dataHelpers = new DataHelpers(this);
        name = findViewById(R.id.textView3);
//        type = findViewById(R.id.etPetsType);
//        gender = findViewById(R.id.etPetsGeender);
        age = findViewById(R.id.textViewValueUmur);
        detail = findViewById(R.id.textViewPetStory);

        SQLiteDatabase db = dataHelpers.getReadableDatabase();
        cursor = db.rawQuery ("SELECT * FROM pet_animals_table Where pet_name = '" +
                getIntent().getStringExtra("Pet_name")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount() >0 ){
            cursor.moveToPosition(0);
            name.setText(cursor.getString(0).toString());
            age.setText(cursor.getString(1).toString());
            detail.setText(cursor.getString(2).toString());
        }
    }
}