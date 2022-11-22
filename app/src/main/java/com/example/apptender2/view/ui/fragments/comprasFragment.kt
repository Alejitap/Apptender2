package com.example.apptender2.view.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptender2.R
import com.example.apptender2.model.compras
import com.example.apptender2.view.adapter.ComprasAdapter
import com.example.apptender2.view.adapter.OnCompraItemClickListener
import com.example.apptender2.viewmodel.ComprasViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class comprasFragment : Fragment(), OnCompraItemClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComprasAdapter
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var precioT: TextView
    lateinit var compraT: Button
    lateinit var valoriva: TextView

    val database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val viewModel by lazy { ViewModelProvider(this).get(ComprasViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_compras, container, false)
        recyclerView = view.findViewById(R.id.recyclerviewcompras)
        precioT=view.findViewById(R.id.preciototal)
        compraT=view.findViewById(R.id.realizar)
        valoriva=view.findViewById(R.id.iva)
        adapter = ComprasAdapter(requireContext(), this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        observerData()
        preciototal()

        compraT.setOnClickListener{
            realizarcompra()
        }

        return view
    }

    private fun observerData() {
        viewModel.fetchComprasData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }



    private fun preciototal(){
        database.collection("compras")
            .get()
            .addOnSuccessListener {
                result->
                val preciounitario= mutableListOf<String>()
                for (document in result){
                    val precio=document["precio"].toString()
                    preciounitario.add(precio!!)
                }
                    val preciototal=preciounitario.mapNotNull { it.toIntOrNull()}.sum()
                precioT.setText(Integer.toString(preciototal))
            }
    }


private fun realizarcompra(){
    val builder=AlertDialog.Builder(requireContext())
    builder.setTitle("Compra AppTender")
    builder.setMessage( "¿Desea realizar esta compra?")
    builder.setPositiveButton("Aceptar"){
dialog,which->
    findNavController().navigate(R.id.action_comprasFragment_to_home2Fragment)
    }
    builder.setNegativeButton("Cancelar", null)
    builder.show()
    }

    override fun onItemcLick(product: compras, position: Int) {
database.collection("compras")
    .document(product.title)
    .delete()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm = view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.Home -> findNavController().navigate(R.id.action_comprasFragment_to_home2Fragment)
                R.id.Perf -> findNavController().navigate(R.id.action_comprasFragment_to_misdatosFragment)
                R.id.Map -> findNavController().navigate(R.id.action_comprasFragment_to_mapasFragment)
                R.id.homeFragment -> findNavController().navigate(R.id.action_comprasFragment_to_listaTiendaFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_homeFragment_to_loginActivity)
                    true

                }


            }
        }
    }
}


