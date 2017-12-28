package com.dg.sigco.client.repository

import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.client.presenter.INewClientPresenter
import com.dg.sigco.db.helper.ClientDBHelper
import com.dg.sigco.db.helper.DBHelperManager

/**
 * Created by Sofia on 28/11/2017.
 */
class NewClientRepositoryKt(val iNewClientPresenter: INewClientPresenter) {

    private val clientDBHelper = ClientDBHelper.getInstance(DBHelperManager.getInstance())

    fun saveClient(clientKt: ClientKt){
        clientDBHelper.save(clientKt)
        iNewClientPresenter.afterSave()
    }
}