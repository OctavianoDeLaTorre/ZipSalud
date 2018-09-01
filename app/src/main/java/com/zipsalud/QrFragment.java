package com.zipsalud;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.zipsalud.controlador.CompartirIMG;
import com.zipsalud.controlador.ControladorIMG;
import com.zipsalud.controlador.ControladorQR;
import com.zipsalud.controlador.infoBasicaHelper;
import com.zipsalud.modelo.IdUsuario;


public class QrFragment extends Fragment {

    private Button btnGuardarQR;
    private Button btnVisualizarQR;
    private Button btnCompartirQR;
    private ImageView image;
    private String text;

    private ControladorQR controladorQR;
    private ControladorIMG controladorIMG;
    private CompartirIMG compartirIMG;

    private Bitmap bitmap;
    private Vibrator vibrator;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Instaciar elementos graficos
        btnGuardarQR = getView().findViewById(R.id.btnGuardarQR);
        btnVisualizarQR = getView().findViewById(R.id.btnVisualizarQR);
        btnCompartirQR = getView().findViewById(R.id.btnCompartirQR);
        image = getView().findViewById(R.id.imageQR);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        //Instaciar controladores
        controladorQR = new ControladorQR();
        controladorIMG = new ControladorIMG(this.getContext());

        text = new infoBasicaHelper(this.getContext()).buscarRegistro(IdUsuario.getInstance().getIdUsuario()).getString(getContext());

        Toast.makeText(getContext(),text,Toast.LENGTH_LONG).show();
        try {

            int wP = (int) ((this.getActivity().getResources().getDisplayMetrics().widthPixels)*0.6);

            image.setMaxHeight(wP);
            image.setMaxHeight(wP);
            bitmap = controladorQR.generarCodigoQR(text,wP);
            image.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        //Agregar evento a boton guardar
        btnGuardarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controladorIMG.guardarIMG(bitmap);
                if(vibrator.hasVibrator())
                    vibrator.vibrate(100);
            }
        });

        final Activity activity = this.getActivity();

        //Agregar evento a boton vsualizar
        btnVisualizarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controladorQR.leerCodigoQR(activity);
                if(vibrator.hasVibrator())
                    vibrator.vibrate(100);
            }
        });


        //Agregar evento al boton compartirQR
        btnCompartirQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartirIMG = new CompartirIMG(activity,bitmap);
                compartirIMG.execute();
                if(vibrator.hasVibrator())
                    vibrator.vibrate(100);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_qr,container,false);
        return inflater.inflate(R.layout.fragment_qr, container, false);
    }



}
