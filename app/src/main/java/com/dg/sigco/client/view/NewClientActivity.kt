package com.dg.sigco.client.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dg.sigco.R
import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.client.presenter.NewClientPresenter
import kotlinx.android.synthetic.main.activity_new_client.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class NewClientActivity : AppCompatActivity(), INewClientView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_client)
        saveClient.setOnClickListener {
            if(validate()){
                NewClientPresenter(this).save(getClientFromView())
            }
        }
    }

    override fun afterSave() {
        startActivity<ClientsActivity>()
        finish()
    }

    private fun validate():Boolean = when {
        name.text.toString().isEmpty() -> {
            toast("Ingresa el nombre del cliente.")
            false
        }
        address.text.toString().isEmpty() -> {
            toast("Ingresa una dirección  para el cliente.")
            false
        }
        else -> true
    }

    private fun getClientFromView():ClientKt{
        return ClientKt(
                name = name.text.toString(),
                alias = alias.text.toString(),
                address = address.text.toString(),
                phone = phone.text.toString()
        )
    }
}