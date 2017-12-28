package com.dg.sigco.card.repository

import android.os.AsyncTask
import android.text.TextUtils
import com.dg.sigco.card.data.Card
import com.dg.sigco.card.presenter.INewCardPresenter
import com.dg.sigco.db.helper.CardDBHelper
import com.dg.sigco.db.helper.DBHelperManager

/**
 * Created by Sofia on 02/12/2017.
 */
class NewCardRepository(private val iNewCardPresenter: INewCardPresenter){
    private val cardDBHelper = CardDBHelper.getInstance(DBHelperManager.getInstance())
    fun saveCard(card: Card){
        SaveCard(card).execute()
    }

    internal inner class SaveCard(val card: Card) : AsyncTask<Void, Void, Long>() {
        override fun doInBackground(vararg params: Void?): Long {
            when {
                TextUtils.isEmpty(card._id) -> return cardDBHelper.save(card)
                else -> return cardDBHelper.update(card)
            }
        }

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            iNewCardPresenter.afterSave(result?.let { it >= 0 }!!)
        }
    }
}