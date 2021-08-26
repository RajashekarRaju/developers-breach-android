package com.developerbreach.developerbreach.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemSearchBinding
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.view.search.SearchAdapter.SearchViewHolder

class SearchAdapter : ListAdapter<Article, SearchViewHolder>(Article.DiffCallback) {

    /**
     * SearchViewHolder class creates child view Article properties.
     * Class also calls setters for binding objects from inflated layout.
     */
    class SearchViewHolder
    /**
     * @param binding binds each properties in [Article] list
     */(
        // Get access to binding the views in layout
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            // Force DataBinding to execute binding views immediately.
            binding.executePendingBindings()
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. DataBinding should
     * update the contents of the [SearchAdapter.SearchViewHolder.itemView] to reflect the item at
     * the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    /**
     * Called when RecyclerView needs a new [SearchViewHolder] of the given type to represent
     * an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Allow DataBinding to inflate the layout.
        val binding: ItemSearchBinding = ItemSearchBinding.inflate(
            inflater, parent, false
        )
        return SearchViewHolder(binding)
    }
}