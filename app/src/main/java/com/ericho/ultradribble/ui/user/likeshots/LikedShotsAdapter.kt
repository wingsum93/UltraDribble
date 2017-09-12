package com.ericho.ultradribble.ui.user.likeshots

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericho.ultradribble.R
import com.ericho.ultradribble.data.LikedShot
import com.ericho.ultradribble.extension.loadNormal
import kotlinx.android.synthetic.main.item_simple_list_shots.view.*

/**
 */

class LikedShotsAdapter(context: Context, list: List<LikedShot>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext = context
    private val mList = list
    private var mListener: ((View, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ShotLikeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_simple_list_shots, parent, false), mListener)
    }

    override fun onBindViewHolder(holderFollower: RecyclerView.ViewHolder?, position: Int) {
        val shotLike = mList[position]
        with(holderFollower as ShotLikeViewHolder) {
            itemView.tag_gif.visibility = if (shotLike.shot.animated) View.VISIBLE else View.GONE
            itemView.image_view.loadNormal( shotLike.shot.images.best())
        }
    }

    override fun getItemCount() = mList.size

    fun setItemClickListener(listener: ((view: View, position: Int) -> Unit)?) {
        mListener = listener
    }

    inner class ShotLikeViewHolder(itemView: View, listener: ((view: View, position: Int) -> Unit)?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val mListener = listener

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if (view != null && mListener != null) {
                mListener.invoke(view, layoutPosition)
            }
        }

    }

}