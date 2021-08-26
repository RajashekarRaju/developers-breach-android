package com.developerbreach.developerbreach.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemRecentArticlesBinding
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.view.home.RecentArticlesAdapter.RecentArticlesViewHolder

class RecentArticlesAdapter internal constructor(
    private val viewModel: HomeViewModel,
    private val fragment: HomeFragment
) : ListAdapter<Article, RecentArticlesViewHolder>(Article.DiffCallback) {

    class RecentArticlesViewHolder (
        private val binding: ItemRecentArticlesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            article: Article,
            viewModel: HomeViewModel,
            fragment: HomeFragment
        ) {
            binding.article = article
            binding.viewModel = viewModel
            binding.fragment = fragment
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: RecentArticlesViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article, viewModel, fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Allow DataBinding to inflate the layout.
        val binding: ItemRecentArticlesBinding = ItemRecentArticlesBinding.inflate(
            inflater, parent, false
        )
        return RecentArticlesViewHolder(binding)
    }

}