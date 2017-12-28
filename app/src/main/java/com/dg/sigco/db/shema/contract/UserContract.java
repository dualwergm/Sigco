package com.dg.sigco.db.shema.contract;

import android.provider.BaseColumns;

import com.dg.sigco.db.shema.DBConstanst;

/**
 * Created by Sofia on 31/10/2017.
 */

public final class UserContract {

    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ UserEntry.TABLE_NAME+" ("+
            UserEntry._ID + DBConstanst.PK_TYPE + DBConstanst.COMMA +
            UserEntry.NAME_COLUMN+ DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            UserEntry.USER_COLUMN + DBConstanst.TEXT_UNIQUE_TYPE + DBConstanst.COMMA +
            UserEntry.PASSWORD_COLUMN + DBConstanst.TEXT_TYPE + DBConstanst.COMMA +
            UserEntry.ROL_COLUMN + DBConstanst.INTEGER_TYPE + DBConstanst.COMMA +
            UserEntry.ACTIVE_COLUMN + DBConstanst.INTEGER_TYPE + ");";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "userc";
        public static final String NAME_COLUMN = "name";
        public static final String USER_COLUMN = "userc";
        public static final String PASSWORD_COLUMN = "password";
        public static final String ROL_COLUMN = "rol"; //1-Admin 0-Cobrador
        public static final String ACTIVE_COLUMN = "active"; //1-si 0-no
    }
}
