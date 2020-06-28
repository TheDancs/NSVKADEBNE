package com.iwkzh4.smarthome.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iwkzh4.smarthome.R
import com.iwkzh4.smarthome.databinding.SensorItemBinding
import com.iwkzh4.smarthome.model.Sensor
import java.util.*

class SensorsAdapter(private val sensors: List<Sensor>) :
    RecyclerView.Adapter<SensorsAdapter.ViewHolder>(),
    Filterable {
    var filteredSensors: List<Sensor> = sensors

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate<SensorItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.sensor_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = filteredSensors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerViewBinding.sensor = filteredSensors[position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    filteredSensors = sensors
                } else {
                    filteredSensors = sensors.filter {
                        it.friendlyName.toLowerCase(Locale.ROOT)
                            .contains(charSearch.toLowerCase(Locale.ROOT))
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredSensors
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredSensors = results?.values as List<Sensor>

                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(val recyclerViewBinding: SensorItemBinding) :
        RecyclerView.ViewHolder(recyclerViewBinding.root)
}