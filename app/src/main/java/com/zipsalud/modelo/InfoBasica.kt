/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud.modelo

import android.content.Context
import com.zipsalud.R

class InfoBasica {

    //Atributos
    var idUsuario: String? = null
    var nombre: String? = null
    var primerAp: String? = null
    var segundoAp: String? = null
    var sexo: String? = null
    var tipoSangre: String? = null
    var telEmergencia: String? = null
    var direccion: String? = null
    var alergias: String? = null
    var fechaNac: String? = null

    //Costructores
    constructor() {}

    constructor(idUsuario: String?, nombre: String?, primerAp: String?, segundoAp: String?,
                sexo: String?, tipoSangre: String?, telEmergencia: String?, direccion: String?,
                alergias: String?, fechaNac: String?) {
        this.idUsuario = idUsuario
        this.nombre = nombre
        this.primerAp = primerAp
        this.segundoAp = segundoAp
        this.sexo = sexo
        this.tipoSangre = tipoSangre
        this.telEmergencia = telEmergencia
        this.direccion = direccion
        this.alergias = alergias
        this.fechaNac = fechaNac
    }

    fun getString(c:Context)
            = (" ${c.getResources().getString(R.string.nombre)}: $nombre" +
                "\n ${c.getResources().getString(R.string.pa)}: $primerAp" +
                "\n ${c.getResources().getString(R.string.sa)}: $segundoAp" +
                "\n ${c.getResources().getString(R.string.sexo)}: $sexo"+
                "\n ${c.getResources().getString(R.string.tSangre)}: $tipoSangre" +
                "\n ${c.getResources().getString(R.string.telefono)}: $telEmergencia" +
                "\n ${c.getResources().getString(R.string.direccion)}: $direccion" +
                "\n ${c.getResources().getString(R.string.alergias)}: $alergias" +
                "\n ${c.getResources().getString(R.string.fecha)}: $fechaNac")

}
