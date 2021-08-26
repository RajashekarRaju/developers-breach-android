package com.developerbreach.developerbreach.view.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemFavoritesBinding
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.view.favorites.FavoritesAdapter.FavoriteViewHolder


class FavoritesAdapter internal constructor(
    private val viewModel: FavoritesViewModel,
    private val fragment: FavoritesFragment
) : ListAdapter<Article, FavoriteViewHolder>(Article.DiffCallback) {

    class FavoriteViewHolder(
        // Get access to binding the views in layout
        private val binding: ItemFavoritesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            article: Article,
            viewModel: FavoritesViewModel,
            fragment: FavoritesFragment
        ) {
            binding.article = article
            binding.favoriteViewModel = viewModel
            binding.fragment = fragment
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoritesEntity = getItem(position)
        holder.bind(favoritesEntity, viewModel, fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Allow DataBinding to inflate the layout.
        val binding: ItemFavoritesBinding = ItemFavoritesBinding.inflate(
            inflater, parent, false
        )
        return FavoriteViewHolder(binding)
    }

}