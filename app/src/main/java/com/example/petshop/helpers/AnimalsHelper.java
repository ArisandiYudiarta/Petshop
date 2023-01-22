package com.example.petshop.helpers;

import static android.provider.BaseColumns._ID;
import static com.example.petshop.database.DatabaseTableAnimals.AnimalsColumns.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.petshop.database.DataHelpers;

public class AnimalsHelper {

//    inisialisasi property untuk nama database
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DataHelpers dataBaseHelpers;
    private static AnimalsHelper INSTANCE;

//    inisilisasi library SQLlite
    private static SQLiteDatabase database;

//    membuat construct
    public AnimalsHelper(Context context){
        dataBaseHelpers = new DataHelpers(context);
    }

// inisliasasi database yang dibuat
    public static AnimalsHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new AnimalsHelper(context);
                }
            }
        }
        return INSTANCE;
    }

//    membuka koneksi database
        public void open() throws SQLException {
            database = dataBaseHelpers.getWritableDatabase();
        }

// menutup koneksi database

    public void close(){
        dataBaseHelpers.close();

        if(database.isOpen()){
            database.close();
        }
    }

    //    Query Mengambil semua data dari database
    public Cursor queryAll(){
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    //    Query Mengambil data berdasarkan ID
    public Cursor queryById(String id){
        return database.query(DATABASE_TABLE,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    //    method untuk menyimpan insert
    public long insert(ContentValues values){
        return database.insert(DATABASE_TABLE, null, values);
    }

    //    method untuk menyimpan perubahan
    public int update(String id, ContentValues values){
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    //    method untuk menghapus
    public int deleteById(String id){
        return database.delete(DATABASE_TABLE, _ID + " = " + id, null);
    }



}
