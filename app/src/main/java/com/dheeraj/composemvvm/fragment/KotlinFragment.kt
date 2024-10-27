package com.dheeraj.composemvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheeraj.composemvvm.adpter.RvAdapter
import com.dheeraj.composemvvm.databinding.FragmentKotlinBinding
import com.dheeraj.composemvvm.model.Demo
import com.dheeraj.composemvvm.viewmodel.CreditCardViewModel

class KotlinFragment : Fragment() {

    private lateinit var rvAdapter: RvAdapter

    private lateinit var binding: FragmentKotlinBinding

    private val viewModel: CreditCardViewModel by viewModels()
    private var photoList: List<Demo> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentKotlinBinding.inflate(inflater,container,false)
        rvAdapter = RvAdapter(photoList)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = rvAdapter

        initData()

        viewModel.creditCards.observe(viewLifecycleOwner, Observer { photos ->
            rvAdapter.updateData(photos.photos)

        })

        return binding.root
    }

    fun initData(){
        viewModel.fetchCreditCards()

    }


    fun refreshData(){

    }

}