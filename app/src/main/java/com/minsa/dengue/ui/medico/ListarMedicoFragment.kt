package com.minsa.dengue.ui.medico

import ListaMedicoAdapter
import OnClickListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.minsa.dengue.R
import com.minsa.dengue.databinding.FragmentListarMedicoBinding
import com.minsa.dengue.databinding.FragmentMedicoBinding
import com.minsa.dengue.entidad.Medico
import com.minsa.dengue.global.Total

class ListarMedicoFragment() : Fragment(), OnClickListener {

    private var _binding: FragmentListarMedicoBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvMedicos: RecyclerView
    private lateinit var adaptador: ListaMedicoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        asignarReferencias(view)
        configurarRecyclerView()
        mostrarMedicos()
    }

    fun asignarReferencias(view: View) {
        rvMedicos = view.findViewById(R.id.rvMedicos)
    }
    private fun configurarRecyclerView() {
        adaptador = ListaMedicoAdapter(this) // Inicializar el adaptador aquí

        rvMedicos.layoutManager = LinearLayoutManager(requireContext())
        rvMedicos.adapter = adaptador
    }



    private fun mostrarMedicos() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = Total.rutaServicio + "api/Medico/Listar"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val medicosJsonArray = response.getJSONObject("data").getJSONArray("Medico")
                val medicosList = ArrayList<Medico>()

                for (i in 0 until medicosJsonArray.length()) {
                    val medicoJson = medicosJsonArray.getJSONObject(i)
                    val medico = Medico(
                        medicoJson.getInt("idMedico"),
                        medicoJson.getString("nombres"),
                        medicoJson.getString("apellidos"),
                        medicoJson.getInt("idTipDoc"),
                        medicoJson.getString("nroDocumento"),
                        medicoJson.getString("especialidad"),
                        medicoJson.getString("telefono"),
                        medicoJson.getString("correo"),
                        medicoJson.getString("foto")
                    )
                    medicosList.add(medico)
                }

                adaptador.agregarItems(medicosList)
                rvMedicos.layoutManager = LinearLayoutManager(requireContext())
                rvMedicos.adapter = adaptador
            },
            Response.ErrorListener { error ->
                Log.e("ERROR", "Error en la solicitud: ${error.message}")
            }
        )

        queue.add(jsonObjectRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListarMedicoBinding.inflate(inflater, container, false)

        val btnNuevo = binding.btnNuevo

        btnNuevo.setOnClickListener {
            findNavController().navigate(R.id.nav_medico)
        }

        val root: View = binding.root

        return root
    }

    override fun onClick(
        idTabla: String,
        nombres: String,
        apellidos: String,
        idTipDoc: String,
        nroDocumento: String,
        especialidad: String,
        telefono: String,
        correo: String,
        foto: String,
        tipoAccion: String,
        userAccion: String
    ) {
        val action = ListarMedicoFragmentDirections.actionNavListamedicoToNavMedico(
            idTabla,
            nombres,
            apellidos,
            idTipDoc,
            nroDocumento,
            especialidad,
            telefono,
            correo,
            foto,
            tipoAccion,
            userAccion
        )
        findNavController().navigate(action)
    }



}
