package com.dg.sigco.card.presenter

import com.dg.sigco.card.data.Card

/**
 * Created by Sofia on 02/12/2017.
 */
interface INewCardPresenter {
    fun save(card: Card);
    fun afterSave(saved:Boolean);
}