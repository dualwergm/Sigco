package com.dg.sigco.detail.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dg.sigco.R;
import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.data.Detail;
import com.dg.sigco.card.repository.DetailRepositoryImp;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.TextUtils;
import com.dg.sigco.common.UtilDate;
import com.dg.sigco.detail.presenter.DetailPresenterImp;
import com.dg.sigco.detail.presenter.IDetailPresenter;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements IDetailView{
    private TextView cardDate, quota, clientName, clientAlias, payed, debt;
    private IDetailPresenter iDetailPresenter;
    private RecyclerView detailsReycler;
    private RecyclerView.LayoutManager lManager;
    private FloatingActionButton back, addDetail;
    private static DetailsActivity detailsActivity;
    private Card card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsActivity = this;
        card = (Card)getIntent().getExtras().getSerializable(Constants.CARD);
        back = findViewById(R.id.back);
        addDetail = findViewById(R.id.addDetail);
        iDetailPresenter = new DetailPresenterImp(this);
        detailsReycler = findViewById(R.id.detailsReycler);
        lManager = new LinearLayoutManager(this);
        detailsReycler.setLayoutManager(lManager);
        iDetailPresenter.getDetails(card.get_id());
        loadDataHeader();
        initializeFABBack();
        initializeFABAddDetail();
    }

    private void initializeFABBack(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeFABAddDetail(){
        addDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewDetailActivity.class);
                intent.putExtra(Constants.CARD, card);
                startActivity(intent);
            }
        });
    }

    private void loadDataHeader(){
        double totalPayed = DetailRepositoryImp.getInstance().getPayed(card.getCardId());
        payed = findViewById(R.id.payed);
        debt = findViewById(R.id.debt);
        cardDate = findViewById(R.id.cardDate);
        quota = findViewById(R.id.quota);
        clientName = findViewById(R.id.clientName);
        clientAlias = findViewById(R.id.clientAlias);
        cardDate.setText(UtilDate.getDateES(card.getDateStr(), UtilDate._DATE_FORMAT_SHORT));
        quota.setText(TextUtils.formatMoney(card.getQuota().toString()));
        clientName.setText(card.getClientName());
        clientAlias.setText(card.getClientAlias());
        payed.setText(TextUtils.formatMoney(String.valueOf(totalPayed)));
        double totalDebt = card.getValue() - totalPayed;
        debt.setText(TextUtils.formatMoney(String.valueOf(totalDebt)));
    }

    @Override
    public void showDetails(List<Detail> details) {
        if(details != null && !details.isEmpty()){
            detailsReycler.setVisibility(View.VISIBLE);
            detailsReycler.setAdapter(new DetailAdapter(details, this, R.layout.item_detail, card));
        }else{
            LinearLayout noDetails = findViewById(R.id.noDetails);
            noDetails.setVisibility(View.VISIBLE);
        }
    }

    public static void toFinish(){
        if(detailsActivity != null){
            detailsActivity.finish();
            detailsActivity = null;
        }
    }
}
