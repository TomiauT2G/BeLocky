package com.example.belocky;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Gestor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor);

        // Otros códigos...

        // Obtén una referencia al botón "Añadir Página"
        Button btnAnadirPagina = findViewById(R.id.btnAnadirPagina);

        // Configura un OnClickListener para el botón "Añadir Página"
        btnAnadirPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogoAñadirPagina();
            }
        });
    }

    private void mostrarDialogoAñadirPagina() {
        // Infla el diseño del diálogo personalizado
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_anadir_pagina, null);

        // Configura el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view)
                .setTitle("Añadir Página")
                .setPositiveButton("Añadir", null) // El botón positivo se configurará más adelante
                .setNegativeButton("Cancelar", null);

        // Crea el AlertDialog
        final AlertDialog alertDialog = builder.create();

        // Configura el OnClickListener para el botón positivo
        alertDialog.setOnShowListener(dialog -> {
            Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(view1 -> {
                // Aquí puedes realizar la lógica para añadir la página a Firestore
                EditText editTextNombre = view.findViewById(R.id.editTextNombre);
                EditText editTextContraseña = view.findViewById(R.id.editTextContraseña);
                EditText editTextIdUsuario = view.findViewById(R.id.editTextPagina);

                String nombre = editTextNombre.getText().toString();
                String contraseña = editTextContraseña.getText().toString();
                String idUsuario = editTextIdUsuario.getText().toString();

                if (!nombre.isEmpty() && !contraseña.isEmpty() && !idUsuario.isEmpty()) {
                    // Lógica para añadir la página a Firestore
                    añadirPaginaFirestore(idUsuario, nombre, contraseña);
                    alertDialog.dismiss(); // Cierra el diálogo después de añadir la página
                } else {
                    // Muestra un mensaje si algún campo está vacío
                    // Puedes personalizar esto según tus necesidades
                    showToast("Completa todos los campos");
                }
            });
        });

        // Muestra el AlertDialog
        alertDialog.show();
    }

    private void añadirPaginaFirestore(String idUsuario, String nombre, String contraseña) {
        // Lógica para añadir la página a Firestore
        Map<String, Object> paginaData = new HashMap<>();
        paginaData.put("Contraseña", contraseña);
        paginaData.put("NombreEmail", nombre);

        DocumentReference usuarioRef = FirebaseFirestore.getInstance()
                .collection("usuarios")
                .document(idUsuario);

        usuarioRef.update(nombre, paginaData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Operación exitosa
                        Log.d("Firestore", "Datos de la página añadidos/actualizados exitosamente");
                        // Puedes realizar acciones adicionales después de añadir la página
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Manejar el error
                        Log.e("Firestore", "Error al añadir/actualizar datos de la página", e);
                        // Puedes realizar acciones adicionales en caso de error
                    }
                });
    }

    private void showToast(String message) {
        // Muestra un Toast con el mensaje proporcionado
        // Puedes personalizar esto según tus necesidades
        // (por ejemplo, utilizando una biblioteca como Toasty)
        // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
