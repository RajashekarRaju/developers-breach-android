package com.developerbreach.developerbreach.view.intro

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.utils.convertToDp
import com.developerbreach.developerbreach.view.controller.AppNavDirections
import timber.log.Timber


@BindingAdapter("bindIntroListData")
fun ViewPager2.setIntroListData(
    list: List<Intro>
) {
    val viewPager = IntroViewPagerAdapter(this)
    viewPager.submitList(list)
    this.adapter = viewPager
}


@BindingAdapter("bindFinishIntroVisibility")
fun ImageView.setFinishIntroVisibility(
    introId: Int
) {
    if (introId == 4) {
        this.visibility = View.VISIBLE
        Timber.e("Visibility yes")
    } else {
        this.visibility = View.GONE
        Timber.e("Visibility No")
    }

    this.setOnClickListener {
        navigateToArticleListFragment()
    }
}


@BindingAdapter(
    "bindNextIntroPagerListener", "bindIntroData",
    "bindIntroBannerImageView", "bindIntroSubtitleTextView"
)
fun ImageView.setNextItemClickListener(
    viewPager: ViewPager2,
    intro: Intro,
    bannerImageView: ImageView,
    subtitle: TextView
) {
    val nextPageImageView = this

    if (intro.id == 4) {
        nextPageImageView.visibility = View.INVISIBLE
    } else {
        nextPageImageView.visibility = View.VISIBLE
    }

    nextPageImageView.setOnClickListener {
        val currentItem = viewPager.currentItem
        viewPager.setCurrentItem(currentItem + 1, true)
    }

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)

            when (state) {
                ViewPager2.SCROLL_STATE_DRAGGING -> hideViewsOnScroll()
                ViewPager2.SCROLL_STATE_IDLE -> showViewsOnScrollComplete()
                ViewPager2.SCROLL_STATE_SETTLING -> showViewsOnScrollComplete()
            }
        }

        private fun hideViewsOnScroll() {
            nextPageImageView.visibility = View.INVISIBLE
            bannerImageView.visibility = View.INVISIBLE
            subtitle.visibility = View.INVISIBLE
        }

        private fun showViewsOnScrollComplete() {
            nextPageImageView.visibility = View.VISIBLE
            bannerImageView.visibility = View.VISIBLE
            subtitle.visibility = View.VISIBLE
        }
    })
}


@BindingAdapter(
    "bindPagerFirstPositionViewer", "bindPagerSecondPositionViewer",
    "bindPagerThirdPositionViewer", "bindPagerFourthPositionViewer"
)
fun View.setPagerFirstPositionViewer(
    currentId: Int,
    secondViewer: View,
    thirdViewer: View,
    fourthViewer: View
) {
    when (currentId) {
        1 -> setCurrentViewPosition(this)
        2 -> setCurrentViewPosition(secondViewer)
        3 -> setCurrentViewPosition(thirdViewer)
        4 -> setCurrentViewPosition(fourthViewer)
    }
}

private fun setCurrentViewPosition(currentView: View) {
    currentView.setBackgroundResource(R.drawable.current_pager_background)
    currentView.layoutParams.height = currentView.convertToDp(8.toFloat())
    currentView.layoutParams.width = currentView.convertToDp(8.toFloat())
}

private fun ImageView.navigateToArticleListFragment() {

    with(
        context.getSharedPreferences(
            context.getString(R.string.preference_intro_result_key),
            Context.MODE_PRIVATE
        ).edit()
    ) {
        putString(
            context.getString(R.string.preference_intro_status_key),
            context.getString(R.string.preference_intro_fragment_shown_value)
        )
        commit()
    }

    AppNavDirections(findNavController()).introToHome()
}