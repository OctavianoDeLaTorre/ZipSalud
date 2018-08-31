package com.zipsalud.modelo

class Actividades {

    //Variables de instacia
    var idUsuario: String? = null
    var visitaMedic:String? = null
    var cigarrillo:String? = null
    var alcohol:String? = null
    var estupefacientes:String? = null
    var hipertension:String?= null
    var actFisica:String? = null
    var obesidad:String? = null
    var colesterol:String? = null
    var diabetes:String? = null
    var pasaTiempo:String? = null


    //Constructores
    constructor()

    constructor(idUsuario: String?, visitaMedic: String?, cigarrillo: String?, alcohol: String?, estupefacientes: String?, hipertension: String?, actFisica: String?, obesidad: String?, colesterol: String?, diabetes: String?, pasaTiempo: String?) {
        this.idUsuario = idUsuario
        this.visitaMedic = visitaMedic
        this.cigarrillo = cigarrillo
        this.alcohol = alcohol
        this.estupefacientes = estupefacientes
        this.hipertension = hipertension
        this.actFisica = actFisica
        this.obesidad = obesidad
        this.colesterol = colesterol
        this.diabetes = diabetes
        this.pasaTiempo = pasaTiempo
    }


}