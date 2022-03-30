package com.example.ktprojectfortuturu.presentation.fragments

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ktprojectfortuturu.R
import com.example.ktprojectfortuturu.domain.AstroViewModel
import com.example.ktprojectfortuturu.presentation.activity.MainActivity
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.paginationProgressBar
import kotlinx.android.synthetic.main.fragment_home.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var viewModel: AstroViewModel
    val args: DetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val picture = args.picture
        if (picture.media_type == "image") {
            showProgressBar()
            Glide.with(this).load(picture.url)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        hideProgressBar()
                        return false
                    }

                })
                .into(dtImageView)

        } else {
            tvForUrl.text = picture.url
            tvVidURL.visibility = View.VISIBLE
            tvForUrl.visibility = View.VISIBLE
        }

        dtTextView.text = picture.explanation
        dtTitle.text = picture.title

    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }

}