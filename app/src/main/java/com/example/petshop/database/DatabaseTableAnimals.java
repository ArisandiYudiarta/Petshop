package com.example.petshop.database;

import android.provider.BaseColumns;

public class DatabaseTableAnimals {

    public static final class AnimalsColumns implements BaseColumns{
        public static final String TABLE_NAME = "pet_animals_table";

        public static final String COLUMN_PET_TITLE = "Pet_name";
        public static final String COLUMN_PET_CATEGORY = "Pet_category";
        public static final String COLUMN_PET_GENDER = "Pet_gender";
        public static final String COLUMN_PET_AGE = "Pet_age";
        public static final String COLUMN_PET_DESCRIPTION = "Pet_desc";
//        public static final String COLUMN_PET_WEIGHT = "Pet_weight";
//        public static final String COLUMN_PET_HEIGHT = "Pet_height";
//        public static final String COLUMN_PET_PHOTO = "Pet_photo";

    }

}
