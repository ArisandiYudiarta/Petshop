package com.example.petshop.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class  DataHelpers extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pet_shop.db";
    private static final String CREATE_TABLE_ANIMALS =
            "CREATE TABLE " + DatabaseTableAnimals.AnimalsColumns.TABLE_NAME + " (" +
            DatabaseTableAnimals.AnimalsColumns._ID + " INTEGER PRIMARY KEY, " +
            DatabaseTableAnimals.AnimalsColumns.COLUMN_PET_TITLE + " TEXT, " +
            DatabaseTableAnimals.AnimalsColumns.COLUMN_PET_AGE + " INT, " +
            DatabaseTableAnimals.AnimalsColumns.COLUMN_PET_DESCRIPTION + " TEXT," +
            DatabaseTableAnimals.AnimalsColumns.COLUMN_PET_CATEGORY + " TEXT, " +
            DatabaseTableAnimals.AnimalsColumns.COLUMN_PET_GENDER + " TEXT, " +
            DatabaseTableAnimals.AnimalsColumns.COLUMN_PET_WEIGHT + " REAL, " +
            DatabaseTableAnimals.AnimalsColumns.COLUMN_PET_HEIGHT + " REAL, " +
            DatabaseTableAnimals.AnimalsColumns.COLUMN_PET_PHOTO + " BLOB )";

    private static final String CREATE_TABLE_HISTORY = "CREATE TABLE " + DatabaseTableHistory.AnimalsHistory.TABLE_NAME + " (" +
            DatabaseTableHistory.AnimalsHistory._ID + " INTEGER PRIMARY KEY, " +
            DatabaseTableHistory.AnimalsHistory.COLUMN_PET_TITLE + " TEXT, " +
            DatabaseTableHistory.AnimalsHistory.COLUMN_PET_AGE + " INT, " +
            DatabaseTableHistory.AnimalsHistory.COLUMN_PET_CATEGORY + " TEXT, " +
            DatabaseTableHistory.AnimalsHistory.COLUMN_PET_GENDER + " TEXT )";

    private static final String DELETE_TABLE_ANIMALS = "DROP TABLE IF EXISTS " + DatabaseTableAnimals.AnimalsColumns.TABLE_NAME;
    private static final String DELETE_TABLE_HISTORY = "DROP TABLE IF EXISTS " + DatabaseTableHistory.AnimalsHistory.TABLE_NAME;

    public DataHelpers(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ANIMALS);
        sqLiteDatabase.execSQL(CREATE_TABLE_HISTORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE_ANIMALS);
        sqLiteDatabase.execSQL(DELETE_TABLE_HISTORY);
        onCreate(sqLiteDatabase);
    }
}
