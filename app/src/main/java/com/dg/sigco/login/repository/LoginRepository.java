package com.dg.sigco.login.repository;

import android.database.Cursor;
import android.os.AsyncTask;

import com.dg.sigco.db.helper.DBHelperManager;
import com.dg.sigco.db.helper.UserDBHelper;
import com.dg.sigco.login.data.Userc;
import com.dg.sigco.login.presenter.ILoginPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dualwer on 17/11/17.
 */

public class LoginRepository {

    private UserDBHelper dbHelper;
    private ILoginPresenter iLoginPresenter;
    public LoginRepository(final ILoginPresenter iLoginPresenter){
        dbHelper = UserDBHelper.getInstance(DBHelperManager.getInstance());
        this.iLoginPresenter = iLoginPresenter;
    }

    public void validateUser(String user, String password){
        new ValidateUser(user, password).execute();
    }

    private class ValidateUser extends AsyncTask<Void, Void, Cursor> {
        private String user;
        private String password;
        public ValidateUser(String user, String password){
            this.user = user;
            this.password = password;
        }

        @Override
        protected Cursor doInBackground(Void... params) {
            return dbHelper.getUser(user, password);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            Userc u = null;
            if(cursor.moveToNext()){
                 u = new Userc(cursor);
            }
            cursor.close();
            if(u != null){
                iLoginPresenter.loginSucces(u);
            }else{
                iLoginPresenter.loginError();
            }
        }
    }

}
