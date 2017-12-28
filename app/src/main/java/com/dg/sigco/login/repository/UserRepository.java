package com.dg.sigco.login.repository;

import android.database.Cursor;
import android.os.AsyncTask;

import com.dg.sigco.db.helper.DBHelperManager;
import com.dg.sigco.db.helper.UserDBHelper;
import com.dg.sigco.login.data.Userc;
import com.dg.sigco.login.presenter.ICreateUserPresenter;
import com.dg.sigco.login.presenter.IUserPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dualwer on 14/11/17.
 */

public class UserRepository {

    private IUserPresenter iUserPresenter;
    private UserDBHelper dbHelper;
    public UserRepository(final IUserPresenter iUserPresenter){
        this.iUserPresenter = iUserPresenter;
        dbHelper = UserDBHelper.getInstance(DBHelperManager.getInstance());
    }

    private ICreateUserPresenter iCreateUserPresenter;
    public UserRepository(final ICreateUserPresenter iCreateUserPresenter){
        this.iCreateUserPresenter = iCreateUserPresenter;
        dbHelper = UserDBHelper.getInstance(DBHelperManager.getInstance());
    }

    public UserRepository(){
        dbHelper = UserDBHelper.getInstance(DBHelperManager.getInstance());
    }

    public void save(Userc userc){
        dbHelper.save(userc);
        iCreateUserPresenter.afterSave();
    }

    public void update(Userc userc){
        dbHelper.update(userc);
        iCreateUserPresenter.afterSave();
    }

    public void activeUser(String userId, int status){
        dbHelper.activeUser(userId, status);
    }

    public void saveAdmin(){
        dbHelper.save(new Userc("Administrador", "admin", "1234", Integer.valueOf(1)));
    }

    public boolean hasUsers(){
        return dbHelper.hasUsers();
    }

    public void listUsers(){
        new ListUsers().execute();
    }

    private class ListUsers extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... params) {
            return dbHelper.getUsers();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            List<Userc> users = new ArrayList<>();
            while(cursor.moveToNext()){
                users.add(new Userc(cursor));
            }
            cursor.close();
            iUserPresenter.showUsers(users);
        }
    }
}
