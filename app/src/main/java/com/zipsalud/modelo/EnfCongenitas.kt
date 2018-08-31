/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud.modelo

import android.content.Context
import com.zipsalud.R

class EnfCongenitas {

    var idUsuario:String? = null
    var cancer:String? = null
    var leucemia:String? = null
    var linfoma:String? = null
    var malformacion:String? = null
    var mieloma:String? = null
    var medCronico:String? = null

    constructor()

    constructor(idUsuario: String?, cancer: String?, leucemia: String?, linfoma: String?, malformacion: String?, mieloma: String?, medCronico: String?) {
        this.idUsuario = idUsuario
        this.cancer = cancer
        this.leucemia = leucemia
        this.linfoma = linfoma
        this.malformacion = malformacion
        this.mieloma = mieloma
        this.medCronico = medCronico
    }

    fun getString(c:Context)
            = ("${c.getResources().getString(R.string.cancer)} $cancer" +
            "\n ${c.getResources().getString(R.string.leucemia)} $leucemia" +
            "\n ${c.getResources().getString(R.string.linfoma)} $linfoma" +
            "\n ${c.getResources().getString(R.string.malformacion)} $malformacion" +
            "\n ${c.getResources().getString(R.string.mieloma)} $mieloma" +
            "\n ${c.getResources().getString(R.string.medicamento)} $medCronico")
}