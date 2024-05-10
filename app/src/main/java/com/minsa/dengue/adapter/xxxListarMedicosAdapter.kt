package com.minsa.dengue.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.minsa.dengue.R
import com.minsa.dengue.entidad.Medico


class xxxListarMedicosAdapter(val activity: Activity, myArray: ArrayList<Medico> ):BaseAdapter() {
    private var arrayMedicos: ArrayList<Medico> = myArray
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.filamedicos,null)

        val mtvNombre = view.findViewById<TextView>(R.id.tvNombre)



        val mimbEditar = view.findViewById<ImageButton>(R.id.ibtEditar)

        mtvNombre.text = arrayMedicos.get(position).nombres

        mimbEditar.setOnClickListener { object : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(activity,
                    "Estado no válido para el envío de Correo.",
                    Toast.LENGTH_SHORT).show()
            }

        } }
        return view
    }

    override fun getCount(): Int {
       return arrayMedicos.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }



}