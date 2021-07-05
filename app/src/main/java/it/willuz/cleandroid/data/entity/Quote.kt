package it.willuz.cleandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey val id: Int,
    val message: String,
    val author: Int,
    val timestamp: Long)