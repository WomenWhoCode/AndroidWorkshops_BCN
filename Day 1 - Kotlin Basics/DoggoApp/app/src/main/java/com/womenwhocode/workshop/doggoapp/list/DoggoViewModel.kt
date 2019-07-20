package com.womenwhocode.workshop.doggoapp.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.data.DoggosRepository
import kotlinx.coroutines.*


class DoggoViewModel(application: Application) : AndroidViewModel(application) {

    private val doggos: MutableLiveData<List<Doggo>> = MutableLiveData()
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private var repository: DoggosRepository = DoggosRepository(application)

    fun getDoggos(): MutableLiveData<List<Doggo>> {
        coroutineScope.launch {
            doggos.value = loadDoggos()
        }
        return doggos
    }

    private suspend fun loadDoggos(): List<Doggo>? {
        return withContext(Dispatchers.IO) {
            repository.getAllDoggos().takeIf { it.isNotEmpty() } ?: repository.downloadDoggos()
        }
    }
}
