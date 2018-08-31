/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud.controlador;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ControladorIMG {

    private Context context;
    private String nombreArchivo = "ZipSalud_QR_"+tomarFechaYHora();


    public ControladorIMG(){

    }

    public ControladorIMG(Context context){
        this.context = context;
    }

    public void guardarIMG(Bitmap imagen) {

        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ZipSalud";
        String CurrentDateAndTime =  tomarFechaYHora();
        File dir = new File(file_path);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, nombreArchivo + CurrentDateAndTime + ".jpg");

        try {
            FileOutputStream fOut = new FileOutputStream(file);

            imagen.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            MakeSureFileWasCreatedThenMakeAvabile(file);
            imgGuardada();
        }catch(FileNotFoundException e  ) {
            imgNoGuardada();
        }catch (IOException e1){
            imgNoGuardada();
        }


    }

    private void MakeSureFileWasCreatedThenMakeAvabile(File file){
        MediaScannerConnection.scanFile(context,
                new String[] { file.toString() } , null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }

    private String tomarFechaYHora() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    private void imgNoGuardada() {
        Toast.makeText(context, "¡No se ha podido guardar la imagen!", Toast.LENGTH_SHORT).show();
    }

    private void imgGuardada() {
        Toast.makeText(context, "Imagen guardada en la galería.", Toast.LENGTH_SHORT).show();
    }
}
