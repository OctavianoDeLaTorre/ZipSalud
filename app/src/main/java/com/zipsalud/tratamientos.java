package com.zipsalud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class tratamientos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamientos);
    }

    public void nTratamiento(View view){
        Intent i = new Intent(this,NuevoTratamiento.class);
        startActivity(i);

    }
    public void menu(View view){
        Intent i = new Intent(this,menuActivity.class);
        startActivity(i);

    }
}
