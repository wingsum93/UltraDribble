package com.ericho.ultradribble.ui.main.shots

import android.view.View

/**
 */

interface OnRecyclerViewItemClickListener {

    fun onItemClick(view: View, position: Int)

    fun onAvatarClick(view: View, position: Int)

}