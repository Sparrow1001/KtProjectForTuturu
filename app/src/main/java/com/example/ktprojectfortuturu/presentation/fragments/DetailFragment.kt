package com.example.ktprojectfortuturu.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.ktprojectfortuturu.R
import com.example.ktprojectfortuturu.domain.AstroViewModel
import com.example.ktprojectfortuturu.presentation.activity.MainActivity

class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var viewModel: AstroViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

}