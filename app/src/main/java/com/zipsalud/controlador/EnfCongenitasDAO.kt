/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/


package com.zipsalud.controlador

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.zipsalud.modelo.EnfCongenitas

class EnfCongenitasDAO(val context: Context?) : SQLiteOpenHelper(context, InfoBD.NOMBRE_BD, null, InfoBD.VERSION_BD) {

    companion object {
        private val TABLA_EN_CONGENITAS: String = "enf_congenicas"

        private val ID:String = "idUsuario"
        private val CANCER:String = "cancer"
        private val LEUCEMIA:String = "leucemia"
        private val LINFOMA:String = "linfoma"
        private val MALFORMACION:String = "malformacion"
        private val MIELOMA:String = "mieloma"
        private val MEDCRONICO:String = "medCronico"


        private val CREACION_TABLA_ENFCON :String =
                ("CREATE TABLE $TABLA_EN_CONGENITAS (" +
                        "$ID  VARCHAR(30) PRIMARY KEY," +
                        "$CANCER VARCHAR(3)," +
                        "$LEUCEMIA VARCHAR(3)," +
                        "$LINFOMA VARCHAR(3)," +
                        "$MALFORMACION VARCHAR(3)," +
                        "$MIELOMA VARCHAR(3)," +
                        "$MEDCRONICO VARCHAR(3))")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREACION_TABLA_ENFCON)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun agregarRegistro(enfCon: EnfCongenitas):Boolean{
        val db = this.getWritableDatabase()

        val datos = ContentValues()

        datos.put(ID,enfCon.idUsuario)
        datos.put(CANCER,enfCon.cancer)
        datos.put(LEUCEMIA,enfCon.leucemia)
        datos.put(LINFOMA,enfCon.linfoma)
        datos.put(MALFORMACION,enfCon.malformacion)
        datos.put(MIELOMA,enfCon.mieloma)
        datos.put(MEDCRONICO,enfCon.medCronico)

        var res: Long = -1L

        try {
            res = db.insert(TABLA_EN_CONGENITAS,null,datos)
        }catch (e: SQLiteException) {
            db.execSQL(CREACION_TABLA_ENFCON)
            try{
                res = db.insert(TABLA_EN_CONGENITAS,null,datos)
            } catch (e: SQLiteException){
                Toast.makeText(context,"Tabla no creada...", Toast.LENGTH_SHORT).show()
            }
        } finally {
            db.close()
            return (res != -1L)
        }
    }

    fun buscarRegistro(idUsuario:String): EnfCongenitas?{
        val db = this.getWritableDatabase()

        val sql:String = ("SELECT * FROM $TABLA_EN_CONGENITAS WHERE $ID = '$idUsuario'")

        var cursor: Cursor?
        var enfCon: EnfCongenitas? = null

        try {
            cursor = db.rawQuery(sql, null)

            if(cursor!!.moveToFirst()){
                enfCon = EnfCongenitas(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6))
            }
        }catch (e: SQLiteException){
            db.execSQL(CREACION_TABLA_ENFCON)
        } finally {
            db.close()
            return enfCon
        }
    }

    fun actualizarRegistro(enfCon: EnfCongenitas):Boolean{
        val db = this.getWritableDatabase()

        val datos = ContentValues()

        datos.put(ID,enfCon.idUsuario)
        datos.put(CANCER,enfCon.cancer)
        datos.put(LEUCEMIA,enfCon.leucemia)
        datos.put(LINFOMA,enfCon.linfoma)
        datos.put(MALFORMACION,enfCon.malformacion)
        datos.put(MIELOMA,enfCon.mieloma)
        datos.put(MEDCRONICO,enfCon.medCronico)

        val res: Int = db.update(TABLA_EN_CONGENITAS,datos,("$ID ='${enfCon.idUsuario}'"),null)

        db.close()
        return  (res != -1)
    }
}