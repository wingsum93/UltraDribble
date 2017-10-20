package com.ericho.ultradribble.ui.settings.license

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ericho.ultradribble.R
import kotlinx.android.synthetic.main.fragment_licenses.*
import timber.log.Timber

/**
 *
 * Main ui for the licenses screen.
 */

class LicensesFragment : Fragment(), LicensesContract.View {

    private lateinit var mPresenter: LicensesContract.Presenter
    lateinit var adapter:LicenseAdapter
    lateinit var recyclerView:RecyclerView

    companion object {
        @JvmStatic
        fun newInstance(): LicensesFragment {
            return LicensesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater?.inflate(R.layout.fragment_licenses, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        mPresenter.subscribe()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.unsubscribe()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity.onBackPressed()
        }
        return true
    }

    override fun setPresenter(presenter: LicensesContract.Presenter) {
        mPresenter = presenter
    }

    private fun initViews() {
        recyclerView = view!!.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = LicenseAdapter(activity,this.getLicenseList())
        recyclerView.adapter = adapter


        title.setOnClickListener {
            Timber.d("licenseTitle click")
        }
    }

}