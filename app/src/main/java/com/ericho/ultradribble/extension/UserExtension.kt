package com.ericho.ultradribble.extension

import com.ericho.ultradribble.data.User


/**
 * Created by Eric Ho on 2017/8/6.
 *
 * A collection of some usable functions about [User]
 */
fun User?.canUserComment(): Boolean =
        (this != null) && ("Player" == this.type || "Team" == this.type)