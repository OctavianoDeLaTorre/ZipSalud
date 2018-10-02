package com.zipsalud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zipsalud.modelo.IdUsuario;

import java.util.Objects;

public class Login extends AppCompatActivity {

    //Variables de intacias para autenticar con gmail
    private GoogleApiClient googleApiClient;
    private Button signInButton;
    private Vibrator vibrator;

    //private Notificacion notificacion;
    public static final int SIGN_IN_CODE = 777;
    private ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Instanciar objeto de opciones de login de google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Instanciar el objeto GoogleApiClient
        googleApiClient = new GoogleApiClient.Builder(Login.this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Intaciar el boton de login

        */
        signInButton =  findViewById(R.id.btnLogin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclikLogin();
            }
        });

        pbLogin = findViewById(R.id.pbLogin);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        IdUsuario.getInstance().setIdUsuario(Objects.requireNonNull("666"));
    }

    //Metodo que se ejecutara al en el evento del boton btnLogin
    public void onclikLogin(){

        iniciarSesion();
       /* signInButton.setVisibility(View.GONE);
        pbLogin.setVisibility(View.VISIBLE);
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, SIGN_IN_CODE);

        */
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            IdUsuario.getInstance().setIdUsuario(Objects.requireNonNull(result.getSignInAccount()).getId());
            Toast.makeText(this,"Bienvenido "+result.getSignInAccount().getDisplayName(),Toast.LENGTH_LONG).show();
            iniciarSesion();
        } else {
            signInButton.setVisibility(View.VISIBLE);
            pbLogin.setVisibility(View.GONE);
            /*Snackbar.make(pbLogin, R.string.no_login, Snackbar.LENGTH_LONG)
                    .setAction(R.string.interntar, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onclikLogin();
                        }
                    }).show();*/
            //Toast.makeText(this, R.string.no_login, Toast.LENGTH_LONG).show();
        }
        if(vibrator.hasVibrator())
            vibrator.vibrate(400);
    }

    private void iniciarSesion() {
        Intent i = new Intent(this,Datos.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        //signInButton.setVisibility(View.VISIBLE);
        //pbLogin.setVisibility(View.GONE);
        startActivity(i);
    }
}
