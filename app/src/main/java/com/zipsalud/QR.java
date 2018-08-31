package com.zipsalud;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.zipsalud.controlador.CompartirIMG;
import com.zipsalud.controlador.ControladorIMG;
import com.zipsalud.controlador.ControladorQR;
import com.zipsalud.controlador.infoBasicaHelper;
import com.zipsalud.modelo.IdUsuario;

public class QR extends AppCompatActivity {

    private Button btnGuardarQR;
    private Button btnVisualizarQR;
    private Button btnCompartirQR;
    private ImageView image;
    private String text;

    private ControladorQR controladorQR;
    private ControladorIMG controladorIMG;
    private CompartirIMG compartirIMG;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        //Instaciar elementos graficos
        btnGuardarQR = findViewById(R.id.btnGuardarQR);
        btnVisualizarQR = findViewById(R.id.btnVisualizarQR);
        btnCompartirQR = findViewById(R.id.btnCompartirQR);
        image = findViewById(R.id.imageQR);

        //Instaciar controladores
        controladorQR = new ControladorQR();
        controladorIMG = new ControladorIMG(this);

        text = new infoBasicaHelper(QR.this)
                .buscarRegistro(IdUsuario.getInstance().getIdUsuario())
                .toString();

        try {

            int wP = (int) ((getResources().getDisplayMetrics().widthPixels-32)*0.9);

            image.setMaxHeight(wP);
            image.setMaxHeight(wP);
            bitmap = controladorQR.generarCodigoQR(text,wP);
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Agregar evento a boton guardar
        btnGuardarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    controladorIMG.guardarIMG(bitmap);


            }
        });

        final Activity activity = this;

        //Agregar evento a boton vsualizar
        btnVisualizarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controladorQR.leerCodigoQR(activity);
            }
        });


        //Agregar evento al boton compartirQR
        btnCompartirQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartirIMG = new CompartirIMG(QR.this,bitmap);
                compartirIMG.execute();
            }
        });

    }
    public void menu(View view){
        Intent i = new Intent(this,menuActivity.class);
        startActivity(i);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                controladorQR.mostrarInfoQRLeido(QR.this,result.getContents()).show();
                //Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
