package com.dg.sigco.client.data

import android.content.ContentValues
import android.database.Cursor
import com.dg.sigco.card.data.Client
import com.dg.sigco.common.removeAccents
import com.dg.sigco.db.shema.contract.ClientContract
import java.io.Serializable

/**
 * Created by dualwer on 24/11/17.
 */
data class ClientKt(val id:String="", val clientId:Int=0, val name:String, val alias:String, val address:String, val phone:String) : Serializable{
    constructor(cursor:Cursor):this(
            cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry._ID)),
            cursor.getInt(cursor.getColumnIndex(ClientContract.ClientEntry.CLIENT_ID_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry.NAME_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry.ALIAS_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry.ADDRESS_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry.PHONE_COLUMN))
    )

    constructor(client: Client):this(
            client._id,
            client.clientId,
            client.name,
            client.alias,
            client.address,
            client.phone
    )

    fun filterStr():String{
        return "$name $alias $address $phone".toLowerCase().removeAccents()
    }

    fun showAlias():String{
        return when {
            alias.isBlank() -> ""
            else -> " - $alias"
        }
    }

    fun toContentValues():ContentValues{
        return ContentValues().apply {
            put(ClientContract.ClientEntry.CLIENT_ID_COLUMN, 0)
            put(ClientContract.ClientEntry.NAME_COLUMN, name)
            put(ClientContract.ClientEntry.ALIAS_COLUMN, alias)
            put(ClientContract.ClientEntry.ADDRESS_COLUMN, address)
            put(ClientContract.ClientEntry.PHONE_COLUMN, phone)
        }
    }
}