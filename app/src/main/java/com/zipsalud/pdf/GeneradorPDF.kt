/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud.pdf

import android.content.Context
import android.content.res.Resources
import android.os.AsyncTask
import android.os.Environment
import android.widget.Toast
import com.lowagie.text.*
import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.pdf.draw.DottedLineSeparator
import harmony.java.awt.Color
import com.zipsalud.R
import com.zipsalud.controlador.EnfCardiacasDAO
import com.zipsalud.controlador.infoBasicaHelper
import com.zipsalud.modelo.EnfCardiacas
import com.zipsalud.modelo.IdUsuario
import com.zipsalud.modelo.InfoBasica
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class GeneradorPDF(val context:Context,var infPDF:InformacionPDF): AsyncTask<Boolean, Boolean, Boolean>() {

    companion object {
        private const val NOMBRE_DIRECTORIO = "ZipSaludPDF"
        private var NOMBRE_DOCUMENTO = "ZipSaludPdf.pdf"
        private const val ETIQUETA_ERROR = "ERROR"
        private val idUsuario: String = IdUsuario.getInstance().idUsuario
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Toast.makeText(context, "Creado pdf", Toast.LENGTH_SHORT).show()
    }

    override fun doInBackground(vararg p0: Boolean?): Boolean {
        var resultado:Boolean = true
        val documento:Document = Document()
        val r: Resources = context.resources

        try {
            var file:File? = crearFichero(NOMBRE_DOCUMENTO)
            var ficheroPdf: FileOutputStream = FileOutputStream(file?.getAbsolutePath())

            var writer: PdfWriter = PdfWriter.getInstance(documento, ficheroPdf)

            documento.setHeader(HeaderFooter(crearEncabezado("Informacion medica"), false))
            //documento.setFooter(HeaderFooter(Phrase("Esta es mi cabecera"), false))

            documento.open()
            documento.setMargins(20f,20f,20f,20f)


            //Informacionbasica
            documento.add(crearTitulo(r.getString(R.string.datos)))
            documento.add(crearParrafo(infPDF.infBasica!!))

            //Enfermedades cardiacas
            documento.add(crearTitulo("Enfermedades cardiobasculares"))
            documento.add(crearSubTitulo(r.getString(R.string.cardiovascular)))
            documento.add(crearParrafo(infPDF.enfCardiacas!!))

            //Enfermedades congenitas
            documento.add(crearTitulo("Enfermedades congenitas"))
            documento.add(crearSubTitulo(r.getString(R.string.sufre)))
            documento.add(crearParrafo(infPDF.enfCongenitas!!))

        }catch (e: DocumentException){
            resultado = false
        }catch ( e: IOException){
            resultado = false
        } finally {
            documento.close()
            return resultado
        }


    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        if (result!!)
             Toast.makeText(context, "Pdf creado con exito...", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "No se pudo crear el Pdf", Toast.LENGTH_SHORT).show()
    }

    /**
     * Crea un nuevo parraro de texto
     *
     * @param contenido
     * @return Paragraph
     */
    private fun crearParrafo(contenido:String ): Paragraph {
        return Paragraph(contenido)
    }

    /**
     * Crea un titulo
     *
     * @param titulo
     * @return Paragraph
     */
    private fun crearTitulo(titulo:String? ): Paragraph {
        var font: Font = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.BOLD, Color.DARK_GRAY);
        var t = Paragraph(titulo,font)
        t.setAlignment(Element.ALIGN_CENTER)
        return t
    }

    /**
     * Crea un Subtitulo
     *
     * @param Subtitulo
     * @return Paragraph
     */
    private fun crearSubTitulo(titulo:String? ): Paragraph {
        var font: Font = FontFactory.getFont(FontFactory.HELVETICA, 12f, Font.BOLD, Color.DARK_GRAY);
        var t = Paragraph(titulo,font)
        t.setAlignment(Element.ALIGN_LEFT)
        return t
    }

    /**
     * Crea un encabezado
     *
     * @param titulo
     * @return Phrase
     */
    private fun crearEncabezado(titulo:String ): Phrase {
        var font: Font = FontFactory.getFont(FontFactory.HELVETICA, 18f, Font.BOLD, Color.BLUE)
        return  Phrase(titulo,font)
    }

    /**
     * Crea un fichero con el nombre que se le pasa a la función y en la ruta
     * especificada.
     *
     * @param nombreFichero
     * @return file
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun crearFichero(nombreFichero:String):File? {
        var ruta: File? = getRuta()
        var fichero: File? = null
        if (ruta != null)
            fichero = File(ruta, nombreFichero)
        return fichero
    }

    /**
     * Obtenemos la ruta donde vamos a almacenar el fichero.
     *
     * @return file
     */
    private fun getRuta(): File? {
       var ruta: File? = null
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            ruta =  File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    NOMBRE_DIRECTORIO)
            if (ruta != null) {
                if (!ruta.mkdirs()) {
                    if (!ruta.exists()) {
                       return null
                    }
                }
            }
        }
        return ruta
    }





}