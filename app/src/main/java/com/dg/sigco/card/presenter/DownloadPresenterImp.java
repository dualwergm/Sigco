package com.dg.sigco.card.presenter;

import com.dg.sigco.card.data.Line;
import com.dg.sigco.card.repository.CardRepositoryImp;
import com.dg.sigco.card.view.fragment.IDownloadView;
import com.dg.sigco.line.repository.LineRepositoryImp;

import java.util.List;

/**
 * Created by Sofia on 31/10/2017.
 */

public class DownloadPresenterImp implements IDownloadPresenter {
    private LineRepositoryImp lineRepositoryImp;
    private CardRepositoryImp cardRepositoryImp;
    private IDownloadView downloadView;
    public DownloadPresenterImp(IDownloadView downloadView){
        lineRepositoryImp = new LineRepositoryImp(this);
        cardRepositoryImp = new CardRepositoryImp(this);
        this.downloadView = downloadView;
    }

    @Override
    public void downloadCard() {
        cardRepositoryImp.downloadCards();
    }

    @Override
    public void evaluateDownload(List<Line> lines) {
        if(lines == null || lines.isEmpty()){
            downloadView.showWithoutCards();
            return;
        }
        lineRepositoryImp.saveList(lines);
    }

    @Override
    public void afterSave(long amountDownloaded) {
        downloadView.showDownloadSuccess(amountDownloaded);
    }

    @Override
    public void connectionError() {
        downloadView.connectionError();
    }
}
