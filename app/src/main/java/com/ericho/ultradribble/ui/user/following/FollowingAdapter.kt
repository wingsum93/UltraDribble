package com.ericho.ultradribble.ui.user.following

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericho.ultradribble.R
import com.ericho.ultradribble.data.Followee
import com.ericho.ultradribble.extension.loadAvatar
import kotlinx.android.synthetic.main.item_user.view.*

/**
 */
class FollowingAdapter(context: Context, list: List<Followee>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext = context
    private val mList = list
    private var mListener: ((View, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FollowingViewHolder {
        return FollowingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false), mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val followee = mList[position]
        with(holder as FollowingViewHolder) {
            itemView.avatar.loadAvatar( followee.followee.avatarUrl)
            itemView.name.text = followee.followee.name
            itemView.user_name.text = followee.followee.username
            itemView.bio.text = if (Build.VERSION.SDK_INT >= 24) {
                Html.fromHtml(followee.followee.bio, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(followee.followee.bio)
            }
        }
    }

    override fun getItemCount() = mList.size

    fun setOnItemClickListener(listener: ((View, Int) -> Unit)?) {
        mListener = listener
    }

    class FollowingViewHolder(itemView: View, listener: ((view: View, position: Int) -> Unit)?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val mListener = listener

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if (mListener != null && view != null) {
                mListener.invoke(view, layoutPosition)
            }
        }

    }
}