package com.womenwhocode.workshop.doggoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 *  :3
 */
@Dao
interface WordDao {

    @Query("SELECT * from dogs_table ORDER BY dog_name ASC")
    fun getAllDoggos(): LiveData<List<DoggoEntity>>

    @Insert
    suspend fun insert(word: DoggoEntity)

}