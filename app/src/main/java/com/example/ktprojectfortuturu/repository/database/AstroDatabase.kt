package com.example.ktprojectfortuturu.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO

@Database(
    entities = [AstroPicturesDTO::class],
    version = 2
)
abstract class AstroDatabase : RoomDatabase() {

    abstract fun getAstroPictureDao() : AstroPicturesDAO

    companion object{
        @Volatile
        private var instance: AstroDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AstroDatabase::class.java,
                "astro_database.db"
            ).build()

    }

}