package com.dg.sigco.detail.presenter;

import com.dg.sigco.card.data.Detail;

import java.util.List;

/**
 * Created by Sofia on 12/11/2017.
 */

public interface IDetailPresenter {
    void getDetails(String slId);
    void showDetails(List<Detail> details);
}
