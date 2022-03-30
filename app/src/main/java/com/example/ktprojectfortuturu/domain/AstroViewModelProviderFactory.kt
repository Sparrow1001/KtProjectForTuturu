package com.example.ktprojectfortuturu.domain

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ktprojectfortuturu.repository.AstroRepository

class AstroViewModelProviderFactory(
    val app: Application,
    val astroRepository: AstroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AstroViewModel(app, astroRepository) as T
    }
}