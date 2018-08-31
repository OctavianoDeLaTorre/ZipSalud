
/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud.modelo

import android.content.Context
import com.zipsalud.R

class EnfCardiacas {

    var idUsuario: String? = null
    var aneurismo:String? = null
    var arritmia:String? = null
    var anginaPecho:String? = null
    var infarto:String? = null
    var sopCardiaco:String? = null
    var enfValvulas:String? = null

    constructor()

    constructor(idUsuario: String?, aneurismo: String?, arritmia: String?, anginaPecho: String?, infarto: String?,
                sopCardiaco: String?, enfValvulas: String?) {
        this.idUsuario = idUsuario
        this.aneurismo = aneurismo
        this.arritmia = arritmia
        this.anginaPecho = anginaPecho
        this.infarto = infarto
        this.sopCardiaco = sopCardiaco
        this.enfValvulas = enfValvulas
    }

    fun getString(c: Context)
            = ("${c.getResources().getString(R.string.aneurismas)} $aneurismo" +
            "\n ${c.getResources().getString(R.string.arritmias)} $arritmia" +
            "\n ${c.getResources().getString(R.string.angina)} $anginaPecho" +
            "\n ${c.getResources().getString(R.string.infarto)} $infarto" +
            "\n ${c.getResources().getString(R.string.soplo)} $sopCardiaco" +
            "\n ${c.getResources().getString(R.string.valvulas)} $enfValvulas")

}