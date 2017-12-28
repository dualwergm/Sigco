package com.dg.sigco.db.helper;

import android.database.Cursor;

import com.dg.sigco.card.data.Card;
import com.dg.sigco.db.shema.contract.CardContract;

/**
 * Created by Sofia on 31/10/2017.
 */

public class CardDBHelper {

    public static final String[] SELECT = new String[]{
            CardContract.CardEntry._ID,
            CardContract.CardEntry.CARD_ID_COLUMN,
            CardContract.CardEntry.CLIENT_ID_COLUMN,
            CardContract.CardEntry.CLIENT_SLID_COLUMN,
            CardContract.CardEntry.LINE_ID_COLUMN,
            CardContract.CardEntry.DATEC_COLUMN,
            CardContract.CardEntry.VALUEC_COLUMN,
            CardContract.CardEntry.QUOTA_COLUMN,
            CardContract.CardEntry.PRODUCTS_COLUMN,
            CardContract.CardEntry.ADDRESS_COLUMN,
            CardContract.CardEntry.PHONE_COLUMN,
            CardContract.CardEntry.TODAYTO_COLUMN,
            CardContract.CardEntry.CREATION_DATE_COLUMN
    };

    private static CardDBHelper cardHelper;
    private DBHelperManager helperManager;

    private CardDBHelper(DBHelperManager helperManager){
        this.helperManager = helperManager;
    }

    public static CardDBHelper getInstance(DBHelperManager helperManager){
        if(cardHelper == null){
            cardHelper = new CardDBHelper(helperManager);
        }
        return cardHelper;
    }

    public long save(Card card) {
        return helperManager.getWritableDatabase().insert(
                CardContract.CardEntry.TABLE_NAME,
                null,
                card.toContentValues());
    }

    public long update(Card card) {
        return helperManager.getWritableDatabase().update(
                CardContract.CardEntry.TABLE_NAME,
                card.toContentUpdateValues(),
                "_id = '"+card.get_id()+"'",
                null
                );
    }

    public Cursor getCards() {
        return helperManager.getReadableDatabase()
                .query(
                        CardContract.CardEntry.TABLE_NAME,
                        SELECT,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getCard(int cardId) {
        return helperManager.getReadableDatabase()
                .query(
                        CardContract.CardEntry.TABLE_NAME,
                        SELECT,
                        "cardId = "+cardId,
                        null,
                        null,
                        null,
                        null);
    }

    public void updateTodayto(int cardId, String todayTo) {
        String sql = "Update card set todayto = '"+todayTo+"' WHERE cardId = "+cardId;
        helperManager.getWritableDatabase().execSQL(sql);
    }

    public Cursor getNewCards() {
        return helperManager.getReadableDatabase()
                .query(
                        CardContract.CardEntry.TABLE_NAME,
                        SELECT,
                        "cardId = 0",
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getMaxCardId(){
        return helperManager.getReadableDatabase()
                .query(
                        CardContract.CardEntry.TABLE_NAME,
                        new String[]{"MAX(cardId)"},
                        null, null, null, null,null
                );
    }
}
