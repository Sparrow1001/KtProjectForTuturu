package com.example.ktprojectfortuturu.repository

import com.example.ktprojectfortuturu.repository.database.AstroDatabase
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO
import com.example.ktprojectfortuturu.repository.network.RetrofitInstance

class AstroRepository(
    val db: AstroDatabase
) {
    suspend fun getAstroPictures() =
        RetrofitInstance.api.getAstroPictures()

    suspend fun upsert(picture: AstroPicturesDTO) = db.getAstroPictureDao().upsert(picture)

    fun getSavedPictures() = db.getAstroPictureDao().getAllPictures()
}