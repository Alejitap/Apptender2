package com.example.apptender2.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptender2.R
import com.example.apptender2.model.productos
import com.example.apptender2.view.adapter.OnBookItemClickListener
import com.example.apptender2.view.adapter.tiendaAdapter
import com.example.apptender2.viewmodel.productosteindaViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class ListaTiendaFragment : Fragment(),OnBookItemClickListener {

        lateinit var recyclerTiend: RecyclerView
        lateinit var firebaseAuth: FirebaseAuth
        lateinit var adapter: tiendaAdapter
        val database:FirebaseFirestore=FirebaseFirestore.getInstance()
        private val viewModel by lazy { ViewModelProvider(this).get(productosteindaViewModel:: class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tienda, container, false)
        recyclerTiend = view.findViewById(R.id.recyclerTienda)
        adapter = tiendaAdapter(requireContext(),this)
        recyclerTiend.layoutManager = LinearLayoutManager(context)
        recyclerTiend.adapter=adapter
        observeData()
        return view
        }
        fun observeData() {
        viewModel.libraryData().observe(viewLifecycleOwner,Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm = view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.Home -> findNavController().navigate(R.id.action_listaTiendaFragment_to_home2Fragment)
                R.id.Perf -> findNavController().navigate(R.id.action_listaTiendaFragment_to_misdatosFragment)
                R.id.Map -> findNavController().navigate(R.id.action_listaTiendaFragment_to_mapasFragment)
                R.id.homeFragment -> findNavController().navigate(R.id.action_listaTiendaFragment_to_homeFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_homeFragment_to_loginActivity)
                    true


                }
            }
        }
    }

    override fun onItemcLick(product: productos, position: Int) {
        val titulo:String=product.title
        val precio:String=product.precio
        val image:String=product.image
        val dato= hashMapOf(
            "titulo" to titulo,
            "precio" to precio,
            "image" to image
        )

        database.collection("compras")
            .document(titulo)
            .set(dato)
            .addOnSuccessListener {
                Toast.makeText(context,"Producto añadido al Carrito",Toast.LENGTH_SHORT).show()
            }

    }
}




