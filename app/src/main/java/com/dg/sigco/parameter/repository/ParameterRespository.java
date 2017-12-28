package com.dg.sigco.parameter.repository;

import android.database.Cursor;

import com.dg.sigco.common.Constants;
import com.dg.sigco.db.helper.DBHelperManager;
import com.dg.sigco.db.helper.ParameterDBHelper;
import com.dg.sigco.db.shema.contract.ParameterContract;
import com.dg.sigco.parameter.data.Parameter;

public class ParameterRespository {

    private static ParameterRespository parameterRespository;

    private ParameterDBHelper dbHelper;
    private ParameterRespository(){
        dbHelper = ParameterDBHelper.getInstance(DBHelperManager.getInstance());
    }

    public static ParameterRespository getInstance(){
        if(parameterRespository == null){
            parameterRespository = new ParameterRespository();
        }
        return parameterRespository;
    }

    public long saveServer(final Parameter parameter){
        dbHelper.delete(Constants.KEY_SERVER_PARAMETER);
        return save(parameter);
    }

    public long save(final Parameter parameter){
        return dbHelper.save(parameter);
    }

    public String getServer(){
        return getParamValue(Constants.KEY_SERVER_PARAMETER);
    }

    public String getParamValue(String keyP){
        final Cursor cursor = dbHelper.getParameter(keyP);
        String valueP = "";
        if(cursor.moveToNext()){
            valueP = cursor.getString(cursor.getColumnIndex(ParameterContract.ParameterEntry.COLUMN_VALUE));
        }
        cursor.close();
        return valueP;
    }

    public int delete(String keyP){
        return dbHelper.delete(keyP);
    }
}
