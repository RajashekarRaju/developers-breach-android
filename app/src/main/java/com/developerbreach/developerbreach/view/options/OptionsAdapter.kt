package com.developerbreach.developerbreach.view.options

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemOptionsBinding
import com.developerbreach.developerbreach.model.Options
import com.developerbreach.developerbreach.view.options.OptionsAdapter.*

class OptionsAdapter(
    private val fragment: OptionsFragment,
) : ListAdapter<Options, OptionsViewHolder>(OptionsDiffCallback) {

    class OptionsViewHolder(private val binding: ItemOptionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            options: Options,
            fragment: OptionsFragment,
        ) {
            binding.options = options
            binding.fragment = fragment
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        return OptionsViewHolder(
            ItemOptionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        val options: Options = getItem(position)
        holder.bind(options, fragment)
    }

    companion object OptionsDiffCallback : DiffUtil.ItemCallback<Options>() {
        override fun areItemsTheSame(oldItem: Options, newItem: Options): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Options, newItem: Options): Boolean {
            return oldItem.optionsId == newItem.optionsId
        }
    }
}