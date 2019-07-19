package com.womenwhocode.workshop.doggoapp.data

import android.content.Context
import com.womenwhocode.workshop.doggoapp.data.database.DoggoDao
import com.womenwhocode.workshop.doggoapp.data.database.DoggosRoomDatabase
import com.womenwhocode.workshop.doggoapp.data.networking.DogApi

class DoggosRepository(application: Context) {

    private val dogsDao: DoggoDao = DoggosRoomDatabase.getDatabase(application).doggoDao()

    val allDoggos = dogsDao.getAllDoggos()

    suspend fun downloadDoggos() {
        val dogs = DogApi.retrofitService.getDoggos().await()
        dogsDao.insertAll(dogs)
    }
}