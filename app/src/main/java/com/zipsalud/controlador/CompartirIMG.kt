/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 */

/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 */

package com.zipsalud.controlador

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.support.v4.content.FileProvider

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

class CompartirIMG(internal var context: Context, internal var imagen: Bitmap) : AsyncTask<Void, Void, Bitmap>() {

    override fun doInBackground(vararg params: Void): Bitmap? {

        try {

            // Guardar bitmap a disco.
            try {

                val cachePath = File(context.cacheDir, "imagenes") //path cache.
                cachePath.mkdirs() // Crea directorio si no existe.
                val stream = FileOutputStream(cachePath.toString() + "/imagen.jpg") // Escribe imagen.
                imagen.compress(Bitmap.CompressFormat.PNG, 100, stream)
                stream.close()

            } catch (e: IOException) {
                e.printStackTrace()
            }


            val imagePath = File(context.cacheDir, "imagenes") //obtiene directorio.
            val newFile = File(imagePath, "imagen.jpg") //obtiene imagen.

            val PACKAGE_NAME = context.packageName + ".providers.FileProvider"

            val contentUri = FileProvider.getUriForFile(context, PACKAGE_NAME, newFile)

            if (contentUri != null) {

                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // temp permission for receiving app to read this file
                shareIntent.setDataAndType(contentUri, context.contentResolver.getType(contentUri))
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
                context.startActivity(Intent.createChooser(shareIntent, "Elige una aplicación:"))

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}
