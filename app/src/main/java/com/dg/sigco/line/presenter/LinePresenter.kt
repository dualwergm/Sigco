package com.dg.sigco.line.presenter

import com.dg.sigco.line.data.LineKt
import com.dg.sigco.line.repository.LineRepositoryKt
import com.dg.sigco.line.view.ILineView

/**
 * Created by dualwer on 27/11/17.
 */
class LinePresenter(val iLineView: ILineView) : ILinePresenter{
    override fun getLines() {
        LineRepositoryKt(this).getLines()
    }

    override fun showLines(lines: List<LineKt>) {
        iLineView.showLines(lines)
    }

}