// ListarMedicoFragment.kt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.minsa.dengue.R
import com.minsa.dengue.entidad.Medico

class ListarMedicoFragment : Fragment() {
    private lateinit var rvMedicos: RecyclerView
    private val adaptador: ListaMedicoAdapter = ListaMedicoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        asignarReferencias(view)
        mostrarMedicos()
    }

    fun asignarReferencias(view: View) {
        rvMedicos = view.findViewById(R.id.rvMedicos)
    }

    private fun mostrarMedicos() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://www.appdengue.somee.com/api/Medico/Listar"

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
                Log.e("ERROR", "Error en la solicitud: ${medicosList.size}")
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
        return inflater.inflate(R.layout.fragment_listar_medico, container, false)
    }

}
