package com.dg.sigco.parameter.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.dg.sigco.db.shema.contract.LineContract;
import com.dg.sigco.db.shema.contract.ParameterContract;

import java.io.Serializable;

/**
 * Created by Sofia on 20/11/2017.
 */

public class Parameter implements Serializable{
    private String _id;
    private String keyP;
    private String valueP;

    public Parameter(String keyP, String valueP){
        this.keyP = keyP;
        this.valueP = valueP;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getKeyP() {
        return keyP;
    }

    public void setKeyP(String keyP) {
        this.keyP = keyP;
    }

    public String getValueP() {
        return valueP;
    }

    public void setValueP(String valueP) {
        this.valueP = valueP;
    }

    public Parameter(Cursor cursor){
        this._id = cursor.getString(cursor.getColumnIndex(ParameterContract.ParameterEntry._ID));
        this.keyP = cursor.getString(cursor.getColumnIndex(ParameterContract.ParameterEntry.COLUMN_KEY));
        this.valueP = cursor.getString(cursor.getColumnIndex(ParameterContract.ParameterEntry.COLUMN_VALUE));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ParameterContract.ParameterEntry.COLUMN_KEY, getKeyP());
        values.put(ParameterContract.ParameterEntry.COLUMN_VALUE, getValueP());
        return values;
    }
}
