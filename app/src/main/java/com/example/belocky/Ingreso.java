package com.example.belocky;

import android.net.Network;
import android.net.NetworkCapabilities;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.net.ConnectivityManager;
import java.io.IOException;
import android.content.Context;

public class Ingreso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

            // Verificar la conexión a Internet
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            Network network = connectivityManager.getActiveNetwork();
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);

            if (networkCapabilities == null || !networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                throw new IOException("No hay conexión a Internet");
            }


        } catch (IOException e) {
            // Manejar la excepción de falta de conexión a Internet
            Toast.makeText(Ingreso.this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
    }
}