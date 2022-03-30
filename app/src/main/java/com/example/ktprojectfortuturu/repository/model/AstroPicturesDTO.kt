package com.example.ktprojectfortuturu.repository.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "pictures",
    indices = [Index(value = ["date"],unique = true)]
)
data class AstroPicturesDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val date: String,
    val explanation: String,
    val title: String,
    val url: String
) : Serializable
