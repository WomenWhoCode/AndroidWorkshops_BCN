package com.womenwhocode.workshop.doggoapp.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.DoggosRepository
import com.womenwhocode.workshop.doggoapp.database.DoggosRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *  :3
 */
class ShowDoggoViewModel(application: Application) : AndroidViewModel(application) {


    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val repository: DoggosRepository

    init {
        val wordsDao = DoggosRoomDatabase.getDatabase(application).doggoDao()
        repository = DoggosRepository(wordsDao)

    }

    fun insert(doggo: Doggo) {
        coroutineScope.launch {
            repository.insert(doggo)
        }
    }

}