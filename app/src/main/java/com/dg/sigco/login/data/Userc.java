package com.dg.sigco.login.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.dg.sigco.db.shema.contract.DetailContract;
import com.dg.sigco.db.shema.contract.UserContract;

import java.io.Serializable;
import java.util.Date;

public class Userc implements Serializable{

    private String _id;
    private String name;
    private String userc;
    private String password;
    private Integer rol;
    private Integer active;

    public Userc(String name, String userc, String password, Integer rol) {
        this.name = name;
        this.userc = userc;
        this.password = password;
        this.rol = rol;
    }

    public Userc() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserc() {
        return userc;
    }

    public void setUserc(String userc) {
        this.userc = userc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public boolean isAdmin(){
        return getRol() != null && getRol().intValue() == 1;
    }

    public int newStatus(){
        if(getActive() == null){
            return 1;
        }
        return 1 - getActive().intValue();
    }

    public boolean isActiveUser(){
        return getActive() != null && getActive().intValue() == 1;
    }

    public Userc(Cursor cursor){
        this._id =  cursor.getString(cursor.getColumnIndex(UserContract.UserEntry._ID));
        this.name =  cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.NAME_COLUMN));
        this.userc = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.USER_COLUMN));
        this.password =  cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.PASSWORD_COLUMN));
        this.rol =  cursor.getInt(cursor.getColumnIndex(UserContract.UserEntry.ROL_COLUMN));
        this.active = cursor.getInt(cursor.getColumnIndex(UserContract.UserEntry.ACTIVE_COLUMN));
    }

    private ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.NAME_COLUMN, getName());
        values.put(UserContract.UserEntry.USER_COLUMN, getUserc());
        values.put(UserContract.UserEntry.PASSWORD_COLUMN, getPassword());
        values.put(UserContract.UserEntry.ROL_COLUMN, getRol());
        return values;
    }

    public ContentValues toContentValues() {
        final ContentValues contentValues = getContentValues();
        contentValues.put(UserContract.UserEntry.ACTIVE_COLUMN, Integer.valueOf(1));
        return contentValues;
    }

    public ContentValues toUpdateValues() {
        return getContentValues();
    }
}