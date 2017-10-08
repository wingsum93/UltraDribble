package com.ericho.ultradribble.ui.test

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ericho.ultradribble.R
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.support.v4.toast

/**
 * Created by steve_000 on 22/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.ui.test
 */
class TestMainFrag:Fragment() {

    var btn_1:Button? = null
    var btn_2:Button? = null
    var btn_3:Button? = null

    var mCallback:TestHomeListener? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_test_main,container,false)
        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCallback = activity as TestHomeListener

        btn_1 = view!!.findViewById(R.id.btn_1)
        btn_2 = view.findViewById(R.id.btn_2)
        btn_3 = view.findViewById(R.id.btn_3)

        btn_1!!.text = "Custom Button "

        btn_1!!.setOnClickListener {
            mCallback!!.switchToFragment(TestBasicButtonFrag())
        }
    }

    interface TestHomeListener{

        fun onCustomButtonOpen()
        fun switchToFragment(frag:Fragment)
    }
}