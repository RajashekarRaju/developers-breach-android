package com.developerbreach.developerbreach.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.developerbreach.developerbreach.databinding.FragmentArticleListBinding
import com.developerbreach.developerbreach.utils.RecyclerViewItemDecoration.Companion.setItemSpacing
import java.util.concurrent.TimeUnit


class ArticleListFragment : Fragment() {

    private lateinit var binding: FragmentArticleListBinding
    private lateinit var viewModel: ArticleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        setItemSpacing(resources, binding.articlesRecyclerView)
        // Time taken for fragment to enter with transition
        postponeEnterTransition(100L, TimeUnit.MILLISECONDS)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.articles.observe(viewLifecycleOwner, { articles ->
            if (articles.isNotEmpty()) {
                binding.articlesRecyclerView.visibility = View.VISIBLE
            } else {
                binding.articlesRecyclerView.visibility = View.GONE
            }
        })
    }
}