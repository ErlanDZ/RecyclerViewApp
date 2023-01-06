package com.example.recyclerviewapp;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NamesViewHolder> {

    private OnClickItemCat onClickItemCat;
    private List<CatModel> listCat = null;


    public void setOnClickItemCat(OnClickItemCat onClickItemCat) {
        this.onClickItemCat = onClickItemCat;
    }

    public void setListCat( List<CatModel> listCat) {
        this.listCat = listCat;
        notifyDataSetChanged();
    }

//    public NamesAdapter(List<CatModel> listCat) {
//        this.listCat = listCat;
//        notifyDataSetChanged();
//    }



    @NonNull
    @Override
    public NamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NamesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_names, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NamesViewHolder holder, int position) {
        holder.onBind(listCat.get(position));
    }

    @Override
    public int getItemCount() {
        return listCat.size();
    }

    public class NamesViewHolder extends RecyclerView.ViewHolder  {
        TextView  name, code;
        ImageView imageView;
        ConstraintLayout constraintLayout;
        public NamesViewHolder(@NonNull View itemView) {
            super(itemView);
           name = itemView.findViewById(R.id.txt_name);
           code = itemView.findViewById(R.id.txt_status_code);
           imageView =itemView.findViewById(R.id.image_cat);
           constraintLayout = itemView.findViewById(R.id.item_cat);
        }

        public void onBind(CatModel listCats){
            URI uri = null;
            try {
                uri = new URI(listCats.getStatusCode());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            String path = uri.getPath();
            String idStr = path.substring(path.lastIndexOf('/') + 1);
            int id = Integer.parseInt(idStr);

            name.setText(listCats.getDescription());
            code.setText(String.valueOf(id));
            Glide.with(imageView.getContext()).load(listCats.getStatusCode()).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemCat.listenerClickItemCat(listCats);
                }
            });

        }
    }
}
