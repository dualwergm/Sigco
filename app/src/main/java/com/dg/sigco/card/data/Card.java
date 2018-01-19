package com.dg.sigco.card.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import com.dg.sigco.client.repository.ClientRepositoryImp;
import com.dg.sigco.common.Constants;
import com.dg.sigco.db.shema.contract.CardContract;
import com.dg.sigco.db.shema.contract.DetailContract;
import com.dg.sigco.line.repository.LineRepositoryImp;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Sofia on 25/10/2017.
 */

public class Card implements Serializable {
    private String _id;
    private String clientSLId;
    @SerializedName("cardId")
    @Expose
    private Integer cardId;
    @SerializedName("clientDto")
    @Expose
    private Client client;
    @SerializedName("products")
    @Expose
    private String products;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("quota")
    @Expose
    private Double quota;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("dateStr")
    @Expose
    private String dateStr;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("todaytoStr")
    @Expose
    private String todaytoStr;
    @SerializedName("lineId")
    @Expose
    private Integer lineId;
    @SerializedName("clientId")
    @Expose
    private Integer clientId;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    private Line line;
    private Detail currentDetail;

    private Timestamp creationDate;

    public Card(){
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getClientSLId() {
        return clientSLId;
    }

    public void setClientSLId(String clientSLId) {
        this.clientSLId = clientSLId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTodaytoStr() {
        return todaytoStr;
    }

    public void setTodaytoStr(String todaytoStr) {
        this.todaytoStr = todaytoStr;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Detail getCurrentDetail() {
        return currentDetail;
    }

    public void setCurrentDetail(Detail currentDetail) {
        this.currentDetail = currentDetail;
    }

    public String getLineName(){
        if(getLine() == null) return "";
        return getLine().getName();
    }

    public String getClientName(){
        if(getClient() == null) return "";
        return getClient().getName();
    }

    public String getClientAlias(){
        if(getClient() == null || TextUtils.isEmpty(getClient().getAlias())) return "";
        return " - " + getClient().getAlias();
    }

    private String getCompleteName(){
        return getClientName()+getClientAlias();
    }

    public String getFilterStr(){
        return com.dg.sigco.common.TextUtils.removeAccents(getCompleteName() +" "+getAddress()+" "+getPhone()).toLowerCase();
    }

    public Card(Cursor cursor, boolean isForUpload){
        loadCommons(cursor);
        if(isForUpload){
            loadForUpload(cursor);
        }else{
            this.client = ClientRepositoryImp.getInstance().getClient(getClientSLId());
            this.line = LineRepositoryImp.getInstance().getLine(lineId);
        }
    }

    private void loadCommons(Cursor cursor){
        this._id =  cursor.getString(cursor.getColumnIndex(CardContract.CardEntry._ID));
        this.cardId =  cursor.getInt(cursor.getColumnIndex(CardContract.CardEntry.CARD_ID_COLUMN));
        this.clientId =  cursor.getInt(cursor.getColumnIndex(CardContract.CardEntry.CLIENT_ID_COLUMN));
        this.lineId =  cursor.getInt(cursor.getColumnIndex(CardContract.CardEntry.LINE_ID_COLUMN));
        this.clientSLId = cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.CLIENT_SLID_COLUMN));
        this.dateStr = cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.DATEC_COLUMN));
        this.value =  cursor.getDouble(cursor.getColumnIndex(CardContract.CardEntry.VALUEC_COLUMN));
        this.quota =  cursor.getDouble(cursor.getColumnIndex(CardContract.CardEntry.QUOTA_COLUMN));
        this.products =  cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.PRODUCTS_COLUMN));
        this.address =  cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.ADDRESS_COLUMN));
        this.phone =  cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.PHONE_COLUMN));
        this.todaytoStr =  cursor.getString(cursor.getColumnIndex(CardContract.CardEntry.TODAYTO_COLUMN));
    }

    private void loadForUpload(Cursor cursor){
        if(this.clientId == 0){
            this.client = ClientRepositoryImp.getInstance().getClient(getClientSLId());
        }
        this.creationDate = new Timestamp(cursor.getLong(cursor.getColumnIndex(CardContract.CardEntry.CREATION_DATE_COLUMN)));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(CardContract.CardEntry.CARD_ID_COLUMN, getCardId());
        values.put(CardContract.CardEntry.LINE_ID_COLUMN, getLineId());
        values.put(CardContract.CardEntry.CLIENT_ID_COLUMN, getClientId());
        values.put(CardContract.CardEntry.CLIENT_SLID_COLUMN, getClientSLId());
        values.put(CardContract.CardEntry.PRODUCTS_COLUMN, getProducts());
        values.put(CardContract.CardEntry.VALUEC_COLUMN, getValue());
        values.put(CardContract.CardEntry.QUOTA_COLUMN, getQuota());
        values.put(CardContract.CardEntry.DATEC_COLUMN, getDateStr());
        values.put(CardContract.CardEntry.ADDRESS_COLUMN, getAddress());
        values.put(CardContract.CardEntry.PHONE_COLUMN, getPhone());
        values.put(CardContract.CardEntry.STATUS_COLUMN, getStatus());
        values.put(CardContract.CardEntry.TODAYTO_COLUMN, getTodaytoStr());
        values.put(CardContract.CardEntry.CREATION_DATE_COLUMN, new Date().getTime());
        return values;
    }

    public ContentValues toContentUpdateValues() {
        ContentValues values = new ContentValues();
        values.put(CardContract.CardEntry.PRODUCTS_COLUMN, getProducts());
        values.put(CardContract.CardEntry.VALUEC_COLUMN, getValue());
        values.put(CardContract.CardEntry.QUOTA_COLUMN, getQuota());
        values.put(CardContract.CardEntry.DATEC_COLUMN, getDateStr());
        values.put(CardContract.CardEntry.ADDRESS_COLUMN, getAddress());
        values.put(CardContract.CardEntry.PHONE_COLUMN, getPhone());
        values.put(CardContract.CardEntry.CREATION_DATE_COLUMN, new Date().getTime());
        values.put(CardContract.CardEntry.TODAYTO_COLUMN, getTodaytoStr());
        return values;
    }

    public String toJson(){
        try {
            JSONObject jCard = new JSONObject();
            jCard.put(CardContract.CardEntry.CARD_ID_COLUMN, getCardId().toString());
            jCard.put(CardContract.CardEntry.LINE_ID_COLUMN, getLineId().toString());
            jCard.put(CardContract.CardEntry.CLIENT_ID_COLUMN, getClientId().toString());
            jCard.put(CardContract.CardEntry.PRODUCTS_COLUMN, getProducts());
            jCard.put(Constants.VALUE, getValue().toString());
            jCard.put(CardContract.CardEntry.QUOTA_COLUMN, getQuota().toString());
            jCard.put(Constants.DATE, getDateStr());
            jCard.put(CardContract.CardEntry.ADDRESS_COLUMN, getAddress());
            jCard.put(CardContract.CardEntry.PHONE_COLUMN, getPhone());
            jCard.put(CardContract.CardEntry.STATUS_COLUMN, "0"); //TODO
            jCard.put(CardContract.CardEntry.TODAYTO_COLUMN, getTodaytoStr());
            jCard.put(CardContract.CardEntry.CREATION_DATE_COLUMN, getCreationDate().getTime());
            if(clientId == 0 && getClient() != null){
                jCard.put(Constants.CLIENT, getClient().toJson());
            }
            jCard.put(Constants.DETAILS, jDetails());
            return jCard.toString();
        } catch (JSONException e) {
            Log.e("Card", "Error cargando los datos al json");
            return null;
        }
    }

    private String jDetails(){
        if(getDetails() == null || getDetails().isEmpty()){
            return "";
        }
        JSONArray detailArray = new JSONArray();
        for(Detail det: getDetails()){
            detailArray.put(det.toJson());
        }
        return detailArray.toString();
    }
}