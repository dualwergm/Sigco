package com.dg.sigco.summary.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.dg.sigco.R
import com.dg.sigco.common.TextUtils
import com.dg.sigco.common.inflate
import com.dg.sigco.summary.data.Payment
import kotlinx.android.synthetic.main.item_summary.view.*

/**
 * Created by dualwer on 23/11/17.
 */
class SummaryKtAdpater(val payments: List<Payment>): RecyclerView.Adapter<SummaryKtAdpater.SummaryHolder>() {

    override fun onBindViewHolder(holder: SummaryHolder?, position: Int) {
        holder?.bindView(payments[position])
    }

    override fun getItemCount(): Int = payments.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SummaryHolder =
            SummaryHolder(parent?.inflate(R.layout.item_summary))

    class SummaryHolder(itemView:View?):RecyclerView.ViewHolder(itemView){
        fun bindView(payment: Payment){
            with(payment){
                itemView.summaryDate.text = datePay
                itemView.summaryValue.text = "$ ${TextUtils.formatMoney(valuec.toString())}"
            }
        }
    }
}