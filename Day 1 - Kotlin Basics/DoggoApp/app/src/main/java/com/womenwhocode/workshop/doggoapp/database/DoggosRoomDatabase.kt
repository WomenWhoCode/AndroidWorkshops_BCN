package com.womenwhocode.workshop.doggoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(DoggoEntity::class), version = 1)
public abstract class DoggosRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

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