package com.womenwhocode.workshop.doggoapp.data

import android.content.Context
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.data.database.DoggoDao
import com.womenwhocode.workshop.doggoapp.data.database.DoggosRoomDatabase
import com.womenwhocode.workshop.doggoapp.data.networking.DogApi

class DoggosRepository(application: Context) {

    private val dogsDao: DoggoDao = DoggosRoomDatabase.getDatabase(application).doggoDao()

    fun getAllDoggos(): List<Doggo> {
        return dogsDao.getAllDoggos()
    }

    suspend fun downloadDoggos(): List<Doggo> {
        val dogs = DogApi.retrofitService.getDoggos().await()
        dogsDao.insertAll(dogs)
        return getAllDoggos()
    }
}