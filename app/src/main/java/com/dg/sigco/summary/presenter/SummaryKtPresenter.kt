package com.dg.sigco.summary.presenter

import com.dg.sigco.summary.SummaryRepository
import com.dg.sigco.summary.data.Payment
import com.dg.sigco.summary.view.ISummaryView

/**
 * Created by dualwer on 23/11/17.
 */
class SummaryKtPresenter(private val iSummaryView: ISummaryView) : ISummaryKtPresenter{
    override fun getPayments() {
        SummaryRepository(this).getNewPayments()
    }

    override fun showPayments(payments: List<Payment>) {
        iSummaryView.showPayments(payments)
    }
}