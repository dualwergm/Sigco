package com.dg.sigco.db.shema.contract;

import android.provider.BaseColumns;

import com.dg.sigco.db.shema.DBConstanst;

/**
 * Created by Sofia on 31/10/2017.
 */

public final class ParameterContract {

    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ ParameterEntry.TABLE_NAME+" ("+
            ParameterEntry._ID + DBConstanst.PK_TYPE + DBConstanst.COMMA +
            ParameterEntry.COLUMN_KEY + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            ParameterEntry.COLUMN_VALUE + DBConstanst.TEXT_TYPE +");";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + ParameterEntry.TABLE_NAME;

    public static abstract class ParameterEntry implements BaseColumns{
        public static final String TABLE_NAME = "parameter";
        public static final String COLUMN_KEY = "keyP";
        public static final String COLUMN_VALUE = "valueP";
    }
}
