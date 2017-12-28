package com.dg.sigco.detail.presenter;

import com.dg.sigco.card.data.Detail;
import com.dg.sigco.detail.repository.NewDetailRepository;
import com.dg.sigco.detail.view.INewDetailView;

public class NewDetailPresenter implements INewDetailPresenter{

    private INewDetailView iNewDetailView;
    private NewDetailRepository detailRepository;
    public NewDetailPresenter(final INewDetailView iNewDetailView){
        this.iNewDetailView = iNewDetailView;
        detailRepository = new NewDetailRepository(this);
    }

    @Override
    public void saveDetail(Detail detail) {
        detailRepository.registerDetail(detail);
    }

    @Override
    public void afterSave() {
        iNewDetailView.afterSave();
    }
}
