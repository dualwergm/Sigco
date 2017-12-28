package com.dg.sigco.client.view

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.dg.sigco.R
import com.dg.sigco.client.data.ClientKt
import com.dg.sigco.common.Constants
import com.dg.sigco.common.inflate
import com.dg.sigco.line.view.LineActivity
import kotlinx.android.synthetic.main.item_client.view.*
import org.jetbrains.anko.startActivity
import java.util.*

/**
 * Created by dualwer on 24/11/17.
 */
class ClientsAdapter(val clients: MutableList<ClientKt>):RecyclerView.Adapter<ClientsAdapter.ClientHolder>() {
    override fun getItemCount(): Int = clients.size

    override fun onBindViewHolder(holder: ClientHolder?, position: Int) {
        holder?.bindView(clients[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ClientHolder =
        ClientHolder(parent?.inflate(R.layout.item_client))

    class ClientHolder(itemView:View?):RecyclerView.ViewHolder(itemView){
        fun bindView(client:ClientKt){
           with(client){
               itemView.name.text = name
               itemView.alias.text = showAlias()
               itemView.address.text = address
               itemView.phone.text = phone
           }
            itemView.sectionActiveLayout.tag = client
            itemView.sectionActiveLayout.setOnClickListener { v: View? ->
                v?.context?.startActivity<LineActivity>(Constants.CLIENT to v.tag as ClientKt)
            }
        }
    }

    fun filter(originalClients: List<ClientKt>, text: String) {
        val lowerCase = text.toLowerCase()
        val filteredClients = originalClients.filter { it.filterStr().contains(lowerCase) }
        clients.clear()
        clients.addAll(filteredClients)
        notifyDataSetChanged()
    }
}