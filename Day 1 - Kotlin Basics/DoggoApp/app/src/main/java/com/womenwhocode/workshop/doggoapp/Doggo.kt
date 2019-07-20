package com.womenwhocode.workshop.doggoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class Doggo(
    @PrimaryKey(autoGenerate = true) val _id: Long = 0,
    val name: String,
    val age: String,
    val size: String,
    val url: String
)
