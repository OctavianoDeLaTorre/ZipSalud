package com.zipsalud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zipsalud.controlador.ActividadesDAO;
import com.zipsalud.modelo.IdUsuario;

public class Actividades extends AppCompatActivity {

    private com.zipsalud.modelo.Actividades act;
    private ActividadesDAO actDAO;
    private IdUsuario idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        act = new com.zipsalud.modelo.Actividades();
        actDAO = new ActividadesDAO(this);
        idUsuario = IdUsuario.getInstance();
    }
    public void enfFamili(View v){

        if (actDAO.buscarRegistro(idUsuario.getIdUsuario()) != null){
            if (actDAO.actualizarRegistro(act)){
                Toast.makeText(this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.registro_no_exitoso,Toast.LENGTH_SHORT).show();
            }
        }else{
            if (actDAO.agregarRegistro(act)){
                Toast.makeText(this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.registro_no_exitoso,Toast.LENGTH_SHORT).show();
            }
        }



        Intent i = new Intent(this,EnfermedadesFamiliares.class);
        startActivity(i);

    }

    public void onClickRB(View v){

        switch (v.getId()){

            case R.id.rbVmSi:
                act.setVisitaMedic("Si");
                break;
            case R.id.rbVmNo:
                act.setVisitaMedic("No");
                break;

            case R.id.rbAlcoholSi:
                act.setAlcohol("Si");
                break;
            case R.id.rbAlcoholNo:
                act.setAlcohol("No");
                break;
            case R.id.rbCigarroSi:
                act.setCigarrillo("Si");
                break;

            case R.id.rbCigarroNo:
                act.setCigarrillo("No");
                break;
            case R.id.rbEstupeSi:
                act.setEstupefacientes("Si");
                break;

            case R.id.rbEstupeNo:
                act.setEstupefacientes("No");
                break;
            case R.id.rbHipertSi:
                act.setHipertension("Si");
                break;

            case R.id.rbHipertNo:
                act.setHipertension("No");
                break;
            case R.id.rbActFiSi:
                act.setActFisica("Si");
                break;

            case R.id.rbObeSi:
                act.setObesidad("Si");
                break;
            case R.id.rbObeNo:
                act.setObesidad("No");
                break;

            case R.id.rbColeSi:
                act.setColesterol("Si");
                break;
            case R.id.rbColeNo:
                act.setColesterol("No");
                break;

            case R.id.rbDeabSi:
                act.setDiabetes("Si");
                break;
            case R.id.rbDeabNo:
                act.setDiabetes("No");
                break;

            case R.id.rbPastSi:
                act.setPasaTiempo("Si");
                break;
            case R.id.rbPastNo:
                act.setPasaTiempo("No");
                break;
        }


    }
}
