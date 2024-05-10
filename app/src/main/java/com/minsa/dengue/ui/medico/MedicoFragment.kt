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

    private var idTabla: Int = 0
    private lateinit var nombres: String
    private lateinit var apellidos: String
    private var idTipDoc: Int = 0
    private lateinit var nroDocumento: String
    private lateinit var especialidad: String
    private lateinit var telefono: String
    private lateinit var correo: String
    private lateinit var foto: String
    private lateinit var tipoAccion: String
    private lateinit var userAccion: String

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

        //-- Argumento de la fila
        idTabla = arguments?.getString("idTabla", "0")?.toInt() ?: 0
        nombres = arguments?.getString("nombres", "").toString()
        apellidos = arguments?.getString("apellidos", "").toString()
        idTipDoc = arguments?.getString("idTipDoc", "0")?.toInt() ?: 0
        nroDocumento = arguments?.getString("nroDocumento", "").toString()
        especialidad = arguments?.getString("especialidad", "").toString()
        telefono = arguments?.getString("telefono", "").toString()
        correo = arguments?.getString("correo", "").toString()
        foto = arguments?.getString("foto", "").toString()
        apellidos = arguments?.getString("apellidos", "").toString()
        tipoAccion = arguments?.getString("tipoAccion", "1").toString()
        userAccion = arguments?.getString("userAccion", "").toString()

        //Cajas de Textos

        if (tipoAccion.equals("1")) {
            tipoAccion = "1"
            binding.lblMTitulo.text = "Ingresar"
        } else {
            binding.lblMTitulo.text = "Modificar"
        }

        //binding.cboMTipoDocumento.text =

        binding.txtMdNroDocumento.setText(nroDocumento)
        binding.txtMdNombre.setText(nombres)
        binding.txtMApellidos.setText(apellidos)
        binding.txtMEspecialidad.setText(especialidad)
        binding.txtMTelefono.setText(telefono)
        binding.txtMCorreo.setText(correo)

        val btnGrabar: Button = binding.btnMGuardar

        btnGrabar.setOnClickListener {
            grabarRegistro()
        }

        return root

    }

    private fun grabarRegistro() {

        val queue = Volley.newRequestQueue(requireContext())
        // URL del servicio
        val url = Total.rutaServicio + "api/Medico/InserUpdate"

        // Crear el objeto JSON a enviar
        val jsonObject = JSONObject().apply {
            if (tipoAccion.equals("1")) {
                put("idMedico", 0)
            } else {
                put("idMedico", idTabla.toString())
            }
            put("nombres", binding.txtMdNombre.text.toString())

            put("apellidos", binding.txtMApellidos.text.toString())
            put("idTipDoc", 1) // 1: DNI , 2. pasaporte
            put("nroDocumento", binding.txtMdNroDocumento.text.toString())
            put("especialidad", binding.txtMEspecialidad.text.toString())
            put("telefono", binding.txtMTelefono.text.toString())
            put("correo", binding.txtMCorreo.text.toString())
            put("foto", "string")
            put("tipoAccion", tipoAccion) // 1-insertar 2-editar
            put("userAccion", userAccion) // idusuario para auditoria

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

        if (status.equals(200) and message.equals("exito")) {
            mostrarMensaje("Se Registro Correctamente.")
        }

    }


    fun mostrarMensaje(mensaje: String) {
        val ventana = AlertDialog.Builder(requireContext())
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.popBackStack()
            /*if (fragmentManager.backStackEntryCount > 0) {
                // Si hay fragmentos en la pila de retroceso, popBackStack() elimina el fragmento actual y restaura el fragmento anterior
                fragmentManager.popBackStack()
            } else {
                // Si no hay fragmentos en la pila de retroceso, simplemente cierra la actividad o ejecuta la acción deseada
                requireActivity().finish()
            }
            if (fragmentManager.backStackEntryCount > 0) {
                // Si hay fragmentos en la pila de retroceso, popBackStack() elimina el fragmento actual y restaura el fragmento anterior
                fragmentManager.popBackStack()
            } else {
                // Si no hay fragmentos en la pila de retroceso, simplemente cierra la actividad o ejecuta la acción deseada
                requireActivity().finish()
            }*/
        })
        ventana.create().show()
    }

}