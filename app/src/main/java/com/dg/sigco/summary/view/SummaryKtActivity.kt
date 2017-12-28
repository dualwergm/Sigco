package com.dg.sigco.summary.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dg.sigco.R
import com.dg.sigco.summary.data.Payment
import com.dg.sigco.summary.presenter.SummaryKtPresenter
import kotlinx.android.synthetic.main.activity_summary_kt.*

class SummaryKtActivity : AppCompatActivity(), ISummaryView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_kt)
        close.setOnClickListener { finish() }
        summaryReycler.layoutManager = LinearLayoutManager(this)
        SummaryKtPresenter(this).getPayments()
    }

    override fun showPayments(payments: List<Payment>) = when {
        payments.isEmpty() -> {
            noSummary.visibility = View.VISIBLE
            summaryReycler.visibility = View.GONE
        }
        else -> summaryReycler.adapter = SummaryKtAdpater(payments)
    }
}
