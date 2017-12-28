package com.dg.sigco.card.data

import android.database.Cursor
import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.db.shema.contract.CardContract
import com.dg.sigco.line.data.LineKt
import java.io.Serializable

/**
 * Created by Sofia on 11/12/2017.
 */
class CardKt(val id:String, val cardId:Int, val value:Double, val quota:Double, val address:String,
             val phone:String, val products:String, val dateStr:String, val toDayto:String?, val lineKt: LineKt?, val clientKt: ClientKt?) : Serializable {

    constructor(card: Card):this(
            card._id,
            card.cardId,
            card.value,
            card.quota,
            card.address,
            card.phone,
            card.products,
            card.dateStr,
            card.todaytoStr,
            LineKt(card.line),
            ClientKt(card.client)
    )

    constructor(cursor: Cursor):this(
            cursor.getString(cursor.getColumnIndex(CardContract.CardEntry._ID)),
            cursor.getInt(cursor.getColumnIndex(CardContract.CardEntry.CARD_ID_COLUMN)),
            cursor.getDouble(cursor.getColumnIndex(CardContract.CardEntry.VALUEC_COLUMN)),
            cursor.getDouble(cursor.getColumnIndex(CardContract.CardEntry.QUOTA_COLUMN)),
            cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.ADDRESS_COLUMN)),
            cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.PHONE_COLUMN)),
            cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.PRODUCTS_COLUMN)),
            cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.DATEC_COLUMN)),
            cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.TODAYTO_COLUMN)),
            null,
            null
    )
}