package com.dg.sigco.summary.view

import com.dg.sigco.summary.data.Payment

/**
 * Created by dualwer on 23/11/17.
 */
interface ISummaryView {
    fun showPayments(payments:List<Payment>)
}