package com.womenwhocode.workshop.doggoapp.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.DoggosRepository
import com.womenwhocode.workshop.doggoapp.database.DoggosRoomDatabase
import com.womenwhocode.workshop.doggoapp.networking.DogApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DoggoViewModel(application: Application) : AndroidViewModel(application) {

    private val doggos: MutableLiveData<List<Doggo>> = MutableLiveData()
    private val doggosPersonal: LiveData<List<Doggo>>
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val repository: DoggosRepository

    init {
        val wordsDao = DoggosRoomDatabase.getDatabase(application).doggoDao()
        repository = DoggosRepository(wordsDao)
        doggosPersonal = repository.allPersonalDoggos
    }

    fun getDoggos(): MutableLiveData<List<Doggo>> {
        if (doggos.value.isNullOrEmpty()) {
            loadDoggos()
        }
        return doggos
    }

    fun getDoggosDataBase(): LiveData<List<Doggo>> {
        return doggosPersonal
    }


    private fun loadDoggos() {
        coroutineScope.launch {
            val getDoggosDeferred = DogApi.retrofitService.getDoggos()
            try {
                val listResult = getDoggosDeferred.await()
                doggos.value = listResult
                Log.d("DoggoViewModel", "Success: ${listResult.size} dogs retrieved")
            } catch (e: Exception) {
                Log.e("DoggoViewModel", "Failure: ${e.message}")
            }
        }
    }

}
