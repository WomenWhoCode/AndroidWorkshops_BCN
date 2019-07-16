package com.womenwhocode.workshop.doggoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs_table")
data class DoggoEntity(@PrimaryKey @ColumnInfo(name = "dog_name")val name: String, val age: String, val size: String, val url: String)