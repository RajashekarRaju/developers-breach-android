package com.developerbreach.developerbreach.ui.home

//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.developerbreach.developerbreach.model.Article
//import com.developerbreach.developerbreach.ui.home.RecentArticlesAdapter.RecentArticlesViewHolder
//
//class RecentArticlesAdapter : ListAdapter<Article, RecentArticlesViewHolder>(Article.DiffCallback) {
//
//    class RecentArticlesViewHolder(
//        private val binding: ItemRecentArticlesBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(
//            article: Article
//        ) {
//            binding.article = article
//            binding.executePendingBindings()
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecentArticlesViewHolder, position: Int) {
//        val article = getItem(position)
//        holder.bind(article)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentArticlesViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        // Allow DataBinding to inflate the layout.
//        val binding: ItemRecentArticlesBinding = ItemRecentArticlesBinding.inflate(
//            inflater, parent, false
//        )
//        return RecentArticlesViewHolder(binding)
//    }
//}