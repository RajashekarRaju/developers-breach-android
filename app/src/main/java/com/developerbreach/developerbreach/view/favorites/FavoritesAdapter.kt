package com.developerbreach.developerbreach.view.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemFavoritesBinding
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.view.favorites.FavoritesAdapter.FavoriteViewHolder


class FavoritesAdapter(
    private val viewModel: FavoritesViewModel
) : ListAdapter<Article, FavoriteViewHolder>(Article.DiffCallback) {

    class FavoriteViewHolder(
        // Get access to binding the views in layout
        private val binding: ItemFavoritesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            article: Article,
            viewModel: FavoritesViewModel
        ) {
            binding.article = article
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorites = getItem(position)
        holder.bind(favorites, viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        // Allow DataBinding to inflate the layout.
        val binding: ItemFavoritesBinding = ItemFavoritesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoriteViewHolder(binding)
    }
}