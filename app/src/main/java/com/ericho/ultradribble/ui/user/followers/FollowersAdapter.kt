package com.ericho.ultradribble.ui.user.followers

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericho.ultradribble.R
import com.ericho.ultradribble.data.Follower
import com.ericho.ultradribble.extension.loadAvatar
import kotlinx.android.synthetic.main.item_user.view.*

/**
 */

class FollowersAdapter(context: Context, list: List<Follower>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext = context
    private val mList = list
    private var mListener: ((View, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false), mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val follower = mList[position]
        with(holder as FollowerViewHolder) {
            itemView.avatar.loadAvatar( follower.follower.avatarUrl!!)

            itemView.name.text = follower.follower.name
            itemView.user_name.text = follower.follower.username

            itemView.bio.text = if (Build.VERSION.SDK_INT >= 24) {
                Html.fromHtml(follower.follower.bio, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(follower.follower.bio)
            }
        }
    }

    override fun getItemCount() = mList.size

    fun setOnItemClickListener(listener: ((View, Int) -> Unit)?) {
        mListener = listener
    }

    class FollowerViewHolder(itemView: View, listener: ((view: View, position: Int) -> Unit)?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
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