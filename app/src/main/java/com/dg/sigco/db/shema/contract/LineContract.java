package com.dg.sigco.db.shema.contract;

import android.provider.BaseColumns;

import com.dg.sigco.db.shema.DBConstanst;

/**
 * Created by Sofia on 31/10/2017.
 */

public final class LineContract {

    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ LineEntry.TABLE_NAME+" ("+
            LineEntry._ID + DBConstanst.PK_TYPE + DBConstanst.COMMA +
            LineEntry.LINE_ID_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            LineEntry.NAME_COLUMN+ DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            LineEntry.CODE_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            LineEntry.DESCRIPTION_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            LineEntry.CONFIG_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            "CONSTRAINT lineid_UK UNIQUE (lineId) );";


    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + LineEntry.TABLE_NAME;

    public static abstract class LineEntry implements BaseColumns {
        public static final String TABLE_NAME = "line";
        public static final String LINE_ID_COLUMN = "lineId";
        public static final String NAME_COLUMN = "name";
        public static final String CODE_COLUMN = "code";
        public static final String DESCRIPTION_COLUMN = "description";
        public static final String CONFIG_COLUMN = "config";
    }
}
