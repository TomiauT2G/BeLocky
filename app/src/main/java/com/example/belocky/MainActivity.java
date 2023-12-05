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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import android.view.View;

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
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        ImageButton signInButton = findViewById(R.id.isg);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });

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
        }
    }
}