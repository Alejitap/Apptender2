package com.example.apptender2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptender2.model.productos
import com.example.apptender2.repositorio.repo

class productosteindaViewModel:ViewModel () {
    val repo=repo()
    fun libraryData(): LiveData<MutableList<productos>>{
        val mutabledata= MutableLiveData<MutableList<productos>>()
        repo.getlibraryData().observeForever { result ->
            mutabledata.value= result
        }
        return mutabledata
    }
}