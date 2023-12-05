package com.example.belocky;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    ImageView Logo;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton IS = findViewById(R.id.OMOMO);
        Button ok = findViewById(R.id.registro);

        IS.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ingreso.class);
            startActivity(intent);

        });
        ok.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Registro.class);
            startActivity(intent);

        });

        Logo = findViewById(R.id.Logo);
        Logo.setImageResource(R.drawable.belocky);
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.z);
        videoView.start();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        ImageButton signInButton = findViewById(R.id.isg);
        signInButton.setOnClickListener(view -> signInWithGoogle());

    }
    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInAccount account = Auth.GoogleSignInApi.getSignInResultFromIntent(data).getSignInAccount();
            handleSignInResult(account);
        }
    }

    private void handleSignInResult(GoogleSignInAccount account) {
        if (account != null) {
            String email = account.getEmail();
            String displayName = account.getDisplayName();
            String id = account.getId();


            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    status -> {
                    });
        } else {
            Log.e(TAG, "Error en la autenticación con Google");
        }
    }
}
