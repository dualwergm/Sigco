package com.dg.sigco.client.view

import com.dg.sigco.client.data.ClientKt

/**
 * Created by Sofia on 24/11/2017.
 */
interface IClientsView {
    fun showClients(clients:MutableList<ClientKt>)
}