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
import com.zipsalud.modelo.Actividades

class ActividadesDAO(val context: Context?) : SQLiteOpenHelper(context, InfoBD.NOMBRE_BD, null, InfoBD.VERSION_BD) {


    companion object {
        private val TABLA_ACTIVIDADES:String = "actividades"
        private val ID:String = "id_usuario"
        private val VMEDICA:String = "visita_medica"
        private val CIGARRILLO:String = "consume_cigarrillo"
        private val ALCOHOL:String = "consume_alcohol"
        private val ESTUPEFACIENTES:String = "consume_estupefacientes"
        private val HIPERTENSION:String = "sufre_hipertension"
        private val ACTFISICA:String = "realiza_act_fisica"
        private val OBESIDAD:String = "tiene_obesidad"
        private val COLESTEROL:String = "tiene_colesterol"
        private val DIABETES:String = "sufre_diabetes"
        private val PASATIEMPO:String = "tiene_pasatiempos"

        private val CREACION_TABLA_ACTIVIDADES=
                ("CREATE TABLE $TABLA_ACTIVIDADES (" +
                        "$ID  VARCHAR(30) PRIMARY KEY," +
                        "$VMEDICA VARCHAR(3)," +
                        "$CIGARRILLO VARCHAR(3)," +
                        "$ALCOHOL VARCHAR(3)," +
                        "$ESTUPEFACIENTES VARCHAR(3)," +
                        "$HIPERTENSION VARCHAR(3)," +
                        "$ACTFISICA VARCHAR(3)," +
                        "$OBESIDAD VARCHAR(3)," +
                        "$COLESTEROL VARCHAR(3)," +
                        "$DIABETES VARCHAR(3)," +
                        "$PASATIEMPO VARCHAR(3))")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREACION_TABLA_ACTIVIDADES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }


    fun agregarRegistro(act: Actividades):Boolean{
        val db = this.getWritableDatabase()

        val datos = ContentValues()

        datos.put(ID,act.idUsuario)
        datos.put(VMEDICA,act.visitaMedic)
        datos.put(CIGARRILLO,act.cigarrillo)
        datos.put(ALCOHOL,act.estupefacientes)
        datos.put(ESTUPEFACIENTES,act.estupefacientes)
        datos.put(HIPERTENSION,act.hipertension)
        datos.put(ACTFISICA,act.actFisica)
        datos.put(OBESIDAD,act.obesidad)
        datos.put(COLESTEROL,act.colesterol)
        datos.put(DIABETES,act.diabetes)
        datos.put(PASATIEMPO,act.pasaTiempo)

        var res: Long = -1L

        try {
            res = db.insert(TABLA_ACTIVIDADES,null,datos)
        }catch (e:SQLiteException) {
            db.execSQL(CREACION_TABLA_ACTIVIDADES)
            try{
                res = db.insert(TABLA_ACTIVIDADES,null,datos)
            } catch (e:SQLiteException){
                Toast.makeText(context,"Tabla no creada...",Toast.LENGTH_SHORT).show()
            }
        } finally {
            db.close()
            return (res != -1L)
        }
    }


    fun buscarRegistro(idUsuario:String): Actividades?{
        val db = this.getWritableDatabase()

        val sql:String = ("SELECT * FROM $TABLA_ACTIVIDADES WHERE $ID = '$idUsuario'")

        var cursor: Cursor?
        var act: Actividades? = null

        try {
            cursor = db.rawQuery(sql, null)
        }catch (e: SQLiteException){
            db.execSQL(CREACION_TABLA_ACTIVIDADES)
            db.close()
            return null
        }

        if(cursor!!.moveToFirst()){
            act = Actividades(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10))
        }

        db.close()
        return act
    }

    fun actualizarRegistro(act: Actividades):Boolean{
        val db = this.getWritableDatabase()

        val datos = ContentValues()

        datos.put(VMEDICA,act.visitaMedic)
        datos.put(CIGARRILLO,act.cigarrillo)
        datos.put(ALCOHOL,act.estupefacientes)
        datos.put(ESTUPEFACIENTES,act.estupefacientes)
        datos.put(HIPERTENSION,act.hipertension)
        datos.put(ACTFISICA,act.actFisica)
        datos.put(OBESIDAD,act.obesidad)
        datos.put(COLESTEROL,act.colesterol)
        datos.put(DIABETES,act.diabetes)
        datos.put(PASATIEMPO,act.pasaTiempo)

        val res: Int = db.update(TABLA_ACTIVIDADES,datos,("$ID ='${act.idUsuario}'"),null)

        db.close()
        return  (res != -1)
    }

}