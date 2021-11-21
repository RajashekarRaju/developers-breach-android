package com.developerbreach.developerbreach.view.intro

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.controller.AppNavDirections
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.utils.PrefUtils
import com.developerbreach.developerbreach.utils.convertToDp
import com.developerbreach.developerbreach.utils.startCircularEffect


@BindingAdapter("bindIntroListData")
fun ViewPager2.setIntroListData(
    list: List<Intro>
) {
    val viewPager = IntroViewPagerAdapter(this)
    viewPager.submitList(list)
    this.adapter = viewPager
}


@BindingAdapter("bindFinishIntroVisibility", "bindIntroItemLayoutParent")
fun ImageView.setFinishIntroVisibility(
    introId: Int,
    parent: ConstraintLayout
) {
    when (introId) {
        4 -> this.visibility = View.VISIBLE
        else -> this.visibility = View.GONE
    }

    this.setOnClickListener {
        navigateToArticleListFragment(parent)
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

    when (intro.id) {
        4 -> nextPageImageView.visibility = View.INVISIBLE
        else -> nextPageImageView.visibility = View.VISIBLE
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

private fun ImageView.navigateToArticleListFragment(
    parent: ConstraintLayout
) {
    /**
     * If PrefUtils(this).preferenceStateSaved() is not called from onPause() in IntroFragment,
     * then make sure to call it here so that user won't see [IntroFragment] again after
     * initial state.
     *
     * PrefUtils(this).preferenceStateSaved()
     */

    PrefUtils(context).preferenceStateSaved()
    startCircularEffect(parent, parent.right, parent.top)
    AppNavDirections(findNavController()).introToHome()
}