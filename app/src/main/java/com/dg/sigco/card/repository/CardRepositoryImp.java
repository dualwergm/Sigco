package com.dg.sigco.card.repository;

import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.dg.sigco.api.service.CardService;
import com.dg.sigco.api.service.RetrofitBuilder;
import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.data.CardKt;
import com.dg.sigco.card.data.Line;
import com.dg.sigco.card.presenter.ICardContainerPresenter;
import com.dg.sigco.card.presenter.IDownloadPresenter;
import com.dg.sigco.client.repository.ClientRepositoryImp;
import com.dg.sigco.common.Constants;
import com.dg.sigco.db.helper.CardDBHelper;
import com.dg.sigco.db.helper.DBHelperManager;
import com.dg.sigco.line.repository.LineRepositoryImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sofia on 31/10/2017.
 */

public class CardRepositoryImp implements Callback<List<Line>>{
    private IDownloadPresenter presenter;
    private CardDBHelper dbHelper;
    private DetailRepositoryImp detailRepositoryImp;
    private ClientRepositoryImp clientRepositoryImp;
    private ICardContainerPresenter iCardContainerPresenter;
    public CardRepositoryImp(IDownloadPresenter presenter){
        this.presenter = presenter;
        this.dbHelper = CardDBHelper.getInstance(DBHelperManager.getInstance());
        detailRepositoryImp = DetailRepositoryImp.getInstance();
        clientRepositoryImp = ClientRepositoryImp.getInstance();
    }

    public CardRepositoryImp(ICardContainerPresenter iCardContainerPresenter){
        this.iCardContainerPresenter = iCardContainerPresenter;
        this.dbHelper = CardDBHelper.getInstance(DBHelperManager.getInstance());
    }

    public CardRepositoryImp(){
        this.dbHelper = CardDBHelper.getInstance(DBHelperManager.getInstance());
    }

    public void downloadCards() {
        CardService service = RetrofitBuilder.getCardService();
        Call<List<Line>> call = service.downloadCards(getMaxCardId());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {
        if(response.code() != Constants.HTTP_OK){
            presenter.connectionError();
            return;
        }
        List<Line> list = response.body();
        presenter.evaluateDownload(list);
    }

    @Override
    public void onFailure(Call<List<Line>> call, Throwable t) {
        Log.e("onFailure-Cards", t.toString());
        presenter.connectionError();
    }

    public void saveList(List<Card> cards, int lineId){
        if(cards == null || cards.isEmpty()){
            return;
        }
        for(Card card: cards){
            card.setLineId(lineId);
            String clientSLId = LineRepositoryImp.savedMap.get(card.getClient().getClientId());
            if(TextUtils.isEmpty(clientSLId)){
                clientSLId = String.valueOf(clientRepositoryImp.save(card.getClient()));
                LineRepositoryImp.savedMap.put(card.getClient().getClientId(), clientSLId);
            }
            card.setClientSLId(clientSLId);
            long save = dbHelper.save(card);
            detailRepositoryImp.saveList(card.getDetails(), String.valueOf(save));
        }
    }

    public void listCards(){
        new ListCards().execute();
    }

    private class ListCards extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected Cursor doInBackground(Void... params) {
            return dbHelper.getCards();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            List<Card> cards = new ArrayList<>();
            while(cursor.moveToNext()){
                cards.add(new Card(cursor, false));
            }
            cursor.close();
            iCardContainerPresenter.showListCards(cards);
        }
    }

    public CardKt getCard(int cardId){
        Cursor cursor = dbHelper.getCard(cardId);
        CardKt cardKt = null;
        if(cursor.moveToNext()){
            cardKt = new CardKt(cursor);
        }
        cursor.close();
        return cardKt;
    }

    public List<Card> getNewsCards(){
        DetailRepositoryImp detailRepositoryImp = DetailRepositoryImp.getInstance();
        Cursor cursor = dbHelper.getNewCards();
        List<Card> cards = new ArrayList<>();
        while(cursor.moveToNext()){
            Card card = new Card(cursor, true);
            card.setDetails(detailRepositoryImp.getNewDetails(card.get_id()));
            cards.add(card);
        }
        cursor.close();
        return cards;
    }

    public int getMaxCardId(){
        Cursor cursor = dbHelper.getMaxCardId();
        int maxCardId = 0;
        if(cursor.moveToNext()){
            maxCardId = cursor.getInt(0);
        }
        cursor.close();
        return maxCardId;
    }

    public void updateTodayto(int cardId, String todayto, int cardStatus){
        dbHelper.updateTodayto(cardId, todayto, cardStatus);
    }
}