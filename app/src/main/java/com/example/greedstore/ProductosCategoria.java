package com.example.greedstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.greedstore.adapter.categoria_adapter;
import com.example.greedstore.adapter.producto_adapter;
import com.example.greedstore.entidades.Categorias;
import com.example.greedstore.entidades.Productos;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductosCategoria extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    RecyclerView recyclerProductos;
    ArrayList<Productos> listaProductos;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    int idprocat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_categoria);

        listaProductos=new ArrayList<>();
        recyclerProductos = findViewById(R.id.reclyer_productos);
        recyclerProductos.setLayoutManager(new GridLayoutManager(this,2));
        recyclerProductos.setHasFixedSize(true);
        request = Volley.newRequestQueue(this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                idprocat = Integer.parseInt(null);
            }else{
                idprocat = extras.getInt("IDCAT");
            }
        }else{
            idprocat = (int) savedInstanceState.getSerializable("IDCAT");
        }
        MostrarProductosxCat(""+idprocat);
    }

    private void MostrarProductosxCat(String cat) {

        String url="http://192.168.1.34/greedstore_mobile/index.php?cat="+cat;
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
            for (int i = 0;i<response.length();i++){
                pro = new Productos();
                JSONObject jsonObject = null;
                jsonObject=response.getJSONObject(i+"");
                pro.setIdproducto(jsonObject.optInt("idproducto"));
                pro.setNombre(jsonObject.optString("nombre"));
                pro.setStock(jsonObject.optInt("stock"));
                pro.setPrecio(Double.parseDouble(jsonObject.optString("precio")));
                pro.setImagen(jsonObject.optString("imagen"));
                listaProductos.add(pro);
            }
            producto_adapter adapter = new producto_adapter(listaProductos);
            recyclerProductos.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}