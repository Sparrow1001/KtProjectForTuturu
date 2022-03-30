package com.example.ktprojectfortuturu.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ktprojectfortuturu.R
import com.example.ktprojectfortuturu.domain.AstroViewModel
import com.example.ktprojectfortuturu.domain.AstroViewModelProviderFactory
import com.example.ktprojectfortuturu.repository.AstroRepository
import com.example.ktprojectfortuturu.repository.database.AstroDatabase


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: AstroViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val astroRepository = AstroRepository(AstroDatabase(this))
        val viewModelProviderFactory = AstroViewModelProviderFactory(application, astroRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(AstroViewModel::class.java)
    }

}