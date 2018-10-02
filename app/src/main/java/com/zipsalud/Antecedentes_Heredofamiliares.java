package com.zipsalud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Antecedentes_Heredofamiliares extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antecedentes__heredofamiliares);
    }
    public void alimentacion(View v){
        Intent i = new Intent(this,Alimentacion_Y_Habitos.class);
        startActivity(i);
    }
}
