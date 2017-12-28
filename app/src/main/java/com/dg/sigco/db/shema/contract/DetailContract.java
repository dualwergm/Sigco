package com.dg.sigco.db.shema.contract;

import android.provider.BaseColumns;

import com.dg.sigco.db.shema.DBConstanst;

/**
 * Created by Sofia on 06/11/2017.
 */

public final class DetailContract {

    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ DetailContract.DetailEntry.TABLE_NAME+" ("+
            DetailContract.DetailEntry._ID + DBConstanst.PK_TYPE + DBConstanst.COMMA +
            DetailContract.DetailEntry.DETAIL_ID_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            DetailContract.DetailEntry.CARD_ID_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            DetailContract.DetailEntry.CARD_SLID_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            DetailContract.DetailEntry.VALUEC_COLUMN+ DBConstanst.REAL_TYPE + DBConstanst.COMMA +
            DetailContract.DetailEntry.CREATIONDATE_COLUMN + DBConstanst.NUMERIC_TYPE + DBConstanst.COMMA +
            DetailContract.DetailEntry.DATE_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            "CONSTRAINT detailid_UK UNIQUE (cardDetailId) );";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + DetailContract.DetailEntry.TABLE_NAME;

    public static abstract class DetailEntry implements BaseColumns {
        public static final String TABLE_NAME = "carddetail";
        public static final String DETAIL_ID_COLUMN = "cardDetailId";
        public static final String CARD_ID_COLUMN = "cardId";
        public static final String CARD_SLID_COLUMN = "cardSLId";
        public static final String VALUEC_COLUMN = "valuec";
        public static final String CREATIONDATE_COLUMN = "creationdate";
        public static final String DATE_COLUMN = "datec";
    }
}
