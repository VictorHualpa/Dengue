package com.minsa.dengue.ui.medico

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.minsa.dengue.R
import com.minsa.dengue.databinding.FragmentCsaludBinding
import com.minsa.dengue.databinding.FragmentMedicoBinding
import com.minsa.dengue.global.Total
import org.json.JSONObject

class MedicoFragment : Fragment() {

    private var _binding: FragmentMedicoBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMedicoBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val btnGrabar: Button = binding.btnGrabar
        btnGrabar.setOnClickListener {
            grabarRegistro()
        }

        return root

    }

    private fun grabarRegistro() {
        //-- Validar
        //-- Grabar

        // Crear una cola de solicitudes
        val queue = Volley.newRequestQueue(requireContext())

        // URL del servicio
        val url = Total.rutaServicio + "api/Medico/InserUpdate"

        // Crear el objeto JSON a enviar
        val jsonObject = JSONObject().apply {
            put("idMedico", 0)
            put("nombres", "Nombres")
            put("apellidos", "string")
            put("idTipDoc", 0) // 1: DNI , 2. pasaporte
            put("nroDocumento", "string")
            put("especialidad", "string")
            put("telefono", "string")
            put("correo", "string")
            put("foto", "string")
            put("tipoAccion", 1) // 1-insertar 2-editar
            put("userAccion", "string") // idusuario para auditoria
        }

        // Crear la solicitud POST
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                // Manejar la respuesta
                Log.d("RESPUESTA", response.toString())
                respuestaServidor(response)
            },
            Response.ErrorListener { error ->
                // Manejar errores
                Log.e("ERROR", "Error en la solicitud: ${error.message}")
            }
        )

        // Agregar la solicitud a la cola
        queue.add(jsonObjectRequest)
    }


    private fun respuestaServidor(response: JSONObject) {
        // Aquí manejas la respuesta del servidor
        // response es un objeto JSONObject que contiene toda la respuesta del servidor
        // Puedes acceder a sus elementos y procesarlos según sea necesario
        Log.d("RESPUESTA_SERVIDOR", response.toString())
        // Por ejemplo, puedes obtener valores específicos del JSON así:
        val isCorrect = response.getBoolean("iscorrect")
        val status = response.getInt("status")
        val message = response.getString("message")
        val dataObject = response.getJSONObject("data")

        if (status.equals(200)  and message.equals("exito")) {
            mostrarMensaje("Se Registro Correctamente.")
        }

    }


    fun mostrarMensaje(mensaje:String){
        val ventana = AlertDialog.Builder(requireContext())
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->

        })
        ventana.create().show()
    }

}