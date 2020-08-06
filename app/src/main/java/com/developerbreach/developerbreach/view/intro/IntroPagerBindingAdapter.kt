package com.developerbreach.developerbreach.view.intro

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.utils.convertToDp


@BindingAdapter("bindNextIntroPagerListener", "bindSkipIntroPagerListener",
        "bindHideIntroNextTextView")
fun TextView.setNextItemClickListener(
        viewPager: ViewPager2,
        skipIntroTextView: TextView,
        intro: Intro
) {
    val nextIntroTextView = this

    nextIntroTextView.setOnClickListener {
        when (nextIntroTextView.text) {
            "Next" -> {
                val currentItem = viewPager.currentItem
                viewPager.setCurrentItem(currentItem + 1, true)
            }
            "Finish" -> {
                navigateToArticleListFragment(nextIntroTextView)
            }
        }
    }

    when (intro.id) {
        1 -> {
            skipIntroTextView.text = context.getText(R.string.skip_intro_pager_text)
            skipIntroTextView.visibility = View.VISIBLE
        }
        2, 3 -> {
            skipIntroTextView.visibility = View.GONE
        }
        4 -> {
            this.text = nextIntroTextView.context.getText(R.string.end_intro_pager_text)
            skipIntroTextView.visibility = View.GONE
        }
    }

    skipIntroTextView.setOnClickListener {
        navigateToArticleListFragment(nextIntroTextView)
    }

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)

            when (state) {
                ViewPager2.SCROLL_STATE_DRAGGING -> {
                    skipIntroTextView.visibility = View.INVISIBLE
                    nextIntroTextView.visibility = View.INVISIBLE
                }
                ViewPager2.SCROLL_STATE_IDLE -> {
                    skipIntroTextView.visibility = View.VISIBLE
                    nextIntroTextView.visibility = View.VISIBLE
                }
                ViewPager2.SCROLL_STATE_SETTLING -> {
                    skipIntroTextView.visibility = View.VISIBLE
                    nextIntroTextView.visibility = View.VISIBLE
                }
            }
        }
    })
}


@BindingAdapter("bindPagerFirstPositionViewer", "bindPagerSecondPositionViewer",
        "bindPagerThirdPositionViewer", "bindPagerFourthPositionViewer")
fun View.setPagerFirstPositionViewer(
        currentId: Int,
        secondViewer: View,
        thirdViewer: View,
        fourthViewer: View
) {
    when (currentId) {
        1 -> {
            this.setBackgroundResource(R.drawable.current_pager_background)
            this.layoutParams.height = convertToDp(12.toFloat())
            this.layoutParams.width = convertToDp(12.toFloat())
        }
        2 -> {
            secondViewer.setBackgroundResource(R.drawable.current_pager_background)
            secondViewer.layoutParams.height = convertToDp(12.toFloat())
            secondViewer.layoutParams.width = convertToDp(12.toFloat())
        }
        3 -> {
            thirdViewer.setBackgroundResource(R.drawable.current_pager_background)
            thirdViewer.layoutParams.height = convertToDp(12.toFloat())
            thirdViewer.layoutParams.width = convertToDp(12.toFloat())
        }
        4 -> {
            fourthViewer.setBackgroundResource(R.drawable.current_pager_background)
            fourthViewer.layoutParams.height = convertToDp(12.toFloat())
            fourthViewer.layoutParams.width = convertToDp(12.toFloat())
        }
    }
}


private fun TextView.navigateToArticleListFragment(nextIntroTextView: TextView) {
    val context: Context = nextIntroTextView.context

    with(context.getSharedPreferences(
            context.getString(R.string.preference_intro_result_key),
            Context.MODE_PRIVATE).edit()
    ) {
        putString(
                context.getString(R.string.preference_intro_status_key),
                context.getString(R.string.preference_intro_fragment_shown_value)
        )
        commit()
    }

    findNavController().navigate(R.id.introToArticleListFragment)
}