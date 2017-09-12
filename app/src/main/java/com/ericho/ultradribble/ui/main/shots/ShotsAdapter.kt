package com.ericho.ultradribble.ui.main.shots

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericho.ultradribble.R
import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.extension.loadAvatar
import com.ericho.ultradribble.extension.loadNormal
import kotlinx.android.synthetic.main.item_shot.view.*

/**
 */

class ShotsAdapter(context: Context, list: List<Shot>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mContext = context
    private var mList = list
    private var mListener: OnRecyclerViewItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ShotViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shot, parent, false), mListener)
    }

    override fun onBindViewHolder(holderFollower: RecyclerView.ViewHolder?, position: Int) {
        if (position <= mList.size) {
            val shot = mList[position]
            with(holderFollower as ShotViewHolder) {
                itemView.avatar.loadAvatar(shot.user?.avatarUrl!!)
                itemView.shot_image_view.loadNormal(shot.images.best())
                itemView.tag_gif.visibility = if (shot.animated) View.VISIBLE else View.GONE
                itemView.shot_title.text = mContext.getString(R.string.shot_title).format(shot.user?.name, shot.title)
            }
        }
    }

    override fun getItemCount() = mList.size

    fun setItemClickListener(listener: OnRecyclerViewItemClickListener) {
        mListener = listener
    }

    inner class ShotViewHolder(itemView: View, listener: OnRecyclerViewItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        private val mListener = listener

        init {
            itemView.avatar.setOnClickListener({ view ->
                mListener?.onAvatarClick(view, layoutPosition)
            })

            itemView.setOnClickListener({ view ->
                mListener?.onItemClick(view, layoutPosition)
            })
        }

    }

}