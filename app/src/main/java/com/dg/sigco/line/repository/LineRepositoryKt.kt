package com.dg.sigco.line.repository

import android.database.Cursor
import android.os.AsyncTask
import com.dg.sigco.db.helper.DBHelperManager
import com.dg.sigco.db.helper.LineDBHelper
import com.dg.sigco.line.data.LineKt
import com.dg.sigco.line.presenter.ILinePresenter

/**
 * Created by dualwer on 27/11/17.
 */
class LineRepositoryKt(val iLinePresenter: ILinePresenter) {

    val lineDBHelper = LineDBHelper.getInstance(DBHelperManager.getInstance())

    fun getLines(){
        AllLines().execute()
    }

    internal inner class AllLines : AsyncTask<Void, Void, Cursor>() {
        override fun doInBackground(vararg params: Void?): Cursor = lineDBHelper.lines

        override fun onPostExecute(cursor: Cursor?) {
            super.onPostExecute(cursor)
            val lines = ArrayList<LineKt>()
            while(cursor?.moveToNext()!!){
                lines.add(LineKt(cursor))
            }
            cursor?.close()
            iLinePresenter.showLines(lines)
        }
    }
}