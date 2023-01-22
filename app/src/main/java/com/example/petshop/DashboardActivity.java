package com.example.petshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.petshop.database.DataHelpers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardActivity extends AppCompatActivity {
    String[] daftar;
    GridView gridView;
    Menu menu;
    Button btnadd;
    protected Cursor cursor;
    DataHelpers database;
    public static DashboardActivity dashboardActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnadd = findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(DashboardActivity.this,AddAnimalActivity.class);
                startActivity(pindah);
            }
        });
        dashboardActivity = this;
        database = new DataHelpers(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pet_animals_table",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i=0; i< cursor.getCount();i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
        }
        gridView = (GridView) findViewById(R.id.mygridviewData);
        gridView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        gridView.setSelected(true);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String selection = daftar[i];
                final CharSequence[] dialogitem = {"Lihat Hewan","Update Hewan","Hapus Hewan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item){
                            case 0:
                                Intent detail = new Intent(getApplicationContext(),DetailHewan.class);
                                detail.putExtra("Pet_name",selection);
                                startActivity(detail);
                                break;
//                            case 1:
//                                Intent update = new Intent(getApplicationContext(),UpdateHewan.class);
//                                update.putExtra("nama",selection);
//                                startActivity(update);
//                                break;
                            case 1:
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.execSQL("DELETE FROM pet_animals_table Where pet_name = '"+ selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) gridView.getAdapter()).notifyDataSetInvalidated();
    }
}