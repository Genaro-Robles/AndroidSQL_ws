package com.example.greedstore;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.greedstore.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_categorias, R.id.navigation_favoritos)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        String archivos[] = fileList();
        this.window = getWindow();

        if(ArchivoExiste(archivos,"config.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("config.txt"));
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
    }
    private boolean ArchivoExiste(String[] archivos, String nombreArchivo) {
        for(int i = 0; i < archivos.length; i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }


    private void cambiarColor(String primaryDark, String primary, String background){
        //Color primaryDark
        window.setStatusBarColor(Color.parseColor(primaryDark));
        //Color primary
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(primary)));
        //Color primary
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor(background)));
        //Bottom navigation
        window.setNavigationBarColor(Color.parseColor(primary));
    }

}