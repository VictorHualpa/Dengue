package com.minsa.dengue.entidad

data class Medico(
    val idMedico: Int,
    val nombres: String,
    val apellidos: String,
    val idTipDoc: Int,
    val nroDocumento: String,
    val especialidad: String,
    val telefono: String,
    val correo: String,
    val foto: String
)