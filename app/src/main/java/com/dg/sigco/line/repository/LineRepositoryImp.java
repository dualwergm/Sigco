package com.dg.sigco.line.repository;

import android.database.Cursor;
import android.os.AsyncTask;

import com.dg.sigco.card.data.Line;
import com.dg.sigco.card.presenter.IDownloadPresenter;
import com.dg.sigco.card.repository.CardRepositoryImp;
import com.dg.sigco.client.repository.ClientRepositoryImp;
import com.dg.sigco.db.helper.DBHelperManager;
import com.dg.sigco.db.helper.LineDBHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sofia on 31/10/2017.
 */

public class LineRepositoryImp {
    public static Map<Integer, String> savedMap = new HashMap<>();
    private LineDBHelper dbHelper;
    private CardRepositoryImp cardRepository;
    private IDownloadPresenter downloadPresenter;

    private static LineRepositoryImp lineRepositoryImp;

    public LineRepositoryImp(){
        dbHelper = LineDBHelper.getInstance(DBHelperManager.getInstance());
    }

    public static LineRepositoryImp getInstance(){
        if(lineRepositoryImp == null){
            lineRepositoryImp = new LineRepositoryImp();
        }
        return lineRepositoryImp;
    }

    public LineRepositoryImp(IDownloadPresenter downloadPresenter){
        dbHelper = LineDBHelper.getInstance(DBHelperManager.getInstance());
        this.downloadPresenter = downloadPresenter;
        cardRepository = new CardRepositoryImp(this.downloadPresenter);
    }

    public void saveList(final List<Line> lines){
        new SaveLineList(lines).execute();
    }

    private class SaveLineList extends AsyncTask<Void, Void, Long>{
        private List<Line> lines;
        public SaveLineList(final List<Line> lines){
            this.lines = lines;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            long amountCard = 0;
            savedMap = ClientRepositoryImp.getInstance().getClientsAsMap();
            for (Line line : lines) {
                amountCard += (line.getCards() == null ? 0 : line.getCards().size());
                dbHelper.save(line);
                cardRepository.saveList(line.getCards(), line.getLineId());
            }
            return amountCard;
        }

        @Override
        protected void onPostExecute(Long amountCards) {
            super.onPostExecute(amountCards);
            downloadPresenter.afterSave(amountCards);
        }
    }

    public Line getLine(int lineId){
        Cursor cursor = dbHelper.getLine(lineId);
        Line line = null;
        if(cursor.moveToNext()){
            line = new Line(cursor);
        }
        cursor.close();
        return line;
    }
}
