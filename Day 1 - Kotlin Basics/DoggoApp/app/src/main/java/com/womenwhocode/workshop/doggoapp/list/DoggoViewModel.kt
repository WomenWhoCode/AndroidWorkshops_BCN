package com.womenwhocode.workshop.doggoapp.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.data.DoggosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DoggoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: DoggosRepository = DoggosRepository(application)
    private var doggos: LiveData<List<Doggo>>
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        doggos = repository.allDoggos
    }

    fun getDoggos(): LiveData<List<Doggo>> {
        return doggos
    }

    fun loadDoggos() {
        coroutineScope.launch {
            repository.downloadDoggos()
        }
    }
}
