package com.example.petshop;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petshop.helpers.AnimalsHelper;
import com.example.petshop.model.AnimalsModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    Activity context;
    Button btnSimpanAdd;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText nama,umur,jenisKel,kategori,tinggi,berat,deskripsi;
    private AnimalsHelper animalHelper;
    private AnimalsModel animals;

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
        Button btnSimpanAdd = (Button) context.findViewById(R.id.btnSimpanAdd);
        nama = (EditText) context.findViewById(R.id.etPetsName);
        umur = (EditText) context.findViewById(R.id.etPetsAge);
        jenisKel = (EditText) context.findViewById(R.id.etPetGender);
        kategori = (EditText) context.findViewById(R.id.etPetCategory);
        deskripsi = (EditText) context.findViewById(R.id.etPetDescription);
        tinggi = (EditText) context.findViewById(R.id.etPetHeight);
        berat = (EditText)  context.findViewById(R.id.etPetWeight);

        animalHelper = AnimalsHelper.getInstance(context.getApplicationContext());
        animalHelper.open();

        animals = new AnimalsModel(0,null,null,0,null,null,null,0,0);


        btnSimpanAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
            String inputUmur = umur.getText().toString();
            String inputTinggi = tinggi.getText().toString();
            String inputBerat = berat.getText().toString();

            String getNama = nama.getText().toString();
            String getDesc = deskripsi.getText().toString();
            String getKategori = kategori.getText().toString();
            String getGender = jenisKel.getText().toString();
            Float getTinggi = Float.parseFloat(inputTinggi);
            int getUmur = Integer.parseInt(inputUmur);
            Float getBerat = Float.parseFloat(inputBerat);

                ContentValues values = new ContentValues();
                values.put("Pet_name", getNama);
                values.put("Pet_age", getUmur);
                values.put("Pet_desc", getDesc);
                values.put("Pet_category", getKategori);
                values.put("Pet_gender", getGender);
                values.put("Pet_height", getTinggi);
                values.put("Pet_weight", getBerat);
                values.put("Pet_photo", "");

                long result = animalHelper.insert(values);

                if(result > 0){
                    animals.setId((int) result);
                    Toast.makeText(context, "Data Pet Added!", Toast.LENGTH_SHORT).show();
                    context.finish();
                }
            }
        });
    }
}