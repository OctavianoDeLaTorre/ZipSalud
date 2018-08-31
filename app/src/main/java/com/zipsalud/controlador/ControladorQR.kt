/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ****************************************************************************/

package com.zipsalud.controlador

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder

import com.zipsalud.R

class ControladorQR {

    @Throws(WriterException::class)
    fun generarCodigoQR(text: String,dimencion:Int): Bitmap {
        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix:BitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, dimencion, dimencion)
        val barcodeEncoder = BarcodeEncoder()
        return barcodeEncoder.createBitmap(bitMatrix)
    }

    fun leerCodigoQR(activity: Activity) {
        val integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
        integrator.setPrompt("QR Scanner")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(false)
        integrator.initiateScan()
    }

    fun mostrarInfoQRLeido(context: Context, info: String): AlertDialog {
        return AlertDialog.Builder(context)
                .setMessage(info)
                .setTitle(R.string.qr)
                .setCancelable(false)
                .setNeutralButton(R.string.ok) { dialogInterface, i -> dialogInterface.cancel() }
                .create()
    }
}
