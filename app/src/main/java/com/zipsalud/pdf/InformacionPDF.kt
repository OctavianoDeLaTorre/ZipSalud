package com.zipsalud.pdf

import android.content.Context
import com.zipsalud.controlador.EnfCardiacasDAO
import com.zipsalud.controlador.EnfCongenitasDAO
import com.zipsalud.controlador.infoBasicaHelper
import com.zipsalud.modelo.IdUsuario

class InformacionPDF(var context:Context) {

     val infBasica : String?
     val enfCardiacas : String?
     val enfCongenitas : String?


    init {
        infBasica = obtenerInfoBasica()
        enfCardiacas = obtenerEnfCardiacas()
        enfCongenitas = obtenerEnfCongenitas()
    }

    companion object {
        private val id: String = IdUsuario.getInstance().idUsuario
    }


    private fun obtenerInfoBasica() = infoBasicaHelper(context).buscarRegistro(id)?.getString(context)

    private fun obtenerEnfCardiacas() =  EnfCardiacasDAO(context).buscarRegistro(id)?.getString(context)

    private fun obtenerEnfCongenitas() = EnfCongenitasDAO(context).buscarRegistro(id)?.getString(context)


}