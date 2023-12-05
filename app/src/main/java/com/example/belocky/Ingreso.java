package com.example.belocky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class Ingreso extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    private EditText Cor;
    private EditText Con;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.z);
        videoView.start();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        ImageButton loginButton = findViewById(R.id.intto);
        Cor = findViewById(R.id.Correo);
        Con = findViewById(R.id.Contrasena);
        loginButton.setOnClickListener(view -> {
            String email = Cor.getText().toString().trim();
            String password = Con.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Ingreso.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(email, password);
            }
        });

    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(Ingreso.this, Gestor.class));
                        Toast.makeText(Ingreso.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Ingreso.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(Ingreso.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show());
    }


}