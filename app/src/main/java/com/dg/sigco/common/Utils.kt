package com.dg.sigco.common

import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.DecimalFormat
import java.text.Normalizer
import java.text.NumberFormat
import java.util.*


/**
 * Created by dualwer on 23/11/17.
 */

fun ViewGroup.inflate(layoutId:Int):View{
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun CharSequence.removeAccents(): String {
    var d = Normalizer.normalize(this, Normalizer.Form.NFD)
    return d.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
}

fun Editable.formatMoney() {
    val indexOf = this.toString().indexOf(".")
    var str = this.toString().replace("[^\\d]".toRegex(), "")
    when {
        indexOf > 0 -> str = str.substring(0, indexOf)
    }
    if (TextUtils.isEmpty(str)) return
    val s1 = java.lang.Double.parseDouble(str)
    val nf2 = NumberFormat.getInstance(Locale.ENGLISH)
    (nf2 as DecimalFormat).applyPattern("###,###.###")
    this.replace(0, this.length, nf2.format(s1))
}

fun String.removeComma():String = this.replace(",","")