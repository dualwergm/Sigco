package com.dg.sigco.line.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.dg.sigco.R
import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.common.Constants
import com.dg.sigco.line.data.LineKt
import com.dg.sigco.line.presenter.LinePresenter
import kotlinx.android.synthetic.main.activity_line.*

class LineActivity : AppCompatActivity(), ILineView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line)
        close.setOnClickListener { finish() }
        lineReycler.layoutManager = LinearLayoutManager(this)
        LinePresenter(this).getLines()
        LocalBroadcastManager.getInstance(this).registerReceiver(broadCastReceiver, IntentFilter(Constants.FINISH_AFTER_CREATE_CARD))
    }

    override fun showLines(lines: List<LineKt>) {
        val client = intent.extras.getSerializable(Constants.CLIENT) as ClientKt
        lineReycler.adapter = LineAdapter(lines, client)
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