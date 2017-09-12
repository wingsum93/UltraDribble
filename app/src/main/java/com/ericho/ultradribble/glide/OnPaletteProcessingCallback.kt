package com.ericho.ultradribble.glide

import android.support.v7.graphics.Palette

/**
 * Created by steve_000 on 12/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.glide
 */
interface OnPaletteProcessingCallback {
    /**
     * The [Palette] finishes its work successfully.
     */
    fun OnPaletteGenerated(palette: Palette?)

    /**
     * The [Palette] finished its work with a failure.
     */
    fun OnPaletteNotAvailable()
}