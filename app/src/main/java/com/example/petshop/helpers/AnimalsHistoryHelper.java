package com.example.petshop.helpers;

import static android.provider.BaseColumns._ID;
import static com.example.petshop.database.DatabaseTableHistory.AnimalsHistory.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.petshop.database.DataHelpers;
import com.example.petshop.database.DatabaseTableAnimals;

public class AnimalsHistoryHelper {

    //inisialisasi property untuk nama database
    private static final String DATABASE_TABLE = DatabaseTableAnimals.AnimalsColumns.TABLE_NAME;
    private static DataHelpers dataBaseHelpers;
    private static AnimalsHistoryHelper INSTANCE;

    //inisilisasi library SQLlite
    private static SQLiteDatabase database;

    //    membuat construct
    public AnimalsHistoryHelper(Context context){
        dataBaseHelpers = new DataHelpers(context);
    }

    // inisliasasi database yang dibuat
    public static AnimalsHistoryHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new AnimalsHistoryHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelpers.getWritableDatabase();
    }

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
