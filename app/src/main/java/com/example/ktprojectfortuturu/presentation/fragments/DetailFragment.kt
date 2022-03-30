package com.example.ktprojectfortuturu.presentation.fragments

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ktprojectfortuturu.R
import com.example.ktprojectfortuturu.domain.AstroViewModel
import com.example.ktprojectfortuturu.presentation.activity.MainActivity
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var viewModel: AstroViewModel
    val args: DetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val picture = args.picture
        Glide.with(this).load(picture.url).into(dtImageView)
        dtTextView.text = picture.explanation
        dtTitle.text = picture.title

    }

}