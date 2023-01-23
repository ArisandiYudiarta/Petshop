package com.example.petshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.R;
import com.example.petshop.model.AnimalsModel;

import java.util.ArrayList;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalsViewHolder> {

    private final OnItemClickCallback onItemClickCallback;
    private final ArrayList<AnimalsModel> listanimals = new ArrayList<>();

    public AnimalsAdapter(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ArrayList<AnimalsModel> getListanimals() {
        return listanimals;
    }

    public void setListAnimals(ArrayList<AnimalsModel> listAnimals){
        if(this.listanimals.size() > 0){
            this.listanimals.clear();
        }

        this.listanimals.addAll(listAnimals);
    }

    public void addItem(AnimalsModel animals){
        this.listanimals.add(animals);
        notifyItemInserted(listanimals.size() - 1);
    }

    public void updateItem(int position, AnimalsModel animals){
        this.listanimals.set(position, animals);
        notifyItemChanged(position, animals);
    }

    public void removeItem(int position){
        this.listanimals.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listanimals.size());
    }

    @NonNull
    @Override
    public AnimalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_animal, parent, false);

        return new AnimalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalsViewHolder holder, int position) {
        holder.tvNama.setText(listanimals.get(position).getPet_name());
        holder.tvUmur.setText(String.valueOf(listanimals.get(position).getPet_age()));
        holder.cvAnimals.setOnClickListener(view -> onItemClickCallback.onItemClicked(listanimals.get(position), position));
    }

    static class AnimalsViewHolder extends RecyclerView.ViewHolder{
        final TextView tvNama, tvUmur;
        final CardView cvAnimals;

        AnimalsViewHolder(View itemView){
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.namaHewan);
            tvUmur = (TextView) itemView.findViewById(R.id.umurHewan);
            cvAnimals = (CardView) itemView.findViewById(R.id.cv_animals_card);
        }
    }

    @Override
    public int getItemCount() {
        return listanimals.size();
    }

    public interface OnItemClickCallback{
        void onItemClicked(AnimalsModel selectedAnimals, Integer position);
    }

}
