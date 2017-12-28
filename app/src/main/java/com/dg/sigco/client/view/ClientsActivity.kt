package com.dg.sigco.client.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.dg.sigco.R
import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.client.presenter.ClientsPresenter
import com.dg.sigco.common.Constants
import kotlinx.android.synthetic.main.activity_clients.*
import org.jetbrains.anko.startActivity

class ClientsActivity : AppCompatActivity(), IClientsView {
    private var copyClients = ArrayList<ClientKt>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)
        close.setOnClickListener { finish() }
        clientsRecycler.layoutManager = LinearLayoutManager(this)
        ClientsPresenter(this).getClients()

        searchClient.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(q: String): Boolean {
                val clientsAdapter = clientsRecycler.adapter as ClientsAdapter
                clientsAdapter.filter(copyClients,q)
                return true
            }
            override fun onQueryTextSubmit(q: String): Boolean = true
        })
        addClient.setOnClickListener {
            startActivity<NewClientActivity>()
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(broadCastReceiver, IntentFilter(Constants.FINISH_AFTER_CREATE_CARD))
    }

    override fun showClients(clients: MutableList<ClientKt>) {
        copyClients.addAll(clients)
        clientsRecycler.adapter = ClientsAdapter(clients)
    }

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if (action == Constants.FINISH_AFTER_CREATE_CARD) {
                finish()
            }
        }
    }
}