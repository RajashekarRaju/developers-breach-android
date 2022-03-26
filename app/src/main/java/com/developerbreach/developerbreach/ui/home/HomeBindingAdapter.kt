package com.developerbreach.developerbreach.ui.home

//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.navigation.findNavController
//import androidx.recyclerview.widget.RecyclerView
//import androidx.transition.Fade
//import androidx.transition.TransitionManager
//import com.developerbreach.developerbreach.R
//import com.developerbreach.developerbreach.controller.AppNavDirections
//import com.developerbreach.developerbreach.model.Article
//import com.developerbreach.developerbreach.utils.itemViewAnimation
//import com.google.android.material.card.MaterialCardView


//@BindingAdapter("bindRecentArticlesListData")
//fun RecyclerView.setRecentArticleFragmentListData(
//    list: List<Article>?,
//) {
//    val adapter = RecentArticlesAdapter()
//    adapter.submitList(list)
//    this.adapter = adapter
//    itemViewAnimation(context, this, 750L, R.anim.fade_enter_anim)
//}
//
//
//@BindingAdapter("bindRecentArticleItemBanner")
//fun ImageView.setRecentArticleItemBanner(
//    imageUrl: String
//) {
//    val requestOptions = RequestOptions().transform(
//        CenterCrop(),
//        RoundedCorners(20)
//    )
//    Glide.with(this.context)
//        .load(imageUrl)
//        .apply(requestOptions)
//        .into(this)
//}
//
//
//@BindingAdapter("bindRecentArticleToDetailListener", "bindRecentArticleItemTitleTransition")
//fun MaterialCardView.setRecentArticleToDetailClickListener(
//    articleId: Int,
//    title: TextView
//) {
//    this.setOnClickListener {
//
//        TransitionManager.beginDelayedTransition(this, Fade())
//        title.visibility = View.GONE
//
//        AppNavDirections(findNavController()).fragmentsToDetailFragment(articleId, this)
//    }
//}