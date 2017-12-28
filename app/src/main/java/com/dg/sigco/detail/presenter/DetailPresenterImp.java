package com.dg.sigco.detail.presenter;

import com.dg.sigco.card.data.Detail;
import com.dg.sigco.card.repository.DetailRepositoryImp;
import com.dg.sigco.detail.view.IDetailView;

import java.util.List;

/**
 * Created by Sofia on 12/11/2017.
 */

public class DetailPresenterImp implements IDetailPresenter{
    private DetailRepositoryImp repositoryImp;
    private IDetailView iDetailView;
    public DetailPresenterImp(final IDetailView iDetailView){
        this.repositoryImp = new DetailRepositoryImp(this);
        this.iDetailView = iDetailView;
    }

    @Override
    public void getDetails(String slId) {
        repositoryImp.getDetails(slId);
    }

    @Override
    public void showDetails(List<Detail> details) {
        this.iDetailView.showDetails(details);
    }
}
