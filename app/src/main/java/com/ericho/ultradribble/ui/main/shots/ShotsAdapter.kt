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
import com.ericho.ultradribble.widget.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_shot.view.*

/**
 */

class ShotsAdapter(context: Context, list: List<Shot>) : BaseRecyclerAdapter<Shot,ShotsAdapter.ShotViewHolder>(context,list,1000) {

    private var mListener: OnRecyclerViewItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShotsAdapter.ShotViewHolder {
        return ShotViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shot, parent, false), mListener)
    }


    override fun onBindViewHolder(holder: ShotsAdapter.ShotViewHolder?, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position <= items.size) {
            val shot = items[position]

            holder?.setData(shot)

        }
    }



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
        fun setData(shot:Shot){
            itemView.avatar.loadAvatar(shot.user?.avatarUrl!!)
            itemView.shot_image_view.loadNormal(shot.images.best())
            itemView.tag_gif.visibility = if (shot.animated) View.VISIBLE else View.GONE
            itemView.shot_title.text = context.getString(R.string.shot_title).format(shot.user?.name, shot.title)
        }
    }

}