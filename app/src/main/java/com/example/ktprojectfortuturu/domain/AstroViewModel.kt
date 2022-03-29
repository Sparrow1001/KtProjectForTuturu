package com.example.ktprojectfortuturu.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktprojectfortuturu.repository.AstroRepository
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO
import com.example.ktprojectfortuturu.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class AstroViewModel(
    val astroRepository: AstroRepository
): ViewModel() {

    val astroPicture: MutableLiveData<Resource<List<AstroPicturesDTO>>> = MutableLiveData()

    init {
        getAstroPictures()
    }

    fun getAstroPictures() = viewModelScope.launch {
        astroPicture.postValue(Resource.Loading())
        val response =  astroRepository.getAstroPictures()
        astroPicture.postValue(handleAstroPictureResponse(response))
    }

    private fun handleAstroPictureResponse(response: Response<List<AstroPicturesDTO>>):Resource<List<AstroPicturesDTO>>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}