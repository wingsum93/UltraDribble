package com.ericho.ultradribble.view

import com.ericho.ultradribble.ui.test.TestMainFrag
import org.jetbrains.anko.*

/**
 * Created by steve_000 on 25/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.view
 */
class TestMainFragUI:AnkoComponent<TestMainFrag> {
    val ET_ID = 111
    override fun createView(ui: AnkoContext<TestMainFrag>)= with(ui){
        verticalLayout {
            val name = editText("LayoutActyUI") {
                id = ET_ID
            }
            button("Say Hello") {

                setOnClickListener {
                    ctx.toast("Hello, ${name.text}!")
                    name.textColor = 0xffff0000.toInt()
                }
            }
        }
    }
}