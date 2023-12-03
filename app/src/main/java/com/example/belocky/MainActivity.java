package com.example.belocky;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
   // FirebaseFirestore mFirestore;
   // FirebaseAuth mAuth;
    ImageView Logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
   //     mFirestore = FirebaseFirestore.getInstance();
     //   mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton IS = findViewById(R.id.OMOMO);
        Button ok = findViewById(R.id.registro);
        IS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ingreso.class);
                startActivity(intent);

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);

            }
        });

        Logo = findViewById(R.id.Logo);
        Logo.setImageResource(R.drawable.belocky);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.z);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }
    // btn_register.setOnClickListener(new View.OnClickListener)

    //{
    //  @Override
    //public void onClick (View view){
    //String nameUser = name.getText().toString().trim();
    //String emailUser = email.getText().toString().trim();
    //String passUser = password.getText().toString().trim();
    //if (nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()) {
    //    Toast.makeText(MainActivity.this, "Complete todos los datos porfavor", Toast.LENGTH_SHORT).show();
    //} else {
    //  registerUser(nameUser, emailUser, passUser);
    //}
    //}
    //}

    //private void registerUser(String nameUser, String emailUser, String passUser) {
    //  mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    //    @Override
    //  public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
    //    String id = mAuth.getCurrentUser().getUid();
    //  Map<String, Object> map = new HashMap<>();
    //map.put("id", id);
    //map.put("name", nameUser);
    //map.put("email", emailUser);
    //map.put("password", passUser);

    //mFirestore.collection("Usuario").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
    //  @Override
    //public void onSuccess(Void unused) {
    //finish();
    //  startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    //    Toast.makeText(RegisterActivity.this, "Usuario registrado con exito", Toast.LENGTH_SHORT);
    //  }
    //}).addOnFailureListener(new OnFailureListener() {
    //@Override
    //  public void onFailure(@NonNull @NotNull Exception e) {
    //        Toast.makeText(Registerctivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
    //      }
    //    });
    //  }
    //}).addOnFailureListener(new OnFailureListener() {
    //@Override
    //public void onFailure(@NonNull @NotNull Exception e) {
    //      Toast.makeText(RegisterActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
    //    }
    //  });
    //}
}