package com.dg.sigco.detail.presenter;

import com.dg.sigco.card.data.Detail;

public interface INewDetailPresenter {
    void saveDetail(Detail detail);
    void afterSave();
}