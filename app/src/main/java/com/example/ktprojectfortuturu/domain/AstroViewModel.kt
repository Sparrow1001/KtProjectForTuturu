package com.example.ktprojectfortuturu.domain

import androidx.lifecycle.ViewModel
import com.example.ktprojectfortuturu.repository.AstroRepository

class AstroViewModel(
    val astroRepository: AstroRepository
): ViewModel() {
}