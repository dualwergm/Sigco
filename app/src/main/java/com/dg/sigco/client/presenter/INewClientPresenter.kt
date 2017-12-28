package com.dg.sigco.client.presenter

import com.dg.sigco.client.data.ClientKt

/**
 * Created by Sofia on 27/11/2017.
 */
interface INewClientPresenter {
    fun save(clientTk:ClientKt)
    fun afterSave()
}