package com.example.greedstore.ui.categorias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.greedstore.R;
import com.example.greedstore.adapter.categoria_adapter;
import com.example.greedstore.databinding.FragmentCategoriasBinding;
import com.example.greedstore.entidades.Categorias;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriasFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    private FragmentCategoriasBinding binding;
    RecyclerView recyclerCategoria;
    ArrayList<Categorias> listaCategoria;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentCategoriasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listaCategoria=new ArrayList<>();
        recyclerCategoria = root.findViewById(R.id.reclyclerCat);
        recyclerCategoria.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerCategoria.setHasFixedSize(true);
        request = Volley.newRequestQueue(getContext());
        MostrarCategorias();


        return root;
    }

    private void MostrarCategorias() {

        String url="http://192.168.1.34/greedstore_mobile/index.php";
        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        Categorias cate = null;
        try {
            for (int i = 0;i<response.length();i++){
                cate = new Categorias();
                JSONObject jsonObject = null;
                jsonObject=response.getJSONObject(i+"");
                cate.setIdcategoria(jsonObject.optInt("idcategoria"));
                cate.setNombre_categoria(jsonObject.optString("nombre_categoria"));
                cate.setFoto_categoria(jsonObject.optString("foto_categoria"));
                listaCategoria.add(cate);
            }
            categoria_adapter adapter = new categoria_adapter(listaCategoria);
            recyclerCategoria.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}