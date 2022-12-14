package com.example.apptender2.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apptender2.model.compras
import com.example.apptender2.model.productos
import com.google.firebase.firestore.FirebaseFirestore


class repo {
    fun getlibraryData():LiveData<MutableList<productos>>{
        val mutabledata=MutableLiveData<MutableList<productos>>()

        FirebaseFirestore.getInstance().collection("productos").get()
            .addOnSuccessListener { result->
                val listData= mutableListOf<productos>()
                for(document in result){
                    val title=document.getString ("title")
                    val precio=document.getString ("precio")
                    val image=document.getString ("image")
                    val product=productos(title!!,precio!!,image!!)
                    listData.add(product)

                }
                mutabledata.value=listData
            }
        return mutabledata
    }

    fun getComprasData():LiveData<MutableList<compras>>{
        val mutabledata=MutableLiveData<MutableList<compras>>()
    FirebaseFirestore.getInstance().collection("compras")
        .get().addOnSuccessListener {
                result ->
            val listData = mutableListOf<compras>()
            for (document in result) {
                val titulo = document.getString("titulo")
                val precio = document.getString("precio")
                val image = document.getString("image")
                val compra = compras(titulo!!, precio!!, image!!)
                listData.add(compra)
            }

            mutabledata.value=listData

        }
    return mutabledata
        }
    }
