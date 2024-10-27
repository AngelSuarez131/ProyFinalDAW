package com.example.proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dawpfinal.R

class AdaptadorPlato(private val listaPlato:List<EntidadPlato>):
                        RecyclerView.Adapter<AdaptadorPlato.PlatoViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_platos, parent, false)
        return PlatoViewHolder(view)
    }

    override fun getItemCount(): Int = listaPlato.size

    override fun onBindViewHolder(holder: PlatoViewHolder, position: Int) {
        val plato = listaPlato[position]
        holder.t_nombreplato.text = plato.nombrePlato
        holder.t_descripplato.text = plato.descripPlato
        holder.t_precioplato.text = "S/.${plato.precioPlato}"
        holder.img_plato.setImageResource(plato.imagenPlato)
    }

    class PlatoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val img_plato: ImageView=itemView.findViewById(R.id.imgPlato)
        val t_nombreplato: TextView=itemView.findViewById(R.id.tvNombrePlato)
        val t_descripplato: TextView=itemView.findViewById(R.id.tvDescripPlato)
        val t_precioplato: TextView=itemView.findViewById(R.id.tvPrecioPlato)
        val b_anadirplato: Button=itemView.findViewById(R.id.btnAnadir)

    }
}