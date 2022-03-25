package com.developerbreach.developerbreach.ui.options
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.developerbreach.developerbreach.databinding.ItemOptionsBinding
//import com.developerbreach.developerbreach.model.Options
//import com.developerbreach.developerbreach.ui.options.OptionsAdapter.*
//
//class OptionsAdapter(
//    private val fragment: OptionsFragment
//) : ListAdapter<Options, OptionsViewHolder>(Options.DiffCallback) {
//
//    class OptionsViewHolder(
//        private val binding: ItemOptionsBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(
//            options: Options,
//            fragment: OptionsFragment
//        ) {
//            binding.options = options
//            binding.fragment = fragment
//            binding.executePendingBindings()
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
//        return OptionsViewHolder(
//            ItemOptionsBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
//        val options: Options = getItem(position)
//        holder.bind(options, fragment)
//    }
//}