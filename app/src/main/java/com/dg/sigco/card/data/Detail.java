package com.dg.sigco.card.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.dg.sigco.client.repository.ClientRepositoryImp;
import com.dg.sigco.common.UtilDate;
import com.dg.sigco.db.shema.contract.CardContract;
import com.dg.sigco.db.shema.contract.ClientContract;
import com.dg.sigco.db.shema.contract.DetailContract;
import com.dg.sigco.line.repository.LineRepositoryImp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Sofia on 06/11/2017.
 */

public class Detail implements Serializable{
    private String _id;
    @SerializedName("cardDetailId")
    @Expose
    private Integer cardDetailId;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("cardId")
    @Expose
    private Integer cardId;
    @SerializedName("dateStr")
    @Expose
    private String dateStr;

    private Timestamp creationDate;
    private String cardSLId;

    public Detail(){}

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getCardDetailId() {
        return cardDetailId;
    }

    public void setCardDetailId(Integer cardDetailId) {
        this.cardDetailId = cardDetailId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getCardSLId() {
        return cardSLId;
    }

    public void setCardSLId(String cardSLId) {
        this.cardSLId = cardSLId;
    }

    public Detail(Cursor cursor){
        this._id =  cursor.getString(cursor.getColumnIndex(DetailContract.DetailEntry._ID));
        this.cardDetailId =  cursor.getInt(cursor.getColumnIndex(DetailContract.DetailEntry.DETAIL_ID_COLUMN));
        this.cardId =  cursor.getInt(cursor.getColumnIndex(DetailContract.DetailEntry.CARD_ID_COLUMN));
        this.cardSLId = cursor.getString(cursor.getColumnIndex(DetailContract.DetailEntry.CARD_SLID_COLUMN));
        this.dateStr = cursor.getString(cursor.getColumnIndex(DetailContract.DetailEntry.DATE_COLUMN));
        this.value =  cursor.getDouble(cursor.getColumnIndex(DetailContract.DetailEntry.VALUEC_COLUMN));
        this.creationDate =  new Timestamp(cursor.getLong(cursor.getColumnIndex(DetailContract.DetailEntry.CREATIONDATE_COLUMN)));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        int detailId = getCardDetailId() == null ? 0 :getCardDetailId().intValue();
        values.put(DetailContract.DetailEntry.DETAIL_ID_COLUMN, detailId);
        values.put(DetailContract.DetailEntry.CARD_ID_COLUMN, getCardId());
        values.put(DetailContract.DetailEntry.CARD_SLID_COLUMN, getCardSLId());
        values.put(DetailContract.DetailEntry.VALUEC_COLUMN, getValue());
        values.put(DetailContract.DetailEntry.CREATIONDATE_COLUMN, UtilDate.getCurrentDateInitTimestamp().getTime());
        values.put(DetailContract.DetailEntry.DATE_COLUMN, getDateStr());
        return values;
    }

    public ContentValues toUpdateValues() {
        ContentValues values = new ContentValues();
        values.put(DetailContract.DetailEntry.VALUEC_COLUMN, getValue());
        values.put(DetailContract.DetailEntry.CREATIONDATE_COLUMN, UtilDate.getCurrentDateInitTimestamp().getTime());
        values.put(DetailContract.DetailEntry.DATE_COLUMN, getDateStr());
        return values;
    }

    public String toJson(){
        try {
            JSONObject jDetail = new JSONObject();
            jDetail.put(DetailContract.DetailEntry.CARD_ID_COLUMN, getCardId().toString());
            jDetail.put(DetailContract.DetailEntry.VALUEC_COLUMN, getValue().toString());
            jDetail.put(DetailContract.DetailEntry.CREATIONDATE_COLUMN, getCreationDate().getTime());
            jDetail.put(DetailContract.DetailEntry.DATE_COLUMN, getDateStr());
            return jDetail.toString();
        } catch (JSONException e) {
            Log.e("Detail", "Error cargando los datos al json");
            return null;
        }
    }
}