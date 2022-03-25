package com.developerbreach.developerbreach.ui.search

//import android.text.Editable
//import android.text.TextWatcher
//import android.view.View
//import android.view.animation.Animation
//import android.widget.FrameLayout
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.widget.AppCompatEditText
//import androidx.databinding.BindingAdapter
//import androidx.navigation.NavController
//import androidx.navigation.findNavController
//import androidx.recyclerview.widget.RecyclerView
//import androidx.transition.Fade
//import androidx.transition.TransitionManager
//import com.developerbreach.developerbreach.R
//import com.developerbreach.developerbreach.controller.AppNavDirections
//import com.developerbreach.developerbreach.model.Search
//import com.developerbreach.developerbreach.utils.itemViewAnimation
//import java.util.*
//
//
//@BindingAdapter("bindSearchRecyclerViewData")
//fun RecyclerView.setSearchRecyclerViewData(
//    list: List<Search>?
//) {
//    val adapter = SearchAdapter()
//    adapter.submitList(list)
//    this.adapter = adapter
//}
//
//
//@BindingAdapter("bindSearchToDetailListener", "bindSearchItemViewGroup")
//fun TextView.setSearchToDetailListener(
//    articleId: Int,
//    frameLayout: FrameLayout
//) {
//    val textView = this
//    textView.setOnClickListener {
//
//        itemViewAnimation(
//            context = textView.context,
//            view = textView,
//            duration = 250L,
//            animationProperty = R.anim.fade_enter_anim
//        ).setAnimationListener(object : Animation.AnimationListener {
//
//            override fun onAnimationEnd(p0: Animation?) {
//                navigateOnAnimationCompleted(articleId, frameLayout, textView, findNavController())
//            }
//
//            override fun onAnimationRepeat(p0: Animation?) {}
//            override fun onAnimationStart(p0: Animation?) {}
//        })
//    }
//}
//
//private fun navigateOnAnimationCompleted(
//    articleId: Int,
//    frameLayout: FrameLayout,
//    textView: TextView,
//    navController: NavController
//) {
//    TransitionManager.beginDelayedTransition(frameLayout, Fade())
//    AppNavDirections(navController).searchToDetail(articleId, textView)
//}
//
//
//@BindingAdapter("bindClearSearchQueryData", "bindSearchViewModelHeader")
//fun ImageView.setClearSearchQueryData(
//    editText: AppCompatEditText,
//    viewModel: SearchViewModel,
//) {
//    val clearImageView = this
//    editText.addTextChangedListener(object : TextWatcher {
//
//        override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            if (query?.isNotEmpty() == true) {
//                val userQuery = query.toString().lowercase(Locale.getDefault())
//                viewModel.getSearchableArticlesWithQuery(userQuery)
//                clearImageView.visibility = View.VISIBLE
//            } else {
//                clearImageView.visibility = View.INVISIBLE
//            }
//        }
//
//        override fun afterTextChanged(p0: Editable?) {}
//        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//    })
//
//    this.setOnClickListener {
//        editText.setText("")
//    }
//}
//
//
//@BindingAdapter("bindSearchEmptyStateImage", "bindSearchRecyclerViewState")
//fun ImageView.setSearchEmptyStateImage(
//    listSize: Int?,
//    recyclerView: RecyclerView
//) {
//    if (listSize == 0) {
//        this.visibility = View.VISIBLE
//        recyclerView.visibility = View.INVISIBLE
//    } else {
//        this.visibility = View.GONE
//        recyclerView.visibility = View.VISIBLE
//    }
//}
