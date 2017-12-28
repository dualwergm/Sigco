package com.dg.sigco.db.helper;

import android.database.Cursor;

import com.dg.sigco.card.data.Line;
import com.dg.sigco.db.shema.contract.LineContract;
import com.dg.sigco.db.shema.contract.ParameterContract;
import com.dg.sigco.parameter.data.Parameter;

/**
 * Created by Sofia on 20/11/2017.
 */

public class ParameterDBHelper {

    private static ParameterDBHelper parameterDBHelper;

    private DBHelperManager helperManager;
    private ParameterDBHelper(DBHelperManager helperManager){
        this.helperManager = helperManager;
    }

    public static ParameterDBHelper getInstance(DBHelperManager helperManager){
        if(parameterDBHelper == null){
            parameterDBHelper = new ParameterDBHelper(helperManager);
        }
        return parameterDBHelper;
    }

    public long save(Parameter parameter) {
        return helperManager.getWritableDatabase().insert(
                ParameterContract.ParameterEntry.TABLE_NAME,
                null,
                parameter.toContentValues());

    }

    public int delete(String keyP){
        return helperManager.getWritableDatabase().delete(ParameterContract.ParameterEntry.TABLE_NAME,
                "keyP = '"+keyP+"'",
                null);
    }

    public Cursor getParameter(String keyP) {
        String[] select = new String[]{
                ParameterContract.ParameterEntry._ID,
                ParameterContract.ParameterEntry.COLUMN_KEY,
                ParameterContract.ParameterEntry.COLUMN_VALUE
        };
        return helperManager.getReadableDatabase()
                .query(
                        ParameterContract.ParameterEntry.TABLE_NAME,
                        select,
                        "keyP = '"+keyP+"'",
                        null,
                        null,
                        null,
                        null);
    }
}
