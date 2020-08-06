package com.developerbreach.developerbreach.view.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.FragmentArticleListBinding
import com.developerbreach.developerbreach.utils.RecyclerViewItemDecoration.Companion.setItemSpacing
import com.developerbreach.developerbreach.utils.showSnackBar
import java.util.concurrent.TimeUnit


class ArticleListFragment : Fragment() {

    private lateinit var binding: FragmentArticleListBinding
    private lateinit var viewModel: ArticleListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences(
                getString(R.string.preference_intro_result_key), Context.MODE_PRIVATE)

        with(sharedPref.getString(
                getString(R.string.preference_intro_status_key),
                getString(R.string.preference_intro_fragment_not_shown_value)
        )) {
            if (!this.equals(getString(R.string.preference_intro_fragment_shown_value))) {
                findNavController().navigate(R.id.introFragment)
            }
        }

        viewModel = ViewModelProvider(this).get(ArticleListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentArticleListBinding.inflate(inflater)
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.isInternetAvailable.observe(viewLifecycleOwner, Observer { isInternetAvailable ->
            if (!isInternetAvailable) {
                showSnackBar(getString(R.string.no_internet_connection), requireActivity())
            }
        })

        viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            if (articles.isNotEmpty()) {
                binding.articlesRecyclerView.visibility = View.VISIBLE
                binding.includeBrandLayout.root.visibility = View.GONE
                binding.appbarArticleList.visibility = View.VISIBLE
            } else {
                binding.articlesRecyclerView.visibility = View.GONE
                binding.appbarArticleList.visibility = View.GONE
                binding.includeBrandLayout.root.visibility = View.VISIBLE
            }
        })
    }
}