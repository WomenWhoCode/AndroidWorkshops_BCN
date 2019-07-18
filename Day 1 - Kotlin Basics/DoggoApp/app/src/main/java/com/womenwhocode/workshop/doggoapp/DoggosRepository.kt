package com.womenwhocode.workshop.doggoapp

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.womenwhocode.workshop.doggoapp.database.DoggoDao

class DoggosRepository(private val doggoDao: DoggoDao) {
    val allPersonalDoggos: LiveData<List<Doggo>> = doggoDao.getAllDoggos()

    @WorkerThread
    suspend fun insert(doggo: Doggo): Long {
       return doggoDao.insert(doggo)
    }
}