package com.dg.sigco.line.data

import android.database.Cursor
import com.dg.sigco.card.data.Line
import com.dg.sigco.db.shema.contract.LineContract
import java.io.Serializable

/**
 * Created by dualwer on 27/11/17.
 */
data class LineKt(val lineId:Int, val name:String) : Serializable{
    constructor(cursor:Cursor):this(
            cursor.getInt(cursor.getColumnIndex(LineContract.LineEntry.LINE_ID_COLUMN)),
            cursor.getString(cursor.getColumnIndex(LineContract.LineEntry.NAME_COLUMN))
    )
    constructor(line: Line):this(line.lineId, line.name)
}