package com.example.greedstore.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greedstore.ProductosCategoria;
import com.example.greedstore.R;
import com.example.greedstore.entidades.Categorias;
import com.squareup.picasso.Picasso;

import java.util.List;

public class categoria_adapter extends RecyclerView.Adapter<categoria_adapter.CategoriaHolder>{

    List<Categorias> listaCategorias;

    public categoria_adapter(List<Categorias> listaCategorias){
        this.listaCategorias = listaCategorias;
    }

    @NonNull
    @Override
    public CategoriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorias, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new CategoriaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaHolder holder, int position) {
        holder.ViewNombreCategoria.setText(listaCategorias.get(position).getNombre_categoria());
        Picasso.get()
                .load(listaCategorias.get(position).getFoto_categoria())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.CateimageView);
    }


    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public class CategoriaHolder extends RecyclerView.ViewHolder {
        TextView ViewNombreCategoria;
        ImageView CateimageView;
        public CategoriaHolder(@NonNull View itemView) {
            super(itemView);
            ViewNombreCategoria=itemView.findViewById(R.id.ViewNombreCategoria);
            CateimageView=itemView.findViewById(R.id.CatimageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ProductosCategoria.class);
                    intent.putExtra("IDCAT",listaCategorias.get(getAdapterPosition()).getIdcategoria());
                    context.startActivity(intent);
                }
            });
        }
    }
}
