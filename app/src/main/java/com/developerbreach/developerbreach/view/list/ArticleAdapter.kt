package com.developerbreach.developerbreach.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.ItemArticleBinding
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.view.list.ArticleAdapter.ArticleViewHolder

/**
 * This class implements a [RecyclerView] extends to [ListAdapter] which uses Data
 * Binding to present list data, including computing diffs between lists.
 *
 * [Article] type of list this adapter will receive.
 * [ArticleViewHolder] class that extends ViewHolder that will be used by the adapter.
 * This adapter is associated with [ArticleListFragment] class.
 * Views inside recyclerView items are bind in class ArticleListBindingAdapter with layout
 * reference to [R.layout.item_article]
 */
class ArticleAdapter
/**
 * @param viewModel takes associated viewModel.
 * @see Article.DiffCallback is a utility class that calculates the
 * difference between two lists and outputs a list of update operations that converts the first
 * list into the second one.
 */
internal constructor(

    /**
     * This adapter class requires associated [ArticleListFragment] fragment with viewModel
     * [ArticleListViewModel] class to get access for binding objects inside recyclerView items,
     * by creating setters in layout.
     *
     * @see [R.layout.item_article], declares setters and variables.
     */
    private val viewModel: ArticleListViewModel,
    private val fragment: ArticleListFragment
) : ListAdapter<Article, ArticleViewHolder>(Article.DiffCallback) {

    /**
     * ArticleViewHolder class creates child view Article properties.
     * Class also calls setters for binding objects from inflated layout.
     */
    class ArticleViewHolder
    /**
     * @param binding binds each properties in [Article] list
     */(
        private val binding: ItemArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * @param article   pass object to set article for binding. This binding is accessed from
         * layout xml [R.layout.item_article]
         * @param viewModel call setter for viewModel
         */
        fun bind(
            article: Article,
            viewModel: ArticleListViewModel,
            fragment: ArticleListFragment
        ) {
            binding.article = article
            binding.viewModel = viewModel
            binding.fragment = fragment
            // Force DataBinding to execute binding views immediately.
            binding.executePendingBindings()
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. DataBinding should
     * update the contents of the [ArticleViewHolder.itemView] to reflect the item at the given
     * position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article, viewModel, fragment)
    }

    /**
     * Called when RecyclerView needs a new [ArticleViewHolder] of the given type to represent
     * an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // Allow DataBinding to inflate the layout.
        val binding: ItemArticleBinding = ItemArticleBinding.inflate(
            inflater, parent, false
        )
        return ArticleViewHolder(binding)
    }

}