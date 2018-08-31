/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud.modelo;

public class IdUsuario {

    private static IdUsuario ourInstance = null;

    //Variables de intacia
    private  String idUsuario;


    //Metodo para regresar la intacia de la clase
    public static synchronized IdUsuario getInstance() {

        if(ourInstance == null){
            ourInstance = new IdUsuario();
        }

        return ourInstance;
    }

    //Costructor
    private IdUsuario() {
    }


    public String getIdUsuario() { return idUsuario; }

    public  void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
