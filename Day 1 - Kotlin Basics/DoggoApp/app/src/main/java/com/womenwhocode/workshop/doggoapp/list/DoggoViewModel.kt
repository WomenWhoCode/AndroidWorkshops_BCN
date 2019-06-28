package com.womenwhocode.workshop.doggoapp.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.networking.DogApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DoggoViewModel: ViewModel() {

    private val doggos: MutableLiveData<List<Doggo>> = MutableLiveData()
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getDoggos(): MutableLiveData<List<Doggo>> {
        if (doggos.value.isNullOrEmpty()) {
            loadDoggos()
        }
        return doggos
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
