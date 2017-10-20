package com.ericho.ultradribble.ui.settings.license

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ericho.ultradribble.R
import com.ericho.ultradribble.extension.broweWithChromeTab
import com.ericho.ultradribble.widget.BaseRecyclerAdapter

/**
 * Created by steve_000 on 18/10/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.ui.settings.license
 */
class LicenseAdapter(context: Context, items: List<LicenseBo>) : BaseRecyclerAdapter<LicenseBo, LicenseAdapter.XXXViewHolder>(context, items) {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): XXXViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_license, parent, false)
        return XXXViewHolder(view)
    }

    override fun onBindContentViewHolder(holder: XXXViewHolder?, position: Int) {
        val z = items.get(position)
        holder!!.title.text = z.name
        holder.author.text = z.author
        holder.license.text = z.license

        holder.row.setOnClickListener {
            context.broweWithChromeTab(z.url)
        }
    }

    class XXXViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val row: ViewGroup = v.findViewById(R.id.row)
        val title: TextView = v.findViewById(R.id.title)
        val author: TextView = v.findViewById(R.id.author)
        val license: TextView = v.findViewById(R.id.license)

    }
}