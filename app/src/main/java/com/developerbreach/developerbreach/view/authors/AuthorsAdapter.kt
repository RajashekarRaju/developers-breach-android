package com.developerbreach.developerbreach.view.authors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemAuthorBinding
import com.developerbreach.developerbreach.model.Authors
import com.developerbreach.developerbreach.view.authors.AuthorsAdapter.AuthorsViewHolder

class AuthorsAdapter: ListAdapter<Authors, AuthorsViewHolder>(Authors.DiffCallback) {

    class AuthorsViewHolder(
        private val binding: ItemAuthorBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            authors: Authors
        ) {
            binding.author = authors
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: AuthorsViewHolder, position: Int) {
        val author = getItem(position)
        holder.bind(author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Allow DataBinding to inflate the layout.
        val binding: ItemAuthorBinding = ItemAuthorBinding.inflate(
            inflater, parent, false
        )
        return AuthorsViewHolder(binding)
    }
}