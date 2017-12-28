package com.dg.sigco.card.presenter

import com.dg.sigco.card.interactor.UploadInteractor
import com.dg.sigco.card.view.fragment.IUploadView

/**
 * Created by Sofia on 14/12/2017.
 */
class UploadPresenter(val iUploadView: IUploadView) : IUploadPresenter{
    override fun uploadCards() {
        UploadInteractor(this).uploadCards()
    }

    override fun errorConection() {
        iUploadView.errorConnection()
    }

    override fun afterUpload() {
        iUploadView.afterUpload()
    }
}