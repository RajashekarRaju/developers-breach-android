package com.developerbreach.developerbreach.utils

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.developerbreach.developerbreach.R

/**
 * Item decoration for cardViews for equal spacing inside recyclerView.
 */
class RecyclerViewItemDecoration private constructor(
// Variable of type int which gives equal spacing for all sides.
    private val itemOffset: Int
) : ItemDecoration() {

    /**
     * @param outRect which has 4 edges for view to set equal spacing. Pass the same variable for
     * all 4 sides of the outRect for the result.
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect[itemOffset, itemOffset, itemOffset] = itemOffset
    }

    companion object {

        fun setItemSpacing(
            resources: Resources,
            recyclerView: RecyclerView
        ) {
            val spacingInPixels =
                resources.getDimensionPixelSize(R.dimen.recycler_view_spacing_dimen)
            recyclerView.addItemDecoration(RecyclerViewItemDecoration(spacingInPixels))
        }
    }

}