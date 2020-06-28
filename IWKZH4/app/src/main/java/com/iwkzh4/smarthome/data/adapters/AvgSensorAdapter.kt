package com.iwkzh4.smarthome.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iwkzh4.smarthome.R
import com.iwkzh4.smarthome.databinding.RowSensorBinding
import com.iwkzh4.smarthome.model.AvgSensorData

class AvgSensorAdapter(private val avgSensorData: List<AvgSensorData>) :
    RecyclerView.Adapter<AvgSensorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate<RowSensorBinding>(
                LayoutInflater.from(parent.context),
                R.layout.row_sensor,
                parent,
                false
            )
        )

    override fun getItemCount() = avgSensorData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerViewBinding.sensorData = avgSensorData[position]

    }

    class ViewHolder(val recyclerViewBinding: RowSensorBinding) :
        RecyclerView.ViewHolder(recyclerViewBinding.root)

}