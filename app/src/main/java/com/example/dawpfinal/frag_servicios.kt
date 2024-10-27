package com.example.dawpfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.AdaptadorPlato
import com.example.proyecto.EntidadPlato

class frag_servicios : Fragment() {

    private lateinit var recyclerPlato: RecyclerView
    private lateinit var adaptadorPlato: AdaptadorPlato

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_servicios, container, false)

        // Initialize RecyclerView
        recyclerPlato = view.findViewById(R.id.recyclerviewCeviche)
        recyclerPlato.layoutManager = LinearLayoutManager(requireContext())

        // Populate data
        llenar_data()

        return view
    }

    private fun llenar_data() {
        val listaPlato = mutableListOf(
            EntidadPlato(R.drawable.lechetigre, nombrePlato = "Leche de tigre", descripPlato = "Fresco e irresistible concentrado de nuestro ceviche.", precioPlato = 15.00),
            EntidadPlato(R.drawable.arrozmariscos, nombrePlato = "Arroz con mariscos", descripPlato = "Arroz atomatado con secretos del fundador.", precioPlato = 27.00),
            EntidadPlato(R.drawable.jaleamixta, nombrePlato = "Jalea mixta", descripPlato = "Crocantes, calientes y sabrosos.", precioPlato = 30.00),
            EntidadPlato(R.drawable.chupelangostinos, nombrePlato = "Chupe de langostinos", descripPlato = "Jugosos langostinos sumergidos en un caldo especial.", precioPlato = 25.00)
        )

        adaptadorPlato = AdaptadorPlato(listaPlato)
        recyclerPlato.adapter = adaptadorPlato
    }
}
