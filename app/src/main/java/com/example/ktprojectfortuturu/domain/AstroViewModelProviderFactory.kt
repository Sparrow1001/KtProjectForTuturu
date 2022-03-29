package com.example.ktprojectfortuturu.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ktprojectfortuturu.repository.AstroRepository

class AstroViewModelProviderFactory(
    val astroRepository: AstroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AstroViewModel(astroRepository) as T
    }
}