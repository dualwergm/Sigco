package com.dg.sigco.card.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dg.sigco.R;
import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.data.CardKt;
import com.dg.sigco.card.view.NewCardActivity;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.UtilDate;
import com.dg.sigco.common.UtilsKt;
import com.dg.sigco.detail.view.DetailsActivity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sofia on 03/11/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{
    private List<Card> cards;
    private Activity activity;
    int resource;
    public CardAdapter(List<Card> cards, Activity activity, int resource){
        this.cards = cards;
        this.activity = activity;
        this.resource = resource;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        final Card card = cards.get(position);
        holder.cardDate.setText(UtilDate.getDateES(card.getDateStr(), UtilDate._DATE_FORMAT_SHORT));
        holder.quota.setText(com.dg.sigco.common.TextUtils.formatMoney(card.getQuota().toString()));
        holder.clientName.setText(card.getClientName());
        holder.clientAlias.setText(card.getClientAlias());
        holder.line.setText(card.getLineName());
        holder.value.setText(com.dg.sigco.common.TextUtils.formatMoney(card.getValue().toString()));
        holder.address.setText(card.getAddress());
        holder.phone.setText(card.getPhone());
        holder.products.setText(card.getProducts());
        holder.listQuotas.setTag(card);
        holder.editCard.setTag(card);
        assignStatusColor(holder, card.getTodaytoStr());
        activeSectionLayoutListener(holder);
        activeListQuotasButton(holder.listQuotas);
        activeEditCardButton(holder.editCard);
    }

    private void activeEditCardButton(Button editCardBtn) {
        if(((Card)editCardBtn.getTag()).getCardId().intValue() > 0){
            editCardBtn.setVisibility(View.INVISIBLE);
            return;
        }
        editCardBtn.setVisibility(View.VISIBLE);
        editCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = (Card)v.getTag();
                Intent intent = new Intent(activity, NewCardActivity.class);
                intent.putExtra(Constants.CARD, new CardKt(card));
                activity.startActivity(intent);
            }
        });
    }

    private void activeListQuotasButton(Button listQuotas) {
        listQuotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = (Card)v.getTag();
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra(Constants.CARD, card);
                activity.startActivity(intent);
            }
        });
    }

    private void assignStatusColor(CardViewHolder holder, String toDayToStr){
        if(TextUtils.isEmpty(toDayToStr)){
            return;
        }
        Date toDayTo = UtilDate.stringToDate(toDayToStr, UtilDate._DATE_FORMAT_SHORT);
        if(toDayTo == null){
            return;
        }
        Timestamp currentDate = UtilDate.getCurrentDateInitTimestamp();
        if(currentDate.after(toDayTo)){
            holder.clientName.setTextColor(ContextCompat.getColor(activity, R.color.statusDelayed));
            holder.clientAlias.setTextColor(ContextCompat.getColor(activity, R.color.statusDelayed));
        }else{
            holder.clientName.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
            holder.clientAlias.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        }
    }

    private void activeSectionLayoutListener(final CardViewHolder holder){
        holder.sectionActiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.sectionDetailLayout.getVisibility() == View.GONE){
                    holder.sectionDetailLayout.setVisibility(View.VISIBLE);
                }else{
                    holder.sectionDetailLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        private TextView cardDate, quota, clientName, clientAlias, line, value, address, phone, products;
        private LinearLayout sectionActiveLayout;
        private RelativeLayout sectionDetailLayout;
        private Button listQuotas, editCard;
        public CardViewHolder(View itemView) {
            super(itemView);
            sectionActiveLayout = (LinearLayout)itemView.findViewById(R.id.sectionActiveLayout);
            sectionDetailLayout = (RelativeLayout)itemView.findViewById(R.id.sectionDetailLayout);
            listQuotas = (Button)itemView.findViewById(R.id.listQuotas);
            editCard = (Button)itemView.findViewById(R.id.editCard);
            cardDate = (TextView)itemView.findViewById(R.id.cardDate);
            quota = (TextView)itemView.findViewById(R.id.quota);
            clientName = (TextView)itemView.findViewById(R.id.clientName);
            clientAlias = (TextView)itemView.findViewById(R.id.clientAlias);
            line = (TextView)itemView.findViewById(R.id.line);
            value = (TextView)itemView.findViewById(R.id.value);
            address = (TextView)itemView.findViewById(R.id.address);
            phone = (TextView)itemView.findViewById(R.id.phone);
            products = (TextView)itemView.findViewById(R.id.products);
        }
    }

    public void filter(List<Card> originalCards, final String text) {
        String lowerCase = text.toLowerCase();
        List<Card> filteredCards = new ArrayList<>();
        for(Card card: originalCards){
            if(card.getFilterStr().contains(lowerCase)){
                filteredCards.add(card);
            }
        }
        cards.clear();
        cards.addAll(filteredCards);
        notifyDataSetChanged();
    }
}
