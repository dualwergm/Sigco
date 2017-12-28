package com.dg.sigco.card.repository;

import android.database.Cursor;
import android.os.AsyncTask;

import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.data.Detail;
import com.dg.sigco.db.helper.DBHelperManager;
import com.dg.sigco.db.helper.DetailDBHelper;
import com.dg.sigco.detail.presenter.IDetailPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sofia on 01/11/2017.
 */

public class DetailRepositoryImp {

    private static DetailRepositoryImp repositoryImp;

    private DetailDBHelper dbHelper;
    private DetailRepositoryImp(){
        this.dbHelper = DetailDBHelper.getInstance(DBHelperManager.getInstance());
    }

    private IDetailPresenter detailPresenter;
    public DetailRepositoryImp(IDetailPresenter detailPresenter){
        this.dbHelper = DetailDBHelper.getInstance(DBHelperManager.getInstance());
        this.detailPresenter = detailPresenter;
    }

    public static DetailRepositoryImp getInstance(){
        if(repositoryImp == null){
            repositoryImp = new DetailRepositoryImp();
        }
        return repositoryImp;
    }

    public void saveList(List<Detail> details, String cardSLId){
        if(details == null || details.isEmpty()){
            return;
        }
        for(Detail detail: details){
            detail.setCardSLId(cardSLId);
            dbHelper.save(detail);
        }
    }

    public double getPayed(int cardId){
        return dbHelper.getPayed(cardId);
    }

    public void getDetails(String slId){
        new ListdDetails(slId).execute();
    }

    private class ListdDetails extends AsyncTask<Void, Void, Cursor> {
        private String slId;
        public ListdDetails(String slId){
            this.slId = slId;
        }

        @Override
        protected Cursor doInBackground(Void... params) {
            return dbHelper.getDetails(slId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            List<Detail> details = new ArrayList<>();
            while(cursor.moveToNext()){
                details.add(new Detail(cursor));
            }
            cursor.close();
            detailPresenter.showDetails(details);
        }
    }

    public List<Detail> getNewDetails(String cardSLId){
        Cursor cursor = dbHelper.getDetails(cardSLId);
        return getDetails(cursor);
    }

    public List<Detail> getNewsDetailOldCards(){
        Cursor cursor = dbHelper.getNewDetailsOldCards();
        return getDetails(cursor);
    }

    private List<Detail> getDetails(Cursor cursor){
        List<Detail> details = new ArrayList<>();
        while(cursor.moveToNext()){
            details.add(new Detail(cursor));
        }
        cursor.close();
        return details;
    }
}