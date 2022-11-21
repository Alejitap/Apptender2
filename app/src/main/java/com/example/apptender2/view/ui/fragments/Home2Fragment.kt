package com.example.apptender2.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apptender2.R

class Home2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardLib = view.findViewById<ImageView>(R.id.cardTienda)
        cardLib.setOnClickListener {
            findNavController().navigate(R.id.action_home2Fragment_to_homeFragment)
        }
        val cardDatos = view.findViewById<ImageView>(R.id.Mdatos)
        cardDatos.setOnClickListener {
            findNavController().navigate(R.id.action_home2Fragment_to_misdatosFragment)
        }
        val cardPedidos = view.findViewById<ImageView>(R.id.CTienda)
        cardPedidos.setOnClickListener {
            findNavController().navigate(R.id.action_home2Fragment_to_listaTiendaFragment)

        }
        val cardConfiguracion = view.findViewById<ImageView>(R.id.Carrito)
        cardConfiguracion.setOnClickListener {
            findNavController().navigate(R.id.action_home2Fragment_to_comprasFragment)

        }
        val cardComentarios = view.findViewById<ImageView>(R.id.Mcomentarios)
        cardComentarios.setOnClickListener {
            findNavController().navigate(R.id.action_home2Fragment_to_comentariosFragment)
        }

        val cardAyuda = view.findViewById<ImageView>(R.id.Mayuda)
        cardAyuda.setOnClickListener {
            findNavController().navigate(R.id.action_home2Fragment_to_mapasFragment)
        }
    }
}