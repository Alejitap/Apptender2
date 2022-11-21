package com.example.apptender2.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptender2.R
import com.example.apptender2.model.compras
import com.example.apptender2.view.adapter.ComprasAdapter
import com.example.apptender2.view.adapter.OnCompraItemClickListener
import com.example.apptender2.viewmodel.ComprasViewModel
import com.google.firebase.firestore.FirebaseFirestore


class comprasFragment : Fragment(), OnCompraItemClickListener   {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComprasAdapter
    val database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val viewModel by lazy { ViewModelProvider(this).get(ComprasViewModel:: class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_compras, container, false)
        recyclerView=view.findViewById(R.id.recyclerviewcompras)
        adapter=ComprasAdapter(requireContext(),this)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter
        observerData()
        return view
    }

    private fun observerData() {
viewModel.fetchComprasData().observe(viewLifecycleOwner,Observer{
    adapter.setListData(it)
adapter.notifyDataSetChanged()
})    }

    override fun onItemcLick(product: compras, position: Int) {
        TODO("Not yet implemented")
    }


}

