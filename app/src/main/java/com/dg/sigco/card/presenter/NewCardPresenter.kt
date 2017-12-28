package com.dg.sigco.card.presenter

import android.app.Activity
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import com.dg.sigco.card.data.Card
import com.dg.sigco.card.repository.NewCardRepository
import com.dg.sigco.card.view.CardsContainerActivity
import com.dg.sigco.common.Constants
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by Sofia on 02/12/2017.
 */
class NewCardPresenter(val activity: Activity) : INewCardPresenter{
    override fun save(card: Card) {
        NewCardRepository(this).saveCard(card)
    }

    override fun afterSave(saved:Boolean) {
        if (saved) {
            activity.startActivity<CardsContainerActivity>()
            activity.finish()
            activity.toast("La terjeta se registró exitosamente.")
            LocalBroadcastManager.getInstance(activity).sendBroadcast(Intent(Constants.FINISH_AFTER_CREATE_CARD))
        }else{
            activity.longToast("No se pudo registrar la tarjeta. Comuníquelo al administrador.")
        }
    }
}