package com.example.ktprojectfortuturu.repository.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO

@Dao
interface AstroPicturesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(picturesDTO: AstroPicturesDTO): Long

    @Query("SELECT * FROM pictures")
    fun getAllPictures(): LiveData<List<AstroPicturesDTO>>

}