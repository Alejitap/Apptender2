package com.example.apptender2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptender2.model.compras
import com.example.apptender2.repositorio.repo

class ComprasViewModel:ViewModel () {
    val repo= repo()
    fun fetchComprasData(): LiveData<MutableList<compras>> {
        val mutabledata= MutableLiveData<MutableList<compras>>()
        repo.getComprasData().observeForever {result ->
            mutabledata.value= result
        }
        return mutabledata
    }
}
