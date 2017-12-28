package com.dg.sigco.card.interactor

import com.dg.sigco.card.repository.CardRepositoryImp
import com.dg.sigco.card.repository.DetailRepositoryImp
import com.dg.sigco.common.UtilDate

/**
 * Created by dualwer on 7/12/17.
 */
class CardInteractor {

    fun updateTodayToCard(cardId:Int){
        val payed = DetailRepositoryImp.getInstance().getPayed(cardId)
        val cardKt = CardRepositoryImp().getCard(cardId)
        val daysPayed = (payed / cardKt.quota).toInt()
        val toDayTo = UtilDate.addDays(UtilDate.stringToDate(cardKt.dateStr, UtilDate._DATE_FORMAT_SHORT), daysPayed-1)
        val dateShort = UtilDate.getDateShort(toDayTo)
        CardRepositoryImp().updateTodayto(cardId, dateShort)
    }
}