

package com.ericho.ultradribble

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.ericho.ultradribble.UltraDeepLinkModule

/**
 *
 * The activity with the scheme you'd like to handle in your `AndroidManifest.xml` file
 * Annotate it with [DeepLinkHandler] and provide a list of [com.airbnb.deeplinkdispatch.DeepLinkModule]
 * annotated class(es).
 */

@DeepLinkHandler(UltraDeepLinkModule::class)
class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DeepLinkDelegate, MangoDeepLinkModuleLoader is generated at compile-time.
        val deepLinkDelegate = DeepLinkDelegate(UltraDeepLinkModuleLoader())

        // Delegate the deep link handling to DeepLinkDispatch.
        // It will start the correct Activity based on the incoming Intent URI
        deepLinkDelegate.dispatchFrom(this)

        // Finish this Activity since the correct one has been just started
        finish()

    }
}