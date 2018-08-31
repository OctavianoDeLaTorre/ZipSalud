/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud.controlador

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.zipsalud.modelo.InfoBasica


class infoBasicaHelper(context: Context?) : SQLiteOpenHelper(context, InfoBD.NOMBRE_BD, null,  InfoBD.VERSION_BD) {

    companion object {
        private val TABLA_INFOBASICA = "info_basica"
        private val ID = "id_usuario"
        private val NOMBRE = "nombre"
        private val PRIMER_AP = "primer_apellido"
        private val SEGUNDO_AP = "segundo_apellido"
        private val SEXO = "sexo"
        private val TIPO_SANGRE = "tipo_sangre"
        private val TEL_EMERGENCIA = "telefono_emergencia"
        private val DIRECCION = "direccion"
        private val ALERGIAS = "alergias"
        private val FECHA_NAC = "fecha_nacimiento"

        private val CREACION_TABLA_INFOBASICA =
                ("CREATE TABLE " + TABLA_INFOBASICA + " ( "
                        + ID + " VARCHAR(30) PRIMARY KEY,"
                        + NOMBRE + " VARCHAR(30),"
                        + PRIMER_AP + "  VARCHAR(30),"
                        + SEGUNDO_AP + "  VARCHAR(30),"
                        + SEXO + " VARCHAR(10),"
                        + TIPO_SANGRE + " VARCHAR(5),"
                        + TEL_EMERGENCIA + " VARCHAR(15),"
                        + DIRECCION + " VARCHAR(50),"
                        + ALERGIAS + " VARCHAR(50),"
                        + FECHA_NAC + " VARCHAR(15))")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREACION_TABLA_INFOBASICA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun agregarRegidtro(info: InfoBasica):Boolean{
        val db = this.getWritableDatabase()
        val datos = ContentValues()

        datos.put(ID, info.idUsuario)
        datos.put(NOMBRE, info.nombre)
        datos.put(PRIMER_AP, info.primerAp)
        datos.put(SEGUNDO_AP, info.segundoAp)
        datos.put(SEXO, info.sexo)
        datos.put(TIPO_SANGRE, info.tipoSangre)
        datos.put(TEL_EMERGENCIA, info.telEmergencia)
        datos.put(DIRECCION, info.direccion)
        datos.put(ALERGIAS, info.alergias)
        datos.put(FECHA_NAC, info.fechaNac)

        val res:Long = db.insert(TABLA_INFOBASICA,null,datos)
        db.close()

        return  (res != -1L)
    }

    fun buscarRegistro(idUsuario:String): InfoBasica? {
        val db = this.getWritableDatabase()
        val sql:String = ("SELECT * FROM $TABLA_INFOBASICA WHERE $ID = '$idUsuario'")
        var cursor:Cursor?

        try {
            cursor = db.rawQuery(sql, null)
        }catch (e: SQLException){
            db.execSQL(CREACION_TABLA_INFOBASICA)
            return null
        }

        var infoBasica: InfoBasica? = null
        if(cursor!!.moveToFirst()){
            infoBasica = InfoBasica(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9))
        }

        db.close()
        return infoBasica
    }

    fun actualizarRegistro(info: InfoBasica):Boolean{
        val db = this.getWritableDatabase()
        val datos = ContentValues()

        datos.put(ID, info.idUsuario)
        datos.put(NOMBRE, info.nombre)
        datos.put(PRIMER_AP, info.primerAp)
        datos.put(SEGUNDO_AP, info.segundoAp)
        datos.put(SEXO, info.sexo)
        datos.put(TIPO_SANGRE, info.tipoSangre)
        datos.put(TEL_EMERGENCIA, info.telEmergencia)
        datos.put(DIRECCION, info.direccion)
        datos.put(ALERGIAS, info.alergias)
        datos.put(FECHA_NAC, info.fechaNac)

        val res: Int = db.update(TABLA_INFOBASICA,datos,(ID +"='${info.idUsuario}'"),null)
        db.close()
        return  (res != -1)
    }

}