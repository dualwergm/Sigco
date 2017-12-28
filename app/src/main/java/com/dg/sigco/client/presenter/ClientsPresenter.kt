package com.dg.sigco.client.presenter

import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.client.repository.ClientRepositoryKt
import com.dg.sigco.client.view.IClientsView

/**
 * Created by Sofia on 25/11/2017.
 */
class ClientsPresenter(private val iClientsView: IClientsView) : IClientsPresenter{
    private val clientRepository = ClientRepositoryKt(this)

    override fun getClients() {
        clientRepository.getClients()
    }

    override fun showClients(clients: MutableList<ClientKt>) {
        iClientsView.showClients(clients)
    }

}