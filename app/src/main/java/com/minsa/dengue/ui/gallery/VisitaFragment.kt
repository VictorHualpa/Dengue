package com.minsa.dengue.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.minsa.dengue.R
import com.minsa.dengue.databinding.FragmentVisitaBinding


class VisitaFragment : Fragment() {

    private var _bindind:FragmentVisitaBinding? =null
    private val binding get() = _bindind!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _bindind =FragmentVisitaBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_visita, container, false)

        cargarComboInitForm()




        binding.tilVPNroDoc.setEndIconOnClickListener {
            Toast.makeText(requireContext(),"CLickec",Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }


    fun  cargarComboInitForm(){

        // cargando combo del Interfaz
        val centroSalud = mutableListOf<String>()
        centroSalud.add("C.S.San Sebastián")
        centroSalud.add("C.S. Raul Patrucco Puig")
        centroSalud.add("C.S. Flor de Amancaes")
        centroSalud.add("C.S. Los Olivos")
        centroSalud.add("C.S. Chacra Colorada")

        val  spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,centroSalud)
        binding.cboVPCentroSalud.adapter =spinnerAdapter

        // cargando combo del Interfaz
        val medicos = mutableListOf<String>()
        medicos.add("Maria Guzman Perez")
        medicos.add("Carlos Pillaca Flores")
        medicos.add("Manuel Enciso Cardenas")
        medicos.add("Pedro Gomez Manzano")
        medicos.add("Alejandro Coras Cardenas")

        val  spinnerAdapterMedico: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,medicos)
        binding.cboVPMedico.adapter =spinnerAdapterMedico

        binding.imgFoto1.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_peple))
        //binding.imgFoto1.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primaryColor))




    }


}