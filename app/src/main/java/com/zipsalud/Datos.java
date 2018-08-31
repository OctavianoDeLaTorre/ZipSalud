package com.zipsalud;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import com.zipsalud.controlador.infoBasicaHelper;
import com.zipsalud.modelo.IdUsuario;
import com.zipsalud.modelo.InfoBasica;

public class Datos extends AppCompatActivity {

    TextView tv;
    RadioButton rbF, rbM;
    Calendar mCurrentDate;
    int day, month, year;

    //Variables de instacia
    private infoBasicaHelper infDAO;
    private InfoBasica infoBasica;

    private EditText txtNombre;
    private EditText txtPrimerAp;
    private EditText txtSegundoAp;
    private Spinner spSangre;
    private EditText txtDireccion;
    private EditText txtTelefono;
    private EditText txtAlergias;
    private TextView txtFechaNac;

    private IdUsuario idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        Spinner spinner = findViewById(R.id.spSangre);
        String[] letra = {"...","A+","A-","B+","B-","AB+","AB-","O+","O-"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));


        tv = (TextView) findViewById(R.id.txtFechaNac);
        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        rbF = findViewById(R.id.rbF);
        rbM = findViewById(R.id.rbM);

        month = month+1;

        tv.setText(day+"/"+month+"/"+year);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Datos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        tv.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        //Instaciar componentes graficos
        txtNombre =  findViewById(R.id.txtNombre);
        txtPrimerAp =  findViewById(R.id.txtPrimerAp);
        txtSegundoAp =  findViewById(R.id.txtSegundoAp);
        txtDireccion =  findViewById(R.id.txtDireccion);
        txtTelefono =  findViewById(R.id.txtTelefono);
        spSangre = findViewById(R.id.spSangre);
        txtAlergias = findViewById(R.id.txtAlergias);
        txtFechaNac = findViewById(R.id.txtFechaNac);

        idUsuario = IdUsuario.getInstance();

    }

    public void actividades1(View v){
        //Instaciar modelo y controlador
        infDAO = new  infoBasicaHelper(this);
        infoBasica = new InfoBasica();

        infoBasica.setIdUsuario(idUsuario.getIdUsuario());
        infoBasica.setNombre(txtNombre.getText().toString());
        infoBasica.setPrimerAp(txtPrimerAp.getText().toString());
        infoBasica.setSegundoAp(txtSegundoAp.getText().toString());
        infoBasica.setAlergias(txtAlergias.getText().toString());
        infoBasica.setDireccion(txtDireccion.getText().toString());
        infoBasica.setTelEmergencia(txtTelefono.getText().toString());
        infoBasica.setFechaNac(txtFechaNac.getText().toString());
        infoBasica.setTipoSangre(spSangre.getSelectedItem().toString());
        if(rbM.isChecked()){
            infoBasica.setSexo("Mujer");

        } else if(rbF.isChecked()){
            infoBasica.setSexo("Hombre");
        }
        guardarInformacion();
        Intent i = new Intent(this,Actividades.class);
        startActivity(i);

    }

    private void guardarInformacion(){
        if(infDAO.buscarRegistro(idUsuario.getIdUsuario()) != null){
            if(infDAO.actualizarRegistro(infoBasica)){
                Toast.makeText(this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.registro_no_exitoso,Toast.LENGTH_SHORT).show();
            }
        }else{
            if(infDAO.agregarRegidtro(infoBasica)){
                Toast.makeText(this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.registro_no_exitoso,Toast.LENGTH_SHORT).show();
            }
        }
    }
}

