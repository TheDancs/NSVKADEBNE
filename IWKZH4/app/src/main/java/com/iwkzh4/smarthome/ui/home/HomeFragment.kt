package com.iwkzh4.smarthome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iwkzh4.smarthome.R
import com.iwkzh4.smarthome.data.adapters.AvgSensorAdapter
import com.iwkzh4.smarthome.data.adapters.DevicesAdapter
import com.iwkzh4.smarthome.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        initRecycleViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        avgSensorData.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recentDevices.layoutManager = GridLayoutManager(this.context, 3)
    }

    private fun initRecycleViews() {
        homeViewModel.recentDevices.observe(this, Observer { devices ->
            recentDevices.also {
                it.adapter = DevicesAdapter(
                    devices,
                    homeViewModel
                )
            }
        })

        homeViewModel.latestSensorData.observe(this, Observer { latestSensorData ->
            avgSensorData.also {
                it.adapter = AvgSensorAdapter(
                    latestSensorData
                )
            }
        })
    }
}
