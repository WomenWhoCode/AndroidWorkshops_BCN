package com.womenwhocode.workshop.doggoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.womenwhocode.workshop.doggoapp.Doggo

@Dao
interface DoggoDao {

    @Query("SELECT * from dogs ORDER BY name ASC")
    fun getAllDoggos(): LiveData<List<Doggo>>

    @Insert
    fun insert(doggo: Doggo): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(doggos: List<Doggo>)

}