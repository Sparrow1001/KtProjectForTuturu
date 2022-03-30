package com.example.ktprojectfortuturu.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktprojectfortuturu.R
import com.example.ktprojectfortuturu.domain.AstroViewModel
import com.example.ktprojectfortuturu.presentation.activity.MainActivity
import com.example.ktprojectfortuturu.presentation.adapters.PicturesAdapter
import com.example.ktprojectfortuturu.utils.Resource
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: AstroViewModel
    lateinit var picturesAdapter: PicturesAdapter

    val TAG = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        picturesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("picture", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundle
            )
        }

        viewModel.astroPicture.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { astroResponse ->
                        picturesAdapter.differ.submitList(astroResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.NoInternet -> {
                    hideProgressBar()
                    response.liveData?.let { astroResponse ->
                        astroResponse.observe(viewLifecycleOwner, Observer { response ->
                            picturesAdapter.differ.submitList(response)
                        })

                    }
                }
            }
        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        picturesAdapter = PicturesAdapter()
        rvHome.apply {
            adapter = picturesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}