package com.ericho.ultradribble.ui.settings.license

import com.ericho.ultradribble.mvp.BasePresenter
import com.ericho.ultradribble.mvp.BaseView

/**
 *
 * This specifies the contract between the view and the presenter.
 */

interface LicensesContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter

}