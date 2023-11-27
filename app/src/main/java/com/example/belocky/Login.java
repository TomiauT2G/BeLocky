package com.example.belocky;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.io.IOException;


public class Login extends AppCompatActivity {
        private EditText Correo;
        private EditText Contrasenna;
        private FirebaseAuth AutorizacionFirebase;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            setTitle("Login");
            try {
                ConnectivityManager Conexion = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                Network Internet = Conexion.getActiveNetwork();
                NetworkCapabilities InternetConectado = Conexion.getNetworkCapabilities(Internet);

                if (InternetConectado == null || !InternetConectado.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    throw new IOException("No hay conexión a Internet");
                }


            } catch (IOException e) {
                // Manejar la excepción de falta de conexión a Internet
                Toast.makeText(Login.this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                // Manejar otras excepciones
                Toast.makeText(Login.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
            }

            AutorizacionFirebase = FirebaseAuth.getInstance();

            Correo = findViewById(R.id.correo);
            Contrasenna = findViewById(R.id.contrasena);
            Button BotonLogin = findViewById(R.id.btn_ingresar);
            Button BotonRegistro = findViewById(R.id.btn_register);

            BotonLogin.setOnClickListener(view -> {
                String email = Correo.getText().toString().trim();
                String password = Contrasenna.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            });

            BotonRegistro.setOnClickListener(view -> startActivity(new Intent(Login.this, Registro.class)));

        }

        private void loginUser(String email, String password) {
            AutorizacionFirebase.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(Login.this, MainActivity.class));
                            Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show());
        }

        @Override
        protected void onStart() {
            super.onStart();
            FirebaseUser user = AutorizacionFirebase.getCurrentUser();
            if (user != null) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        }

    }