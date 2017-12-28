package com.dg.sigco.card.presenter;

import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.repository.CardRepositoryImp;
import com.dg.sigco.card.view.ICardContainerView;
import com.dg.sigco.common.Constants;
import com.dg.sigco.parameter.repository.ParameterRespository;

import java.util.List;

/**
 * Created by Sofia on 09/11/2017.
 */

public class CardContainerPresenterImp implements ICardContainerPresenter{

    private ICardContainerView iCardContainerView;
    private CardRepositoryImp cardRepositoryImp;
    public CardContainerPresenterImp(ICardContainerView iCardContainerView){
        this.iCardContainerView = iCardContainerView;
        cardRepositoryImp = new CardRepositoryImp(this);
    }


    @Override
    public void listCards() {
        cardRepositoryImp.listCards();
    }

    @Override
    public void showListCards(List<Card> cards) {
        iCardContainerView.showListCards(cards);
    }

    @Override
    public void logout() {
        ParameterRespository.getInstance().delete(Constants.KEY_USER_LOGGED_PARAMETER);
        iCardContainerView.showLogin();
    }
}
