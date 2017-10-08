package com.ericho.ultradribble.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.util.Property
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator

class CircularProgressDrawable2(@ColorInt color: Int, private val mBorderWidth: Float) : Drawable(), Animatable {
    private val fBounds = RectF()

    private var mObjectAnimatorSweep: ObjectAnimator? = null
    private var mObjectAnimatorAngle: ObjectAnimator? = null
    private var mModeAppearing: Boolean = false
    private val mPaint: Paint
    private var mCurrentGlobalAngleOffset: Float = 0.toFloat()
    var currentGlobalAngle: Float = 0.toFloat()
        set(currentGlobalAngle) {
            field = currentGlobalAngle
            invalidateSelf()
        }
    var currentSweepAngle: Float = 0.toFloat()
        set(currentSweepAngle) {
            field = currentSweepAngle
            invalidateSelf()
        }
    private var mRunning: Boolean = false
    private val mAngleProperty = object : Property<CircularProgressDrawable2, Float>(Float::class.java, "angle") {
        override fun get(`object`: CircularProgressDrawable2): Float {
            return `object`.currentGlobalAngle
        }

        override fun set(`object`: CircularProgressDrawable2, value: Float?) {
            `object`.currentGlobalAngle = value!!
        }
    }
    private val mSweepProperty = object : Property<CircularProgressDrawable2, Float>(Float::class.java, "arc") {
        override fun get(`object`: CircularProgressDrawable2): Float {
            return `object`.currentSweepAngle
        }

        override fun set(`object`: CircularProgressDrawable2, value: Float?) {
            `object`.currentSweepAngle = value!!
        }
    }

    init {

        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mBorderWidth
        mPaint.color = color

        setupAnimations()
    }

    override fun draw(canvas: Canvas) {
        var startAngle = currentGlobalAngle - mCurrentGlobalAngleOffset
        var sweepAngle = currentSweepAngle
        if (!mModeAppearing) {
            startAngle = startAngle + sweepAngle
            sweepAngle = 360f - sweepAngle - MIN_SWEEP_ANGLE.toFloat()
        } else {
            sweepAngle += MIN_SWEEP_ANGLE.toFloat()
        }
        canvas.drawArc(fBounds, startAngle, sweepAngle, false, mPaint)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        mPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    //////////////////////////////////////////////////////////////////////////////
    ////////////////            Animation

    private fun toggleAppearingMode() {
        mModeAppearing = !mModeAppearing
        if (mModeAppearing) {
            mCurrentGlobalAngleOffset = (mCurrentGlobalAngleOffset + MIN_SWEEP_ANGLE * 2) % 360
        }
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        fBounds.left = bounds.left.toFloat() + mBorderWidth / 2f + .5f
        fBounds.right = bounds.right.toFloat() - mBorderWidth / 2f - .5f
        fBounds.top = bounds.top.toFloat() + mBorderWidth / 2f + .5f
        fBounds.bottom = bounds.bottom.toFloat() - mBorderWidth / 2f - .5f
    }

    private fun setupAnimations() {
        mObjectAnimatorAngle = ObjectAnimator.ofFloat(this, mAngleProperty, 360f)
        mObjectAnimatorAngle!!.interpolator = ANGLE_INTERPOLATOR
        mObjectAnimatorAngle!!.duration = ANGLE_ANIMATOR_DURATION.toLong()
        mObjectAnimatorAngle!!.repeatMode = ValueAnimator.RESTART
        mObjectAnimatorAngle!!.repeatCount = ValueAnimator.INFINITE

        mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, mSweepProperty, 360f - MIN_SWEEP_ANGLE * 2)
        mObjectAnimatorSweep!!.interpolator = SWEEP_INTERPOLATOR
        mObjectAnimatorSweep!!.duration = SWEEP_ANIMATOR_DURATION.toLong()
        mObjectAnimatorSweep!!.repeatMode = ValueAnimator.RESTART
        mObjectAnimatorSweep!!.repeatCount = ValueAnimator.INFINITE
        mObjectAnimatorSweep!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationRepeat(animation: Animator) {
                toggleAppearingMode()
            }
        })
    }

    override fun start() {
        if (isRunning) {
            return
        }
        mRunning = true
        mObjectAnimatorAngle!!.start()
        mObjectAnimatorSweep!!.start()
        invalidateSelf()
    }

    override fun stop() {
        if (!isRunning) {
            return
        }
        mRunning = false
        mObjectAnimatorAngle!!.cancel()
        mObjectAnimatorSweep!!.cancel()
        invalidateSelf()
    }

    override fun isRunning(): Boolean {
        return mRunning
    }

    companion object {

        private val ANGLE_INTERPOLATOR = LinearInterpolator()
        private val SWEEP_INTERPOLATOR = DecelerateInterpolator()
        private val ANGLE_ANIMATOR_DURATION = 2000
        private val SWEEP_ANIMATOR_DURATION = 600
        private val MIN_SWEEP_ANGLE = 30
    }

}