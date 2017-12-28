package com.dg.sigco.line.presenter

import com.dg.sigco.line.data.LineKt

/**
 * Created by dualwer on 27/11/17.
 */
interface ILinePresenter {
    fun getLines()
    fun showLines(lines:List<LineKt>)
}