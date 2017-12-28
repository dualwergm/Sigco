package com.dg.sigco.card.presenter;

import com.dg.sigco.card.data.Card;

import java.util.List;

/**
 * Created by Sofia on 09/11/2017.
 */

public interface ICardContainerPresenter {
    void listCards();
    void showListCards(List<Card> cards);
    void logout();
}
