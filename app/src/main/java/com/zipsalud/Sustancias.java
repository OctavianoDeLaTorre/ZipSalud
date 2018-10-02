package com.zipsalud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Sustancias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sustancias);
    }
    public void aPPatologicos(View v){
        Intent i = new Intent(this,AntecedentesPersonalesPatologicos.class);
        startActivity(i);
    }
}
