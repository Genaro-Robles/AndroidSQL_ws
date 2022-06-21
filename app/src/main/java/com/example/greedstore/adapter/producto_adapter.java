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

import com.example.greedstore.ProductoDetalle;
import com.example.greedstore.ProductosCategoria;
import com.example.greedstore.R;
import com.example.greedstore.entidades.Productos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class producto_adapter extends RecyclerView.Adapter<producto_adapter.ProductoHolder>{

    List<Productos> listaProductos;

    public producto_adapter(List<Productos> listaProductos){
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public producto_adapter.ProductoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new producto_adapter.ProductoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull producto_adapter.ProductoHolder holder, int position) {
        holder.ViewNombreProducto.setText(listaProductos.get(position).getNombre());
        holder.ViewPrecioProducto.setText("S/. "+listaProductos.get(position).getPrecio()+"");
        holder.ViewStockProducto.setText("Stock: "+listaProductos.get(position).getStock()+"");
        Picasso.get()
                .load(listaProductos.get(position).getImagen())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.ProimageView);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoHolder extends RecyclerView.ViewHolder {
        TextView ViewNombreProducto, ViewPrecioProducto, ViewStockProducto;
        ImageView ProimageView;
        public ProductoHolder(@NonNull View itemView) {
            super(itemView);
            ViewNombreProducto=itemView.findViewById(R.id.ViewNombreProducto);
            ViewPrecioProducto=itemView.findViewById(R.id.ViewPrecioProducto);
            ViewStockProducto=itemView.findViewById(R.id.ViewStockProducto);
            ProimageView=itemView.findViewById(R.id.ProimageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();
                    Intent intent = new Intent(context, ProductoDetalle.class);
                    intent.putExtra("IDPRO",listaProductos.get(getAdapterPosition()).getIdproducto());
                    context.startActivity(intent);
                }
            });
        }
    }
}
