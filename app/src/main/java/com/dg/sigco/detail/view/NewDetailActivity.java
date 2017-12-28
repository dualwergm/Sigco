package com.dg.sigco.detail.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.dg.sigco.R;
import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.data.Detail;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.DatePickerFragment;
import com.dg.sigco.common.UtilDate;
import com.dg.sigco.detail.presenter.INewDetailPresenter;
import com.dg.sigco.detail.presenter.NewDetailPresenter;

import okhttp3.internal.Util;

public class NewDetailActivity extends AppCompatActivity implements INewDetailView{
    private FloatingActionButton back;
    private EditText detailDate, detailValue;
    private Button saveDetail;
    private INewDetailPresenter iNewDetailPresenter;
    private String selectedDate;
    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        iNewDetailPresenter = new NewDetailPresenter(this);
        back = findViewById(R.id.back);
        detailDate = findViewById(R.id.detailDate);
        detailValue = findViewById(R.id.detailValue);
        card = (Card) getIntent().getExtras().getSerializable(Constants.CARD);
        saveDetail = findViewById(R.id.saveDetail);
        initializeFABBack();
        initializeSaveDetail();
        initializeDetailDatePicker();
        initializeData();
    }

    private void initializeData(){
        Detail currentDetail = card.getCurrentDetail();
        selectedDate = UtilDate.getCurrentDateShort();
        detailValue.setText(card.getQuota().toString());
        if(currentDetail != null){
            selectedDate = currentDetail.getDateStr();
            detailValue.setText(currentDetail.getValue().toString());
        }
        detailDate.setText(UtilDate.getDateES(selectedDate, UtilDate._DATE_FORMAT_SHORT));

    }

    private void initializeSaveDetail(){
        saveDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    iNewDetailPresenter.saveDetail(buildDetail());
                }
            }
        });
    }

    private boolean validate(){
        if(TextUtils.isEmpty(detailValue.getText().toString())){
            Toast.makeText(this, "Ingresa el valor de la cuota.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Detail buildDetail(){
        final boolean has = card.getCurrentDetail() != null && !TextUtils.isEmpty(card.getCurrentDetail().get_id());
        Detail detail = !has ? new Detail() : card.getCurrentDetail();
        detail.setCardId(card.getCardId());
        detail.setValue(Double.valueOf(detailValue.getText().toString()));
        detail.setDateStr(selectedDate);
        detail.setCardSLId(card.get_id());
        return detail;
    }

    private void initializeFABBack(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeDetailDatePicker(){
        detailDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                selectedDate =  year+ "-" + (month+1) + "-" + day;
                detailDate.setText(UtilDate.getDateES(selectedDate, UtilDate._DATE_FORMAT_SHORT));
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void afterSave() {
        DetailsActivity.toFinish();
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.CARD, card);
        startActivity(intent);
        finish();
    }
}