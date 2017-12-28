package com.dg.sigco.card.view.fragment;

/**
 * Created by Sofia on 08/11/2017.
 */

public interface IDownloadView {
    void showDownloadSuccess(long amountDownloaded);
    void showWithoutCards();
    void connectionError();
}
