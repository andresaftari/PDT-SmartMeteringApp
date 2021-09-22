package org.smartmetering.client.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.smartmetering.client.data.api.UnitResponseItem
import org.smartmetering.client.databinding.ItemUnitBinding

@SuppressLint("SetTextI18n")
class MainAdapter(private val items: ArrayList<UnitResponseItem>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        ItemUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) = with(holder) {
        bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    class MainViewHolder(private val binding: ItemUnitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnitResponseItem, position: Int) = with(binding) {
            tvAmpere.text = item.ampere
            tvVolt.text = item.volt
            tvWatt.text = item.watt

            tvPlace.text = "Device ${position + 1}"
        }
    }
}