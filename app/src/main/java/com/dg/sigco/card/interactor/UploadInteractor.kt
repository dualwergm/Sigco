package com.dg.sigco.card.interactor

import android.os.AsyncTask
import com.dg.sigco.card.presenter.IUploadPresenter
import com.dg.sigco.card.repository.CardRepositoryImp
import com.dg.sigco.card.repository.DetailRepositoryImp
import com.dg.sigco.card.repository.UploadRepository
import com.dg.sigco.common.Constants
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Sofia on 12/12/2017.
 */
class UploadInteractor(val iUploadPresenter: IUploadPresenter) {

    fun uploadCards(){
        Upload(iUploadPresenter).execute()
    }

    internal class Upload(val iUploadPresenter: IUploadPresenter) : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg p0: Void?): String {
            val allCards = CardRepositoryImp().newsCards
            var allDetailsOldCards = DetailRepositoryImp.getInstance().newsDetailOldCards
            val cardArray = JSONArray()
            val detailArray = JSONArray()
            allCards.forEach { card ->
                cardArray.put(card.toJson())
            }
            allDetailsOldCards.forEach { detail ->
                detailArray.put(detail.toJson())
            }
            val jResult = JSONObject()
            jResult.put(Constants.CARDS, cardArray.toString())
            jResult.put(Constants.DETAILS, detailArray.toString())
            return jResult.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            UploadRepository.getInstance(iUploadPresenter).uploadCard(result)
        }
    }
}