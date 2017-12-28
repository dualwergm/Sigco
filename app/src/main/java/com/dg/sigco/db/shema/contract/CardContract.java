package com.dg.sigco.db.shema.contract;

import android.provider.BaseColumns;

import com.dg.sigco.db.shema.DBConstanst;

public final class CardContract {

    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ CardEntry.TABLE_NAME+" ("+
            CardEntry._ID + DBConstanst.PK_TYPE + DBConstanst.COMMA +
            CardEntry.CARD_ID_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            CardEntry.LINE_ID_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            CardEntry.CLIENT_ID_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            CardEntry.CLIENT_SLID_COLUMN+ DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            CardEntry.PRODUCTS_COLUMN+ DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            CardEntry.VALUEC_COLUMN + DBConstanst.REAL_TYPE + DBConstanst.COMMA +
            CardEntry.QUOTA_COLUMN + DBConstanst.REAL_TYPE + DBConstanst.COMMA +
            CardEntry.DATEC_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            CardEntry.CREATION_DATE_COLUMN + DBConstanst.NUMERIC_TYPE + DBConstanst.COMMA +
            CardEntry.TODAYTO_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            CardEntry.ADDRESS_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            CardEntry.PHONE_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            CardEntry.STATUS_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            CardEntry.ACTIVE_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            "CONSTRAINT cardid_UK UNIQUE (cardId) );";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + CardEntry.TABLE_NAME;

    public static abstract class CardEntry implements BaseColumns {
        public static final String TABLE_NAME = "card";
        public static final String CARD_ID_COLUMN = "cardId";
        public static final String LINE_ID_COLUMN = "lineId";
        public static final String CLIENT_ID_COLUMN = "clientId";
        public static final String CLIENT_SLID_COLUMN = "clientSLId";
        public static final String PRODUCTS_COLUMN = "products";
        public static final String VALUEC_COLUMN = "valuec";
        public static final String QUOTA_COLUMN = "quota";
        public static final String DATEC_COLUMN = "datec";
        public static final String CREATION_DATE_COLUMN = "creationdate";
        public static final String TODAYTO_COLUMN = "todayto";
        public static final String ADDRESS_COLUMN = "address";
        public static final String PHONE_COLUMN = "phone";
        public static final String STATUS_COLUMN = "status";
        public static final String ACTIVE_COLUMN = "active";
    }
}