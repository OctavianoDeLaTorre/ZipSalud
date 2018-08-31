package com.zipsalud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class EnfermedadesSistemaNervioso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_sistema_nervioso);
    }

    public void enfermedadesCongenitas(View view){
        Intent i = new Intent(this,EnfermedadesCongenitas.class);
        startActivity(i);

    }
}