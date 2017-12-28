package com.dg.sigco.db.shema.contract;

import android.provider.BaseColumns;

import com.dg.sigco.db.shema.DBConstanst;

/**
 * Created by Sofia on 31/10/2017.
 */

public final class ClientContract {

    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ ClientEntry.TABLE_NAME+" ("+
            ClientEntry._ID + DBConstanst.PK_TYPE + DBConstanst.COMMA +
            ClientEntry.CLIENT_ID_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            ClientEntry.NAME_COLUMN+ DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            ClientEntry.ALIAS_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            ClientEntry.ADDRESS_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            ClientEntry.PHONE_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            "CONSTRAINT clientid_UK UNIQUE (clientId) " + DBConstanst.COMMA +
            "CONSTRAINT namealias_UK UNIQUE (name, alias) );";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + ClientEntry.TABLE_NAME;

    public static abstract class ClientEntry implements BaseColumns {
        public static final String TABLE_NAME = "client";
        public static final String CLIENT_ID_COLUMN = "clientId";
        public static final String NAME_COLUMN = "name";
        public static final String ALIAS_COLUMN = "alias";
        public static final String ADDRESS_COLUMN = "address";
        public static final String PHONE_COLUMN = "phone";
    }
}
