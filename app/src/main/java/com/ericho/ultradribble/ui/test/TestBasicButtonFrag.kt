package com.ericho.ultradribble.ui.test

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.ericho.ultradribble.R
import com.ericho.ultradribble.widget.CircularProgressDrawable
import com.ericho.ultradribble.widget.ProgressView
import com.ericho.ultradribble.widget.ProgressView2

/**
 * Created by steve_000 on 28/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.ui.test
 */
class TestBasicButtonFrag:Fragment(){

    var progressView:ProgressView? = null
    var progressView2: ProgressView2? = null
    var btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_test_button,container,false)
        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressView = view!!.findViewById(R.id.progressView)
        progressView2 = view.findViewById(R.id.progressView2)
        btn = view.findViewById(R.id.btn)


        btn!!.setOnClickListener {
            progressView!!.start()
        }

        progressView
    }
}