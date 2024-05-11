package com.minsa.dengue.ui.csalud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.minsa.dengue.R
import com.minsa.dengue.databinding.FragmentCsaludBinding

class CsaludFragment : Fragment() , OnMapReadyCallback {
    private var _binding: FragmentCsaludBinding? = null
    private lateinit var map:GoogleMap
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //-- enlazando fragment con enlcae Binding
        _binding = FragmentCsaludBinding.inflate(inflater, container, false)
        val root: View  = binding.root
        asignarReferencias()
        return root
    }

    private fun asignarReferencias() {
        // val mapFragment: SupportMapFragment =supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
//        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as? SupportMapFragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapa) as? SupportMapFragment

        if (mapFragment != null) {
            mapFragment.getMapAsync(this)
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        map =p0

        map.uiSettings.isZoomControlsEnabled =true;
        val coordenada = LatLng(-12.001589453275123,-77.0624926553245)
        val marcador = MarkerOptions().position(coordenada).title("UPC - Monterrico")
        map.addMarker(marcador)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenada,18f))
    }

}