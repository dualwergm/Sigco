package com.dg.sigco.detail.repository;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.dg.sigco.card.data.Detail;
import com.dg.sigco.card.interactor.CardInteractor;
import com.dg.sigco.db.helper.DBHelperManager;
import com.dg.sigco.db.helper.DetailDBHelper;
import com.dg.sigco.detail.presenter.INewDetailPresenter;

public class NewDetailRepository {

    private INewDetailPresenter iNewDetailPresenter;
    private DetailDBHelper dbHelper;
    public NewDetailRepository(final INewDetailPresenter iNewDetailPresenter){
        this.iNewDetailPresenter = iNewDetailPresenter;
        dbHelper = DetailDBHelper.getInstance(DBHelperManager.getInstance());
    }

    public void registerDetail(Detail detail){
        new RegisterDetail(detail).execute();
    }

    private class RegisterDetail extends AsyncTask<Void, Void, Long>{
        private Detail detail;
        private CardInteractor cardInteractor;
        public RegisterDetail(final Detail detail){
            this.detail = detail;
            cardInteractor = new CardInteractor();
        }

        @Override
        protected Long doInBackground(Void... voids) {
            long r = -1;
            if(TextUtils.isEmpty(detail.get_id())){
                r = dbHelper.save(detail);
            }else{
                r = dbHelper.update(detail);
            }
            cardInteractor.updateTodayToCard(detail.getCardId());
            return r;
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            iNewDetailPresenter.afterSave();
        }
    }
}