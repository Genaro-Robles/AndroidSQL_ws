package com.example.greedstore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.greedstore.adapter.producto_adapter;
import com.example.greedstore.entidades.Productos;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductoDetalle extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    TextView txtNombre, txtPrecio, txtStock,txtDescripcion;
    Button btnComprar;
    ImageView imgProd;
    int idprod = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_detalle);

        request = Volley.newRequestQueue(this);
        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtStock = findViewById(R.id.txtStock);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        imgProd = findViewById(R.id.imgProd);
        btnComprar = findViewById(R.id.btnComprar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                idprod = Integer.parseInt(null);
            }else{
                idprod = extras.getInt("IDPRO");
            }
        }else{
            idprod = (int) savedInstanceState.getSerializable("IDPRO");
        }
        MostrarProductosDetalle(idprod+"");

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://192.168.1.34/GreedStore/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void MostrarProductosDetalle(String prod) {

        String url="http://192.168.1.34/greedstore_mobile/index.php?pro="+prod;
        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        Productos pro = null;
        try {
            txtNombre.setText(response.getString("nombre"));
            txtDescripcion.setText(response.getString("descripcion"));
            txtPrecio.setText("S/. "+response.getDouble("precio"));
            txtStock.setText(response.getInt("stock")+" unidades");
            Picasso.get()
                    .load(response.getString("imagen"))
                    .error(R.mipmap.ic_launcher_round)
                    .into(imgProd);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error Producto detalle", Toast.LENGTH_SHORT).show();
        }


    }
}