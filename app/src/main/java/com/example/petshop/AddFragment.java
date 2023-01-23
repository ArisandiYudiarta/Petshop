package com.example.petshop;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petshop.database.DataHelpers;
import com.example.petshop.database.DatabaseTableAnimals;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    Activity context;
    Button btnSimpanAdd;

    DataHelpers database;
    EditText petName, petAge, petDesc, petCategory, petGender, petWeight, petHeight;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View root =inflater.inflate(R.layout.fragment_add, container,false);
        return root;
    }
    public void onStart(){
        super.onStart();

        database = new DataHelpers(this.context);
        petName = (EditText) context.findViewById(R.id.etPetsName);
        petDesc = (EditText) context.findViewById(R.id.etPetDescription);
        petAge = (EditText) context.findViewById(R.id.etPetsAge);
        petCategory = (EditText) context.findViewById(R.id.etPetCategory);
        petGender = (EditText) context.findViewById(R.id.etPetGender);
        petHeight = (EditText) context.findViewById(R.id.etPetHeight);
        petWeight = (EditText) context.findViewById(R.id.etPetWeight);

        Button btnSimpanAdd = (Button) context.findViewById(R.id.btnSimpanAdd);
        btnSimpanAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                String Query = "INSERT INTO " + DatabaseTableAnimals.AnimalsColumns.TABLE_NAME + "(Pet_name, Pet_age, Pet_desc, Pet_category, Pet_gender, Pet_weight, Pet_height)" +
                        "VALUES ('"+ petName.getText().toString()+ "','"+
                        Integer.parseInt(petAge.getText().toString()) + "','" +
                        petDesc.getText().toString() + "','" +
                        petCategory.getText().toString() + "','" +
                        petGender.getText().toString() + "','" +
                        Float.parseFloat(petWeight.getText().toString()) + "','" +
                        Float.parseFloat(petHeight.getText().toString()) + "')";
                db.execSQL(Query);
                Toast.makeText(AddFragment.this.context, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                HomeFragment.home.RefreshList();
            }
        });
    }
}