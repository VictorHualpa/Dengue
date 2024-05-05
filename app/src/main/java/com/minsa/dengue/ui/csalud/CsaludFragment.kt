package com.minsa.dengue.ui.csalud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minsa.dengue.R
import com.minsa.dengue.databinding.FragmentCsaludBinding

class CsaludFragment : Fragment() {
    private var _binding: FragmentCsaludBinding? = null
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

        return root
    }

}