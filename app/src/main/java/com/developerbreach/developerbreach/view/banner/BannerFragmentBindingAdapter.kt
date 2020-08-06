package com.developerbreach.developerbreach.view.banner

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView


@BindingAdapter("bindBannerWithZoom", "bindCloseBannerFragment")
fun PhotoView.setBannerWithZoom(
        bannerUrlString: String,
        closeFragmentImageView: ImageView
) {
    Glide.with(this.context).load(bannerUrlString).into(this)

    closeFragmentImageView.setOnClickListener {
        findNavController().navigateUp()
    }
}