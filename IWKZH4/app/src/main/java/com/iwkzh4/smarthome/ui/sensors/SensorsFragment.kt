package com.iwkzh4.smarthome.ui.sensors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.iwkzh4.smarthome.R
import com.iwkzh4.smarthome.data.adapters.SensorsAdapter
import com.iwkzh4.smarthome.databinding.FragmentSensorsBinding
import kotlinx.android.synthetic.main.fragment_sensors.*

class SensorsFragment : Fragment() {
    private lateinit var sensorsViewModel: SensorsViewModel
    private lateinit var binding: FragmentSensorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorsViewModel = ViewModelProviders.of(this).get(SensorsViewModel::class.java)

        initRecycleView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sensors, container, false
        )
        binding.viewModel = sensorsViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sensorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initRecycleView() {
        sensorsViewModel.sensors.observe(this, Observer { sensors ->
            sensorsRecyclerView.also {
                val adapter = SensorsAdapter(sensors)
                it.adapter = adapter

                adapter.filter.filter(sensorsViewModel.searchText)

                sensorFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText != null) {
                            sensorsViewModel.searchText = newText
                        }

                        adapter.filter.filter(newText)
                        return false
                    }

                })
            }
        })
    }
}
