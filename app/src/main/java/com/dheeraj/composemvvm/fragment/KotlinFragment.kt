package com.dheeraj.composemvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.postDelayed
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheeraj.composemvvm.adpter.RvAdapter
import com.dheeraj.composemvvm.databinding.FragmentKotlinBinding
import com.dheeraj.composemvvm.model.Demo
import com.dheeraj.composemvvm.viewmodel.MarsPhotoViewModel

class KotlinFragment : BaseFragment() {

    private lateinit var rvAdapter: RvAdapter

    private lateinit var binding: FragmentKotlinBinding

    private val viewModel: MarsPhotoViewModel by viewModels()
    private var photoList: List<Demo> = emptyList()

    var lastRefreshTime: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentKotlinBinding.inflate(inflater,container,false)
        rvAdapter = RvAdapter(photoList)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = rvAdapter
        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            initData()
        }



        initData()

        viewModel.marsPhotoResponseLiveData.observe(viewLifecycleOwner, Observer { photos ->

            binding.swipeRefreshLayout.isRefreshing = false
            rvAdapter.updateData(photos.photos)

        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->

            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }


    override fun sortData(isAscend: Boolean) {
        rvAdapter.sortData(isAscend)
    }


    private fun initData(){
        viewModel.fetchMarsPhoto()
        lastRefreshTime = System.currentTimeMillis()
    }


    fun refreshData(){

    }

}