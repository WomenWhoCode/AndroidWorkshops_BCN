package com.womenwhocode.workshop.doggoapp.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.DoggosRepository
import com.womenwhocode.workshop.doggoapp.database.DoggosRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DoggoViewModel(application: Application) : AndroidViewModel(application) {


    private val doggos: LiveData<List<Doggo>>
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val repository: DoggosRepository

    init {
        val wordsDao = DoggosRoomDatabase.getDatabase(application).doggoDao()
        repository = DoggosRepository(wordsDao)
        doggos = repository.allPersonalDoggos
    }

    fun getDoggos(): LiveData<List<Doggo>> {
        return doggos
    }

    fun insert(doggo: Doggo) {
        coroutineScope.launch {
            var success = repository.insert(doggo)
            Log.d("DoggoViewModel", "Success: ${success} dogs retrieved from databae")
        }
    }

}
