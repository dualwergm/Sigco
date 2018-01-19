package com.dg.sigco.detail.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dg.sigco.R;
import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.data.Detail;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.TextUtils;
import com.dg.sigco.common.UtilDate;

import java.util.List;

/**
 * Created by Sofia on 12/11/2017.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>{

    private List<Detail> details;
    private Activity activity;
    private Card card;
    private int resource;
    public DetailAdapter(List<Detail> details, Activity activity, int resource, Card card){
        this.details = details;
        this.activity = activity;
        this.resource = resource;
        this.card = card;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new DetailViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        final Detail detail = details.get(position);
        holder.detailDate.setText(UtilDate.getDateES(detail.getDateStr(), UtilDate._DATE_FORMAT_SHORT));
        holder.detailValue.setText(TextUtils.formatMoney(detail.getValue().toString()));
        holder.containerItemDetail.setTag(detail);
        holder.containerItemDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Detail tag = (Detail) v.getTag();
                if(tag.getCardDetailId() == null || tag.getCardDetailId().intValue() > 0){
                    return;
                }
                card.setCurrentDetail(tag);
                Intent intent = new Intent(activity, NewDetailActivity.class);
                intent.putExtra(Constants.CARD, card);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public static class DetailViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout containerItemDetail;
        private TextView detailDate, detailValue;
        public DetailViewHolder(View itemView) {
            super(itemView);
            detailDate = (TextView)itemView.findViewById(R.id.detailDate);
            detailValue = (TextView)itemView.findViewById(R.id.detailValue);
            containerItemDetail = (RelativeLayout)itemView.findViewById(R.id.containerItemDetail);
        }
    }
}
