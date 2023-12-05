package com.example.belocky;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
public class Registro extends AppCompatActivity {
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        EditText Nom , Cor,Con;

        setContentView(R.layout.activity_registro);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.z);
        videoView.start();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        Nom = findViewById(R.id.Usuario);
        Cor = findViewById(R.id.Correo);
        Con = findViewById(R.id.Contrasena);

        ImageButton frr = findViewById(R.id.reff);


        frr.setOnClickListener(view -> {

            String NombreDeUsuario = Nom.getText().toString().trim();
            String Correo = Cor.getText().toString().trim();
            String Contrasena = Con.getText().toString().trim();

            if (NombreDeUsuario.isEmpty() && Correo.isEmpty() && Contrasena.isEmpty()){
                Toast.makeText(Registro.this, "Complete los datos", Toast.LENGTH_SHORT).show();
            }else{
                RegistrarUsuario(NombreDeUsuario, Correo, Contrasena);
            }

        });
    }

    public void RegistrarUsuario(String nameUser, String emailUser, String passUser) {
        mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    String id = user.getUid();
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", id);
                    map.put("Nombre De Usuario", nameUser);
                    map.put("Correo", emailUser);
                    map.put("Contraseña", passUser);

                    mFirestore.collection("Usuarios").document(id).set(map).addOnSuccessListener(unused -> {
                        finish();
                        startActivity(new Intent(Registro.this, Gestor.class));
                        Toast.makeText(Registro.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(e -> Toast.makeText(Registro.this, "Error al guardar", Toast.LENGTH_SHORT).show());
                }
            } else {

                Toast.makeText(Registro.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
            }
        });

}
}