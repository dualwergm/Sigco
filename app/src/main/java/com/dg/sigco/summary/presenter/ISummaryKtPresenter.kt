package com.dg.sigco.summary.presenter

import com.dg.sigco.summary.data.Payment

/**
 * Created by dualwer on 23/11/17.
 */
interface ISummaryKtPresenter {
    fun getPayments()
    fun showPayments(payments:List<Payment>)
}