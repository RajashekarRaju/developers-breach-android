package com.developerbreach.developerbreach.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemSearchBinding
import com.developerbreach.developerbreach.model.Search
import com.developerbreach.developerbreach.view.search.SearchAdapter.SearchViewHolder

class SearchAdapter : ListAdapter<Search, SearchViewHolder>(Search.DiffCallback) {

    class SearchViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            search: Search
        ) {
            binding.search = search
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Allow DataBinding to inflate the layout.
        val binding: ItemSearchBinding = ItemSearchBinding.inflate(
            inflater, parent, false
        )
        return SearchViewHolder(binding)
    }
}