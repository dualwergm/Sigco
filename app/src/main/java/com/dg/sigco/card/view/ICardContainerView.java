package com.dg.sigco.card.view;

import com.dg.sigco.card.data.Card;

import java.util.List;

/**
 * Created by Sofia on 09/11/2017.
 */

public interface ICardContainerView {
    void showListCards(List<Card> cards);
    void showLogin();
}
