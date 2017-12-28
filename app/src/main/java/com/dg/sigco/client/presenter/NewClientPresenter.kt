package com.dg.sigco.client.presenter

import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.client.repository.NewClientRepositoryKt
import com.dg.sigco.client.view.INewClientView

/**
 * Created by Sofia on 27/11/2017.
 */
class NewClientPresenter(private val iNewClientView: INewClientView) : INewClientPresenter{
    override fun save(clientTk: ClientKt) {
        NewClientRepositoryKt(this).saveClient(clientTk)
    }

    override fun afterSave() {
        iNewClientView.afterSave()
    }
}