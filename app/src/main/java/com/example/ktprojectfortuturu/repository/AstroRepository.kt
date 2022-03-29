package com.example.ktprojectfortuturu.repository

import com.example.ktprojectfortuturu.repository.database.AstroDatabase
import com.example.ktprojectfortuturu.repository.network.RetrofitInstance

class AstroRepository(
    val db: AstroDatabase
) {
    suspend fun getAstroPictures() =
        RetrofitInstance.api.getAstroPictures()
}