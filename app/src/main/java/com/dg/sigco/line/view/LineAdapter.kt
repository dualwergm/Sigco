package com.dg.sigco.line.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.dg.sigco.R
import com.dg.sigco.card.view.NewCardActivity
import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.common.Constants
import com.dg.sigco.common.inflate
import com.dg.sigco.line.data.LineKt
import kotlinx.android.synthetic.main.item_line.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by dualwer on 27/11/17.
 */
class LineAdapter(private val lines:List<LineKt>, private val clientKt: ClientKt?):RecyclerView.Adapter<LineAdapter.LineHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LineHolder =
            LineHolder(parent?.inflate(R.layout.item_line))

    override fun onBindViewHolder(holder: LineHolder?, position: Int) {
        holder?.bindView(lines[position])
    }

    override fun getItemCount(): Int = lines.size

    inner class LineHolder(itemView:View?):RecyclerView.ViewHolder(itemView){
        fun bindView(line:LineKt){
            with(line){
                itemView.containerItemLine.tag = lineId
                itemView.lineName.text = name
            }
            itemView.sectionActiveLayout.tag = line
            itemView.sectionActiveLayout.setOnClickListener { v: View? ->
                v?.context?.startActivity<NewCardActivity>(Constants.LINE to v.tag as LineKt, Constants.CLIENT to clientKt)
            }
        }
    }

}

