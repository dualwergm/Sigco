package com.dg.sigco.db.helper;

import android.database.Cursor;

import com.dg.sigco.card.data.Detail;
import com.dg.sigco.db.shema.contract.DetailContract;
import com.dg.sigco.db.shema.contract.UserContract;
import com.dg.sigco.login.data.Userc;

/**
 * Created by Sofia on 31/10/2017.
 */

public class UserDBHelper {

    private static UserDBHelper userHelper;

    private DBHelperManager helperManager;
    private UserDBHelper(DBHelperManager helperManager){
        this.helperManager = helperManager;
    }

    public static UserDBHelper getInstance(DBHelperManager helperManager){
        if(userHelper == null){
            userHelper = new UserDBHelper(helperManager);
        }
        return userHelper;
    }

    public long save(Userc userc) {
        return helperManager.getWritableDatabase().insert(
                UserContract.UserEntry.TABLE_NAME,
                null,
                userc.toContentValues());
    }

    public long update(Userc userc) {
        return helperManager.getWritableDatabase().update(
                UserContract.UserEntry.TABLE_NAME,
                userc.toUpdateValues(),
                UserContract.UserEntry._ID + " = ? ",
                new String[] {userc.get_id()});
    }

    public Cursor getUsers() {
        String[] select = new String[]{
                UserContract.UserEntry._ID,
                UserContract.UserEntry.NAME_COLUMN,
                UserContract.UserEntry.USER_COLUMN,
                UserContract.UserEntry.PASSWORD_COLUMN,
                UserContract.UserEntry.ROL_COLUMN,
                UserContract.UserEntry.ACTIVE_COLUMN
        };
        return helperManager.getReadableDatabase()
                .query(
                        UserContract.UserEntry.TABLE_NAME,
                        select,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getUser(String user, String password) {
        String[] select = new String[]{
                UserContract.UserEntry._ID,
                UserContract.UserEntry.NAME_COLUMN,
                UserContract.UserEntry.USER_COLUMN,
                UserContract.UserEntry.PASSWORD_COLUMN,
                UserContract.UserEntry.ROL_COLUMN,
                UserContract.UserEntry.ACTIVE_COLUMN
        };
        return helperManager.getReadableDatabase()
                .query(
                        UserContract.UserEntry.TABLE_NAME,
                        select,
                        " userc = '"+user+"' and password = '"+password+"' and active = 1",
                        null,
                        null,
                        null,
                        null);
    }

    public boolean hasUsers() {
        String sql = "SELECT count(*) FROM "+UserContract.UserEntry.TABLE_NAME;
        Cursor cursor = helperManager.getReadableDatabase().rawQuery(sql, null);
        return getAmounUser(cursor) > 0;
    }

    public void activeUser(String userId, int status) {
        String sql = "Update userc set active = "+status+" WHERE _id = '"+userId+"' ";
        helperManager.getWritableDatabase().execSQL(sql);
    }

    private int getAmounUser(Cursor cursor){
        if(cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        return 0;
    }
}