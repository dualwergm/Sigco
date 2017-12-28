package com.dg.sigco.card.presenter;

import com.dg.sigco.card.data.Line;

import java.util.List;

/**
 * Created by Sofia on 31/10/2017.
 */

public interface IDownloadPresenter {
    void downloadCard();
    void evaluateDownload(List<Line> lines);
    void afterSave(long amountDownloaded);
    void connectionError();
}
