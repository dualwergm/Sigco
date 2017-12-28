package com.dg.sigco.client.presenter

import com.dg.sigco.client.data.ClientKt

/**
 * Created by Sofia on 24/11/2017.
 */
interface IClientsPresenter {
    fun getClients()
    fun showClients(clients:MutableList<ClientKt>)
}
