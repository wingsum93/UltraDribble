package com.ericho.ultradribble.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.LinearInterpolator


/**
 * Created by steve_000 on 18/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.widget
 */
open abstract class BaseRecyclerAdapter<T,VH :RecyclerView.ViewHolder> constructor(
        val context:Context,
        val items:List<T>,
        private val mDuration:Long = 300L) :RecyclerView.Adapter<VH>() {


    private var mLastPosition:Int = -1
    private val mInterpolator = LinearInterpolator()

    override fun onBindViewHolder(holder: VH?, position: Int) {
        onBindContentViewHolder(holder, position)
        holder?.let {
            setAnimation(it.itemView,position)
        }
    }

    override fun getItemCount() = items.size

    abstract fun onBindContentViewHolder(holder: VH?,position: Int)

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > mLastPosition) {
            val anim = ObjectAnimator.ofFloat(viewToAnimate, "translationY", viewToAnimate.getMeasuredHeight().toFloat(), 0f)
            anim.setInterpolator(mInterpolator)
            anim.setDuration(mDuration).start()
            mLastPosition = position
        } else {
            viewToAnimate.setTranslationY( 0f)
        }
    }

}