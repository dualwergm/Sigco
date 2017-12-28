package com.dg.sigco.client.repository

import android.database.Cursor
import android.os.AsyncTask
import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.client.presenter.IClientsPresenter
import com.dg.sigco.client.presenter.INewClientPresenter
import com.dg.sigco.db.helper.ClientDBHelper
import com.dg.sigco.db.helper.DBHelperManager

/**
 * Created by Sofia on 24/11/2017.
 */
class ClientRepositoryKt (val iClientsPresenter: IClientsPresenter){
    private val clientDBHelper = ClientDBHelper.getInstance(DBHelperManager.getInstance())

    fun getClients(){
        GetAllClients().execute()
    }

    internal inner class GetAllClients : AsyncTask<Void, Void, Cursor>() {
        override fun doInBackground(vararg params: Void?): Cursor = clientDBHelper.getClients()

        override fun onPostExecute(cursor: Cursor?) {
            val clients = ArrayList<ClientKt>()
            while (cursor?.moveToNext()!!){
                clients.add(ClientKt(cursor))
            }
            cursor?.close()
            iClientsPresenter.showClients(clients)
        }
    }

}