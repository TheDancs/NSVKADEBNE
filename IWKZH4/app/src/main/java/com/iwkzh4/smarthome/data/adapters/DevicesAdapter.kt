package com.iwkzh4.smarthome.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iwkzh4.smarthome.R
import com.iwkzh4.smarthome.databinding.DeviceItemBinding
import com.iwkzh4.smarthome.model.Device
import com.iwkzh4.smarthome.utils.DeviceItemClickListener
import java.util.*

class DevicesAdapter(
    private val devices: List<Device>,
    private val deviceItemClickListener: DeviceItemClickListener
) : RecyclerView.Adapter<DevicesAdapter.ViewHolder>(), Filterable {
    var filteredDevices: List<Device> = devices

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate<DeviceItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.device_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = filteredDevices.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerViewBinding.device = filteredDevices[position]
        holder.recyclerViewBinding.root.setOnClickListener {
            deviceItemClickListener.onClick(holder.itemView, filteredDevices[position])
            notifyItemChanged(position)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    filteredDevices = devices
                } else {
                    filteredDevices = devices.filter {
                        it.friendlyName.toLowerCase(Locale.ROOT)
                            .contains(charSearch.toLowerCase(Locale.ROOT))
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredDevices
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredDevices = results?.values as List<Device>

                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(val recyclerViewBinding: DeviceItemBinding) :
        RecyclerView.ViewHolder(recyclerViewBinding.root)
}