package com.ericho.ultradribble.ui.user.shots

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericho.ultradribble.R
import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.extension.loadNormal
import kotlinx.android.synthetic.main.item_simple_list_shots.view.*

/**
 */
class ShotsAdapter(context: Context, list: List<Shot>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mContext = context
    private var mList = list
    private var mListener: ((View, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ShotViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_simple_list_shots, parent, false), mListener)
    }

    override fun onBindViewHolder(holderFollower: RecyclerView.ViewHolder?, position: Int) {
        val shot = mList[position]
        with(holderFollower as ShotViewHolder) {
            itemView.tag_gif.visibility = if (shot.animated) View.VISIBLE else View.GONE
            itemView.image_view.loadNormal(shot.images.best())
        }
    }

    override fun getItemCount() = mList.size

    fun setItemClickListener(listener: ((view: View, position: Int) -> Unit)?) {
        mListener = listener
    }

    inner class ShotViewHolder(itemView: View, listener: ((view: View, position: Int) -> Unit)?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

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