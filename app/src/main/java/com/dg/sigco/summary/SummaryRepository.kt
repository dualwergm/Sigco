package com.dg.sigco.summary

import android.database.Cursor
import android.os.AsyncTask
import com.dg.sigco.common.UtilDate
import com.dg.sigco.db.helper.DBHelperManager
import com.dg.sigco.db.helper.DetailDBHelper
import com.dg.sigco.db.shema.contract.DetailContract
import com.dg.sigco.summary.data.Payment
import com.dg.sigco.summary.presenter.ISummaryKtPresenter
import java.util.ArrayList

/**
 * Created by dualwer on 23/11/17.
 */
class SummaryRepository(val iSummaryPresenter:ISummaryKtPresenter) {

    private val dbHelper = DetailDBHelper.getInstance(DBHelperManager.getInstance())

    fun getNewPayments() {
        GetPayments().execute()
    }

    internal inner class GetPayments : AsyncTask<Void, Void, Cursor>() {
        override fun doInBackground(vararg params: Void?): Cursor = dbHelper.getNewPayments()

        override fun onPostExecute(cursor: Cursor?) {
            super.onPostExecute(cursor)
            val payments = ArrayList<Payment>()
            while (cursor?.moveToNext()!!) {
                val time = cursor.getLong(cursor.getColumnIndex(DetailContract.DetailEntry.CREATIONDATE_COLUMN))
                val value = cursor.getDouble(cursor.getColumnIndex("total"))
                payments += Payment(value,UtilDate.getDateES(time))
            }
            cursor?.close()
            iSummaryPresenter.showPayments(payments)
        }
    }
}