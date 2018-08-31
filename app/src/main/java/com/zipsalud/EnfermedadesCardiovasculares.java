package com.zipsalud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.zipsalud.controlador.EnfCardiacasDAO;
import com.zipsalud.modelo.EnfCardiacas;
import com.zipsalud.modelo.IdUsuario;

public class EnfermedadesCardiovasculares extends AppCompatActivity {

    private EnfCardiacas enfCard;
    private EnfCardiacasDAO enfCarDAO;
    private IdUsuario idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_cardiovasculares);

        enfCarDAO = new EnfCardiacasDAO(this);
        enfCard = new EnfCardiacas();
        idUsuario = IdUsuario.getInstance();

        enfCard.setIdUsuario(idUsuario.getIdUsuario());
    }
    public void enfermedadesSisNervioso(View view){

        if (enfCarDAO.buscarRegistro(idUsuario.getIdUsuario())!=null){
            if (enfCarDAO.actualizarRegistro(enfCard)){
                Toast.makeText(this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.registro_no_exitoso,Toast.LENGTH_SHORT).show();
            }
        }else{

            if(enfCarDAO.agregarRegistro(enfCard)){
                Toast.makeText(this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.registro_no_exitoso,Toast.LENGTH_SHORT).show();
            }
        }

         startActivity(new Intent(this,EnfermedadesSistemaNervioso.class));

    }


    public void onclikRB(View v){
        switch (v.getId()){
            case R.id.rbAneuSi:
                enfCard.setAneurismo("Si");
                break;
            case R.id.rbAneuNo:
                enfCard.setAneurismo("No");
                break;

            case R.id.rbArriSi:
                enfCard.setArritmia("Si");
                break;
            case R.id.rbArriNo:
                enfCard.setArritmia("No");
                break;

            case R.id.rbAngiSi:
                enfCard.setAnginaPecho("Si");
                break;
            case R.id.rbAngiNo:
                enfCard.setAnginaPecho("No");
                break;

            case R.id.rbInfSi:
                enfCard.setInfarto("Si");
                break;
            case R.id.rbInfNo:
                enfCard.setInfarto("No");
                break;

            case R.id.rbSopSi:
                enfCard.setSopCardiaco("Si");
                break;
            case R.id.rbSopNo:
                enfCard.setSopCardiaco("No");
                break;

            case R.id.rbEnfCarSi:
                enfCard.setEnfValvulas("Si");
                break;
            case R.id.rbEnfCarNo:
                enfCard.setEnfValvulas("No");
                break;
        }

    }
}

