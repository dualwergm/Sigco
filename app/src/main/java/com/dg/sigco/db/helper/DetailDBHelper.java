package com.dg.sigco.db.helper;

import android.database.Cursor;

import com.dg.sigco.card.data.Detail;
import com.dg.sigco.db.shema.contract.DetailContract;

/**
 * Created by Sofia on 31/10/2017.
 */

public class DetailDBHelper {

    private static DetailDBHelper cardHelper;

    private DBHelperManager helperManager;
    public static final String[] SELECT = new String[]{
            DetailContract.DetailEntry._ID,
            DetailContract.DetailEntry.DETAIL_ID_COLUMN,
            DetailContract.DetailEntry.CARD_ID_COLUMN,
            DetailContract.DetailEntry.CARD_SLID_COLUMN,
            DetailContract.DetailEntry.DATE_COLUMN,
            DetailContract.DetailEntry.VALUEC_COLUMN,
            DetailContract.DetailEntry.CREATIONDATE_COLUMN
    };

    private DetailDBHelper(DBHelperManager helperManager){
        this.helperManager = helperManager;
    }

    public static DetailDBHelper getInstance(DBHelperManager helperManager){
        if(cardHelper == null){
            cardHelper = new DetailDBHelper(helperManager);
        }
        return cardHelper;
    }

    public long save(Detail detail) {
        return helperManager.getWritableDatabase().insert(
                DetailContract.DetailEntry.TABLE_NAME,
                null,
                detail.toContentValues());
    }

    public long update(Detail detail) {
        return helperManager.getWritableDatabase().update(
                DetailContract.DetailEntry.TABLE_NAME,
                detail.toUpdateValues(),
                DetailContract.DetailEntry._ID + " = ? ",
                new String[] {detail.get_id()});
    }

    public Cursor getDetails(String cardSLId) {
        return helperManager.getReadableDatabase()
                .query(
                        DetailContract.DetailEntry.TABLE_NAME,
                        SELECT,
                        DetailContract.DetailEntry.CARD_SLID_COLUMN +" = '"+cardSLId+"'",
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getNewDetailsOldCards() {
        return helperManager.getReadableDatabase()
                .query(
                        DetailContract.DetailEntry.TABLE_NAME,
                        SELECT,
                        "cardDetailId = 0 and cardId > 0",
                        null,
                        null,
                        null,
                        null);
    }

    public double getPayed(int cardId) {
        String sql = "SELECT SUM("+DetailContract.DetailEntry.VALUEC_COLUMN+") FROM "+DetailContract.DetailEntry.TABLE_NAME + " WHERE " + DetailContract.DetailEntry.CARD_ID_COLUMN +" = "+cardId;
        Cursor cursor = helperManager.getReadableDatabase().rawQuery(sql, null);
        return getPayed(cursor);
    }

    private double getPayed(Cursor cursor){
        if(cursor.moveToFirst()){
            return cursor.getDouble(0);
        }
        return Double.parseDouble("0");
    }

    public Cursor getNewPayments(){
        return helperManager.getReadableDatabase().rawQuery("select creationdate, sum(valuec) as total from carddetail where cardDetailId = 0 group by creationdate order by creationdate desc", null);
    }
}