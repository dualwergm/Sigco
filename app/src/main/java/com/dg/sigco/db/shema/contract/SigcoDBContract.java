package com.dg.sigco.db.shema.contract;

/**
 * Created by Sofia on 31/10/2017.
 */

public final class SigcoDBContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Sigco.db";


    public static final String CREATE_DATABASE =
            ParameterContract.SQL_CREATE_TABLE +
                    LineContract.SQL_CREATE_TABLE +
                    ClientContract.SQL_CREATE_TABLE +
                    CardContract.SQL_CREATE_TABLE +
                    DetailContract.SQL_CREATE_TABLE ;

    public static final String DROP_DATABASE =
            DetailContract.SQL_DROP_TABLE +
            CardContract.SQL_DROP_TABLE +
            LineContract.SQL_DROP_TABLE +
            ClientContract.SQL_DROP_TABLE +
            ParameterContract.SQL_DROP_TABLE;
}
