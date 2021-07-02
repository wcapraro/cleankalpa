package it.willuz.cleandroid.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Author(
    @PrimaryKey val id: Int,
    val firstName: String,
    val lastName: String,
    val role: String)