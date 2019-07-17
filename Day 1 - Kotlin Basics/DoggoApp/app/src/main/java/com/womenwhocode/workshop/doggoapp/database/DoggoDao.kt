package com.womenwhocode.workshop.doggoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.womenwhocode.workshop.doggoapp.Doggo

/**
 *  :3
 */
@Dao
interface DoggoDao {

    @Query("SELECT * from dogs_table ORDER BY dog_name ASC")
    fun getAllDoggos(): LiveData<List<Doggo>>

    @Insert
    suspend fun insert(word: Doggo)

}