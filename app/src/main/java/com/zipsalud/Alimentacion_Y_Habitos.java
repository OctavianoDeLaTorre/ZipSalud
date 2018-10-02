package com.zipsalud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Alimentacion_Y_Habitos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacion__y__habitos);
    }
    public void sustancias(View v){
        Intent i = new Intent(this,Sustancias.class);
        startActivity(i);
    }
}
