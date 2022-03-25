package com.developerbreach.developerbreach.ui.authors
//
//import android.widget.ImageView
//import androidx.databinding.BindingAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.developerbreach.developerbreach.model.Authors
//
//
//@BindingAdapter("bindAuthorsListData")
//fun RecyclerView.setAuthorsListData(
//    list: List<Authors>?
//) {
//    val adapter = AuthorsAdapter()
//    adapter.submitList(list)
//    this.adapter = adapter
//}
//
//
//@BindingAdapter("bindAuthorProfileImage")
//fun ImageView.setAuthorProfileImage(
//    authorProfileUrl: String
//) {
//    Glide.with(this.context)
//        .load(authorProfileUrl)
//        .circleCrop()
//        .into(this)
//}