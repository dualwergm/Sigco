package com.dg.sigco.card.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.dg.sigco.R
import com.dg.sigco.card.data.Card
import com.dg.sigco.card.data.CardKt
import com.dg.sigco.card.presenter.NewCardPresenter
import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.common.*
import com.dg.sigco.line.data.LineKt
import kotlinx.android.synthetic.main.activity_new_card.*
import org.jetbrains.anko.toast

class NewCardActivity : AppCompatActivity() {
    private var selectedDate = UtilDate.getCurrentDateShort()
    private var line:LineKt? = null
    private var client:ClientKt? = null
    private var cardKt:CardKt? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)
        cardKt = intent.getSerializableExtra(Constants.CARD) as CardKt?
        loadParams()
        clientName.text = client?.name
        clientAlias.text = client?.showAlias()
        lineName.text = line?.name
        cardDate.setOnClickListener { showDatePickerDialog() }
        saveCard.setOnClickListener {
            if(validate()){
                NewCardPresenter(this).save(getCard())
            }
        }
        initializeFormatMoney()
    }

    private fun loadParams(){
        when {
            cardKt != null -> {
                client = cardKt?.clientKt
                line = cardKt?.lineKt
                selectedDate = cardKt?.dateStr
                cardValue.setText(cardKt?.value.toString())
                cardQuota.setText(cardKt?.quota.toString())
                products.setText(cardKt?.products)
                address.setText(cardKt?.address)
                phone.setText(cardKt?.phone)
            }
            else -> {
                client = intent.getSerializableExtra(Constants.CLIENT) as ClientKt
                line = intent.getSerializableExtra(Constants.LINE) as LineKt
                address.setText(client?.address)
                phone.setText(client?.phone)
            }
        }
        cardDate.setText(selectedDate)
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance { datePicker, year, month, day ->
            selectedDate = year.toString() + "-" + UtilDate.fillNumber(month + 1) + "-" + UtilDate.fillNumber(day)
            cardDate.setText(UtilDate.getDateES(selectedDate, UtilDate._DATE_FORMAT_SHORT))
        }
        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun getCard():Card{
        val card = Card()
        card._id = cardKt?.id
        when (cardKt) {
            null -> card.cardId = 0
            else -> card.cardId = cardKt?.cardId
        }
        card.dateStr = selectedDate
        card.value = cardValue.text.toString().removeComma().toDouble()
        card.quota = cardQuota.text.toString().removeComma().toDouble()
        card.address = address.text.toString()
        card.phone = phone.text.toString()
        card.products = products.text.toString()
        card.lineId = line?.lineId
        card.clientId = client?.clientId
        card.clientSLId = client?.id
        val initDaySelected = UtilDate.getTimestampInitDay(selectedDate)
        when {
            initDaySelected.after(UtilDate.getCurrentDateInitTimestamp()) -> {
                card.status = 1
                card.todaytoStr = UtilDate.getDateShort(UtilDate.addDays(initDaySelected, -1))
            }
            else -> card.status = 2
        }
        return card
    }

    private fun validate():Boolean = when {
        cardValue.text.toString().isEmpty() ->{
            toast("Ingresa el valor total de la tarjeta.")
            false
        }
        cardQuota.text.toString().isEmpty() -> {
            toast("Ingresa el valor de la quota.")
            false
        }
        address.text.toString().isEmpty() -> {
            toast("Ingresa la dirección del cliente.")
            false
        }
        phone.text.toString().isEmpty() -> {
            toast("Ingresa el teléfono del cliente.")
            false
        }
        products.text.toString().isEmpty() -> {
            toast("Ingresa los productos a cobrar en la tarjeta.")
            false
        }
        else -> true
    }

    private fun initializeFormatMoney(){
        cardValue.text.formatMoney()
        cardValue.addTextChangedListener(object : TextWatcher {
            internal var isEditing: Boolean = false
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (isEditing) return
                isEditing = true
                s.formatMoney()
                isEditing = false
            }
        })
        cardQuota.text.formatMoney()
        cardQuota.addTextChangedListener(object : TextWatcher {
            internal var isEditing: Boolean = false
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (isEditing) return
                isEditing = true
                s.formatMoney()
                isEditing = false
            }
        })
    }
}