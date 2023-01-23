package com.example.petshop;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.petshop.database.DataHelpers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Activity context;
    String[] animals;
    GridView dataGridView;
    Menu menu;
    Cursor cursor;
    DataHelpers db;

    public static HomeFragment home;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View root =inflater.inflate(R.layout.fragment_home, container,false);
        return root;
    }

    public void onStart(){
        super.onStart();

        home = this;
        db = new DataHelpers(this.context);

        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase DB = db.getReadableDatabase();
        cursor = DB.rawQuery("SELECT * FROM pet_animals_table", null);

        animals = new String[cursor.getCount()];

        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            animals[i] = cursor.getString(1).toString();
        }

        dataGridView = (GridView) context.findViewById(R.id.gridDataFragment);
        dataGridView.setAdapter(new ArrayAdapter(this.context, R.layout.grid_animal, animals));

        dataGridView.setSelected(true);
    }

//    public void onStart(){
//        super.onStart();
//        Button detail = (Button) context.findViewById(R.id.detail);
//        detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailHewan.class);
//                startActivity(intent);
//            }
//        });
//    }
}