package com.example.apptender2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptender2.R
import com.example.apptender2.model.compras
import com.squareup.picasso.Picasso

class ComprasAdapter (private val context: Context, var clickListener: OnCompraItemClickListener):
    RecyclerView.Adapter<ComprasAdapter.ViewHolder> (){

    private var productolista= mutableListOf<compras>()

    fun setListData(data:MutableList<compras>){
        productolista=data

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):ViewHolder {

        val v=
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_compras,viewGroup,false)
        return ViewHolder (v)
    }
    inner class  ViewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
        fun binWew(product: compras, action:OnCompraItemClickListener) {
            itemView.findViewById<TextView>(R.id.title).text = product.title
            itemView.findViewById<TextView>(R.id.Precio).text=product.precio
            Picasso.with(context).load(product.image).into(itemView.findViewById<ImageView>(R.id.image))
            val btneliminar=itemView.findViewById<ImageButton>(R.id.eliminar)
            btneliminar.setOnClickListener {
                action.onItemcLick(product,adapterPosition)
            }

        }
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i:Int) {
        val product=productolista[i]
        viewHolder.binWew(product,clickListener)
    }

    override fun getItemCount(): Int {
        return if(productolista.size>0){
            productolista.size
        }else {
            0
        }

    }
}

interface OnCompraItemClickListener {
    fun onItemcLick (product: compras, position:Int)
}