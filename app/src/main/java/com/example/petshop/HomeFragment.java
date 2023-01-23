package com.example.petshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.petshop.adapter.AnimalsAdapter;
import com.example.petshop.helpers.AnimalsHelper;
import com.example.petshop.helpers.AnimalsMapping;
import com.example.petshop.model.AnimalsModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements LoadAnimalCallback {
    Activity context;
    private RecyclerView rvAnimals;
    private AnimalsAdapter animalsAdapter;

    private static final String EXTRA_STATE = "EXTRA_STATE";

    Button detail;
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

        if(savedInstanceState == null){
            new LoadAnimalsAsync(this.context, this::postExecute).execute();
        }
        else{
            ArrayList<AnimalsModel> animalList = savedInstanceState.getParcelableArrayList(EXTRA_STATE);

            if(animalList != null){
                animalsAdapter.setListAnimals(animalList);
            }
        }
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        if(context instanceof LoadAnimalCallback){
//            this.LoadCallback = (HomeFragment.LoadAnimalCallback) context;
//        }
//        else{
//            throw new RuntimeException(context.toString() + " must implement loadCallback");
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        View root =inflater.inflate(R.layout.fragment_home, container,false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvAnimals = (RecyclerView) view.findViewById(R.id.rv_animals);
        rvAnimals.setLayoutManager(new LinearLayoutManager(this.context));
        rvAnimals.setHasFixedSize(true);

        animalsAdapter = new AnimalsAdapter(new AnimalsAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(AnimalsModel selectedAnimals, Integer position) {
                Intent intent = new Intent(context, AddFragment.class);
                startActivity(intent);
            }
        });

        rvAnimals.setAdapter(animalsAdapter);
    }

    public void onStart(){
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, animalsAdapter.getListanimals());
    }

    @Override
    public void postExecute(ArrayList<AnimalsModel> dataAnimals) {
        if(dataAnimals.size() > 0){
            animalsAdapter.setListAnimals(dataAnimals);
        }
        else{
            animalsAdapter.setListAnimals(new ArrayList<>());
        }
    }

    private static class LoadAnimalsAsync{
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadAnimalCallback> weakCallback;

        public LoadAnimalsAsync(Context context, LoadAnimalCallback callBack){
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callBack);
        }

        void execute(){
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(()->{
                Context context = weakContext.get();
                AnimalsHelper animalHelper = AnimalsHelper.getInstance(context);

                animalHelper.open();
                Cursor dataCursor = animalHelper.queryAll();
                ArrayList<AnimalsModel> animals = AnimalsMapping.mapCursorToArrayList(dataCursor);

                animalHelper.close();

                handler.post(()-> weakCallback.get().postExecute(animals));

            });
        }
    }

    interface LoadAnimalCallback{
        void postExecute(ArrayList<AnimalsModel> dataAnimals);
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