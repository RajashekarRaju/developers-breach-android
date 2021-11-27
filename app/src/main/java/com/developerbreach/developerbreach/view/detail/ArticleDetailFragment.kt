package com.developerbreach.developerbreach.view.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developerbreach.developerbreach.databinding.FragmentArticleDetailBinding
import com.google.android.material.transition.MaterialContainerTransform


class ArticleDetailFragment : Fragment() {

    private lateinit var viewModel: ArticleDetailViewModel
    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = requireActivity().application
        val factory = ArticleDetailViewModelFactory(application, args.articleDetailArgs)
        viewModel = ViewModelProvider(this, factory)[ArticleDetailViewModel::class.java]

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            this.duration = 300L
            this.containerColor = Color.BLACK
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.navController = findNavController()
        binding.executePendingBindings()
        return binding.root
    }
}
