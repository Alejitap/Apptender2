package com.example.apptender2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.apptender2.R
import com.example.apptender2.model.productos
import com.squareup.picasso.Picasso



class tiendaAdapter(private val context: Context):RecyclerView.Adapter<tiendaAdapter.ViewHolder> (){

    private var productolista= mutableListOf<productos>()

    fun setListData(data:MutableList<productos>){
        productolista=data

    }

    override fun onCreateViewHolder(viewGroup:ViewGroup,i: Int):ViewHolder {

        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_tienda,viewGroup,false)
        return ViewHolder (v)
    }
    inner class  ViewHolder (ItemView: View):RecyclerView.ViewHolder(ItemView) {
        fun binWew(product:productos) {
            itemView.findViewById<TextView>(R.id.title).text = product.title
            itemView.findViewById<TextView>(R.id.Precio).text=product.precio
            Picasso.with(context).load(product.image).into(itemView.findViewById<ImageView>(R.id.image))

        }
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i:Int) {
        val product=productolista[i]
        viewHolder.binWew(product)
    }

    override fun getItemCount(): Int {
        return if(productolista.size>0){
            productolista.size
        }else {
            0
        }

    }
}