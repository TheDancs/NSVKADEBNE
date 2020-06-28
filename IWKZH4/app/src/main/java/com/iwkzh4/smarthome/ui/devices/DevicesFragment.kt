package com.iwkzh4.smarthome.ui.devices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.iwkzh4.smarthome.R
import com.iwkzh4.smarthome.data.adapters.DevicesAdapter
import com.iwkzh4.smarthome.databinding.FragmentDevicesBinding
import kotlinx.android.synthetic.main.fragment_devices.*

class DevicesFragment : Fragment() {
    private lateinit var devicesViewModel: DevicesViewModel
    private lateinit var binding: FragmentDevicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        devicesViewModel = ViewModelProviders.of(this).get(DevicesViewModel::class.java)
        initRecycleView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_devices, container, false
        )
        binding.viewModel = devicesViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        devicesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun initRecycleView() {
        devicesViewModel.devices.observe(this, Observer { devices ->
            devicesRecyclerView.also {
                val adapter = DevicesAdapter(
                    devices,
                    devicesViewModel
                )
                it.adapter = adapter

                adapter.filter.filter(devicesViewModel.searchText)

                devicesFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText != null) {
                            devicesViewModel.searchText = newText
                        }
                        adapter.filter.filter(devicesViewModel.searchText)
                        return false
                    }

                })
            }
        })
    }
}
