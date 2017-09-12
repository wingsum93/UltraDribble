package com.ericho.ultradribble.ui.user.shots

import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */

interface ShotsContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(loading: Boolean)

        fun showShots(shots: List<Shot>)

        fun notifyDataAllRemoved(size: Int)

        fun notifyDataAdded(startPosition: Int, size: Int)

        fun setEmptyViewVisibility(visible: Boolean)

        fun showNetworkError()

    }

    interface Presenter : BasePresenter {

        fun loadShots()

        fun loadShotsOfNextPage()

    }

}