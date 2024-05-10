// ListaMedicoAdapter.kt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minsa.dengue.R
import com.minsa.dengue.entidad.Medico

interface OnClickListener {
    fun onClick(id: Int
    )
}
class ListaMedicoAdapter : RecyclerView.Adapter<ListaMedicoAdapter.MiViewHolder>() {
    private var lista: ArrayList<Medico> = ArrayList()

    fun agregarItems(item: ArrayList<Medico>) {
        this.lista = item
        notifyDataSetChanged()
    }

    class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        var btnEditar = view.findViewById<ImageButton>(R.id.ibtEditar)

        fun setearValores(medico: Medico) {
            tvNombre.text = medico.nombres
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.filamedicos, parent, false)
        return MiViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val medicoItem = lista[position]
        holder.setearValores(medicoItem)

        holder.btnEditar.setOnClickListener {
         /*listener.onClick(
             1
         )*/
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
