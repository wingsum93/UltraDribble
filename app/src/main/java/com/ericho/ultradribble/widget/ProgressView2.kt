package com.ericho.ultradribble.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.ericho.ultradribble.R

/**
 *
 */
class ProgressView2 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val mDrawable: CircularProgressDrawable2?

    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.ProgressView, defStyleAttr, 0)
        val color = a.getColor(R.styleable.ProgressView_progressColor, ContextCompat.getColor(context, R.color.purple_400))
        a.recycle()
        mDrawable = CircularProgressDrawable2(color, 20f)
        mDrawable.callback = this
    }

    /**
     * Called when the visibility of the view or an ancestor of the view has changed.
     */
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (mDrawable == null) return
        if (visibility == VISIBLE) mDrawable.start() else mDrawable.stop()
    }

    //when size change
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mDrawable!!.setBounds(0, 0, w, h)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        mDrawable!!.draw(canvas)
    }


    override fun verifyDrawable(who: Drawable): Boolean {
        return who === mDrawable || super.verifyDrawable(who)
    }

    fun start() {
        mDrawable?.start()
    }

}