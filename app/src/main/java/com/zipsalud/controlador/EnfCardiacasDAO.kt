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
import com.zipsalud.modelo.EnfCardiacas

class EnfCardiacasDAO(val context: Context?) : SQLiteOpenHelper(context, InfoBD.NOMBRE_BD, null, InfoBD.VERSION_BD) {

    companion object {
        private val TABLA_ENF_CARD: String = "enf_cardiobasculares"
        private val ID:String = "id_usuario"
        private val ANEURISMO = "aneurismo"
        private val ARRITMIA ="arritmia"
        private val ANGINAPECHO = "angina_de_pecho"
        private val INFARTO = "infarto"
        private val SOPCARDIACO ="soplo_cardiaco"
        private val ENFVALVULAS = "enfermedad_de_valvulas"

        private val CREACION_TABLA_ENF_CARDIACAS=
                ("CREATE TABLE $TABLA_ENF_CARD (" +
                        "$ID  VARCHAR(30) PRIMARY KEY," +
                        "$ANEURISMO VARCHAR(3)," +
                        "$ARRITMIA VARCHAR(3)," +
                        "$ANGINAPECHO VARCHAR(3)," +
                        "$INFARTO VARCHAR(3)," +
                        "$SOPCARDIACO VARCHAR(3)," +
                        "$ENFVALVULAS VARCHAR(3))")
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREACION_TABLA_ENF_CARDIACAS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun agregarRegistro(enfCar: EnfCardiacas):Boolean{
        val db:SQLiteDatabase = this.getWritableDatabase()
        val datos = ContentValues()

        datos.put(ID,enfCar.idUsuario)
        datos.put(ANEURISMO,enfCar.aneurismo)
        datos.put(ARRITMIA,enfCar.arritmia)
        datos.put(ANGINAPECHO,enfCar.anginaPecho)
        datos.put(INFARTO,enfCar.infarto)
        datos.put(SOPCARDIACO,enfCar.sopCardiaco)
        datos.put(ENFVALVULAS,enfCar.sopCardiaco)

        var res: Long = -1L

        try {
            res = db.insert(TABLA_ENF_CARD,null,datos)
        }catch (e: SQLiteException) {
            db.execSQL(CREACION_TABLA_ENF_CARDIACAS)
            try{
                res = db.insert(TABLA_ENF_CARD,null,datos)
            } catch (e:SQLiteException){
                Toast.makeText(context,"Tabla no creada...",Toast.LENGTH_SHORT).show()
            }
        } finally {
            db.close()
            return (res != -1L)
        }
    }


    fun buscarRegistro(idUsuario:String): EnfCardiacas?{
        val db:SQLiteDatabase = this.getWritableDatabase()
        val sql:String = ("SELECT * FROM $TABLA_ENF_CARD WHERE $ID ='$idUsuario'")

        var cursor: Cursor?
        var enfCar: EnfCardiacas? = null

        try {
            cursor = db.rawQuery(sql, null)
        }catch (e: SQLiteException){
            db.execSQL(CREACION_TABLA_ENF_CARDIACAS)
            Toast.makeText(context, "Crendo tabla", Toast.LENGTH_SHORT).show()
            db.close()
            return null
        }

        if(cursor!!.moveToFirst()){
            enfCar = EnfCardiacas(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6))
            Toast.makeText(context, "Guardando Informacion", Toast.LENGTH_SHORT).show()

        }

        db.close()
        return enfCar
    }

    fun actualizarRegistro(enfCar: EnfCardiacas):Boolean{
        val db:SQLiteDatabase = this.getWritableDatabase()
        val datos:ContentValues = ContentValues()

        datos.put(ANEURISMO,enfCar.aneurismo)
        datos.put(ARRITMIA,enfCar.arritmia)
        datos.put(ANGINAPECHO,enfCar.anginaPecho)
        datos.put(INFARTO,enfCar.infarto)
        datos.put(SOPCARDIACO,enfCar.sopCardiaco)
        datos.put(ENFVALVULAS,enfCar.sopCardiaco)

        val res: Int = db.update(TABLA_ENF_CARD,datos,("$ID ='${enfCar.idUsuario}'"),null)

        db.close()
        return  (res != -1)
    }

}