package com.developerbreach.developerbreach.view.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.developerbreach.developerbreach.databinding.FragmentArticleDetailBinding
import com.google.android.material.transition.MaterialContainerTransform


class ArticleDetailFragment : Fragment() {

    private lateinit var viewModel: ArticleDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val article = ArticleDetailFragmentArgs.fromBundle(requireArguments()).articleDetailArgs
        val factory = ArticleDetailViewModelFactory(requireActivity().application, article)
        viewModel = ViewModelProvider(this, factory).get(ArticleDetailViewModel::class.java)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            this.duration = 300L
            this.containerColor = Color.BLACK
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentArticleDetailBinding = FragmentArticleDetailBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        return binding.root
    }
}