package com.dg.sigco.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dg.sigco.db.shema.contract.CardContract;
import com.dg.sigco.db.shema.contract.ClientContract;
import com.dg.sigco.db.shema.contract.DetailContract;
import com.dg.sigco.db.shema.contract.LineContract;
import com.dg.sigco.db.shema.contract.ParameterContract;
import com.dg.sigco.db.shema.contract.SigcoDBContract;
import com.dg.sigco.db.shema.contract.UserContract;

/**
 * Created by Sofia on 31/10/2017.
 */

public class DBHelperManager extends SQLiteOpenHelper {
    private static DBHelperManager dbHelper;

    private DBHelperManager(Context context){
        super(context, SigcoDBContract.DATABASE_NAME, null, SigcoDBContract.DATABASE_VERSION);
    }

    /**
     * Al iniciar la aplicación se instancia por lo que siempre debe estar activa la instancia
     * solo para abrir o cerrar
     * @return
     */
    public static DBHelperManager getInstance(){
        return dbHelper;
    }

    public static DBHelperManager getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelperManager(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
           //db.execSQL(SigcoDBContract.CREATE_DATABASE);
            db.execSQL(UserContract.SQL_CREATE_TABLE);
            db.execSQL(ParameterContract.SQL_CREATE_TABLE);
            db.execSQL(LineContract.SQL_CREATE_TABLE);
            db.execSQL(ClientContract.SQL_CREATE_TABLE);
            db.execSQL(CardContract.SQL_CREATE_TABLE);
            db.execSQL(DetailContract.SQL_CREATE_TABLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SigcoDBContract.DROP_DATABASE);
        onCreate(db);
    }
}
