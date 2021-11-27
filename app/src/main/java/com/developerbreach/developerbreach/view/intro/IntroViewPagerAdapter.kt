package com.developerbreach.developerbreach.view.intro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.developerbreach.developerbreach.databinding.ItemIntroViewPagerBinding
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.view.intro.IntroViewPagerAdapter.*

class IntroViewPagerAdapter(
    private val introViewPager: ViewPager2
) : ListAdapter<Intro, IntroViewHolder>(Intro.DiffCallback) {

    class IntroViewHolder(
        val binding: ItemIntroViewPagerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            intro: Intro,
            introViewPager: ViewPager2
        ) {
            binding.intro = intro
            binding.introViewPager = introViewPager
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(
            ItemIntroViewPagerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        val intro = getItem(position)
        holder.bind(intro, introViewPager)
    }
}