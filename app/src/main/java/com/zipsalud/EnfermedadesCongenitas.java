package com.zipsalud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zipsalud.controlador.EnfCongenitasDAO;
import com.zipsalud.modelo.EnfCongenitas;
import com.zipsalud.modelo.IdUsuario;

public class EnfermedadesCongenitas extends AppCompatActivity {

    private EnfCongenitasDAO enfConDAO;
    private EnfCongenitas enfCon;
    private String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_congenitas);

        enfConDAO = new EnfCongenitasDAO(this);
        enfCon = new EnfCongenitas();
        idUsuario = IdUsuario.getInstance().getIdUsuario();
        enfCon.setIdUsuario(idUsuario);

    }

    public void menu(View view){
        Intent i = new Intent(this,menuActivity.class);
        guardadInformacion();
        startActivity(i);
    }

    private void guardadInformacion(){
        if(enfConDAO.buscarRegistro(idUsuario) != null){
            if(enfConDAO.actualizarRegistro(enfCon)){
                Toast.makeText(this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.registro_no_exitoso,Toast.LENGTH_SHORT).show();
            }
        }else{
            if(enfConDAO.agregarRegistro(enfCon)){
                Toast.makeText(this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.registro_no_exitoso,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClikRB(View v){
        switch (v.getId()){
            case R.id.rbCancerSi:
                enfCon.setCancer("Si");
                break;
            case R.id.rbCancerNo:
                enfCon.setCancer("No");
                break;

            case R.id.rbLeucSi:
                enfCon.setLeucemia("Si");
                break;
            case R.id.rbLeucNo:
                enfCon.setLeucemia("No");
                break;

            case R.id.rbLinfSi:
                enfCon.setLinfoma("Si");
                break;
            case R.id.rbLinfNo:
                enfCon.setLinfoma("No");
                break;

            case R.id.rbMalfSi:
                enfCon.setMalformacion("Si");
                break;
            case R.id.rbMalfNo:
                enfCon.setMalformacion("No");
                break;

            case R.id.rbMielSi:
                enfCon.setMieloma("Si");
                break;
            case R.id.rbMielNo:
                enfCon.setMieloma("No");
                break;

            case R.id.rbMedCroSi:
                enfCon.setMedCronico("Si");
                break;
            case R.id.rbMedCroNo:
                enfCon.setMedCronico("No");
                break;
        }
    }
}










