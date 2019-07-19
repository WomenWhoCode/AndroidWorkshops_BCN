package com.womenwhocode.workshop.doggoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.womenwhocode.workshop.doggoapp.Doggo


@Database(entities = [Doggo::class], version = 1)
abstract class DoggosRoomDatabase : RoomDatabase() {

    abstract fun doggoDao(): DoggoDao

    companion object {
        @Volatile
        private var INSTANCE: DoggosRoomDatabase? = null

        fun getDatabase(context: Context): DoggosRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DoggosRoomDatabase::class.java,
                    "Doggos_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}