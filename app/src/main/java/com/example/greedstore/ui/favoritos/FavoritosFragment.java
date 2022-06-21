package com.example.greedstore.ui.favoritos;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.greedstore.R;
import com.example.greedstore.databinding.FragmentFavoritosBinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FavoritosFragment extends Fragment {

    Button btnRojo, btnMorado, btnNaranja;
    private Window window;

    private FragmentFavoritosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritosViewModel notificationsViewModel =
                new ViewModelProvider(this).get(FavoritosViewModel.class);

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        btnRojo = root.findViewById(R.id.btnRojo);
        btnMorado = root.findViewById(R.id.btnMorado);
        btnNaranja = root.findViewById(R.id.btnNaranja);
        String archivos[] = getActivity().fileList();

        this.window = getActivity().getWindow();

        if(ArchivoExiste(archivos,"config.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(getActivity().openFileInput("config.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String temas [] = new String[3];
                for (int i = 0; i<3;i++){
                    temas[i] = linea;
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                cambiarColor(temas[0],temas[1],temas[2]);
            } catch (IOException e) {

            }
        }

        btnRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primaryDark = "#f44336";
                String primary = "#ba000d";
                String background = "#ffffff";

                cambiarColor(primaryDark,primary,background);
                Guardar(getView(),primaryDark,primary,background);
            }
        });

        btnMorado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primaryDark = "#320b86";
                String primary = "#673ab7";
                String background = "#ffffff";

                cambiarColor(primaryDark,primary,background);
                Guardar(getView(),primaryDark,primary,background);
            }
        });

        btnNaranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primaryDark = "#ff9800";
                String primary = "#c66900";
                String background = "#ffffff";

                cambiarColor(primaryDark,primary,background);
                Guardar(getView(),primaryDark,primary,background);
            }
        });

        return root;
    }

    private boolean ArchivoExiste(String[] archivos, String nombreArchivo) {
        for(int i = 0; i < archivos.length; i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;
            return false;
    }

    public void Guardar(View view, String primaryDark, String primary, String background){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(getActivity().openFileOutput("config.txt", Activity.MODE_PRIVATE));
            archivo.write(primaryDark+"\n"+primary+"\n"+background);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {

        }
        Toast.makeText(getContext(), "Tema guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void cambiarColor(String primaryDark, String primary, String background){
        //Color primaryDark
        window.setStatusBarColor(Color.parseColor(primaryDark));
        //Color primary
        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(primary)));
        //Color primary
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(background)));
        //Bottom navigation
        window.setNavigationBarColor(Color.parseColor(primary));
    }
}