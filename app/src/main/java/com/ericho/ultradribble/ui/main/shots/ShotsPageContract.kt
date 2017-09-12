package com.ericho.ultradribble.ui.main.shots

import com.ericho.ultradribble.data.Shot
import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */

interface ShotsPageContract {

    interface View : BaseView<Presenter> {

        fun initViews()

        fun setLoadingIndicator(loading: Boolean)

        fun showResults(results: List<Shot>)

        fun notifyDataAllRemoved(size: Int)

        fun notifyDataAdded(startPosition: Int, size: Int)

        fun showNetworkError()

        fun setEmptyContentVisibility(visible: Boolean)

    }

    interface Presenter : BasePresenter {

        fun listShots()

        fun listMoreShots()

    }

}