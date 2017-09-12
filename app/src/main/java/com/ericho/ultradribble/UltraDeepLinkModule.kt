

package com.ericho.ultradribble

import com.airbnb.deeplinkdispatch.DeepLinkModule

/**
 *
 * The deep link module. For every class annotated [DeepLinkModule],
 * [DeepLinkDispatch](com.airbnb.deeplinkdispatch) will generate a `Loader` class,
 * which contains a registry of all the [DeepLinkModule] annotations.
 */

@DeepLinkModule
class UltraDeepLinkModule