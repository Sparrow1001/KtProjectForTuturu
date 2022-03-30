package com.example.ktprojectfortuturu.domain

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.*
import com.example.ktprojectfortuturu.AstroApplication
import com.example.ktprojectfortuturu.repository.AstroRepository
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO
import com.example.ktprojectfortuturu.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class AstroViewModel(
    app: Application,
    val astroRepository: AstroRepository
): AndroidViewModel(app) {

    val astroPicture: MutableLiveData<Resource<List<AstroPicturesDTO>>> = MutableLiveData()

    init {
        getAstroPictures()
    }

    fun getAstroPictures() = viewModelScope.launch {
        safeAstroPicturesCall()
    }

    private fun handleAstroPictureResponse(response: Response<List<AstroPicturesDTO>>):Resource<List<AstroPicturesDTO>>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                for (i in 0..resultResponse.size - 1) savePicture(resultResponse.get(i))
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun savePicture(picture: AstroPicturesDTO) = viewModelScope.launch {
        astroRepository.upsert(picture)
    }

    fun getSavedPictures()  = astroRepository.getSavedPictures()

    private suspend fun safeAstroPicturesCall() {
        astroPicture.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = astroRepository.getAstroPictures()
                astroPicture.postValue(handleAstroPictureResponse(response))
            } else {
                val response = getSavedPictures()
                astroPicture.postValue(Resource.Error("No internet connection"))
                astroPicture.postValue(Resource.NoInternet(response))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> astroPicture.postValue(Resource.Error("Network Failure"))
                else -> astroPicture.postValue(Resource.Error("Conversion Error: ${t.message}"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<AstroApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
           connectivityManager.activeNetworkInfo?.run {
               return when(type){
                   TYPE_WIFI -> true
                   TYPE_MOBILE -> true
                   TYPE_ETHERNET -> true
                   else -> false
               }
           }
        }
        return false
    }

}