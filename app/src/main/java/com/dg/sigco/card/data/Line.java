package com.dg.sigco.card.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.dg.sigco.db.shema.contract.CardContract;
import com.dg.sigco.db.shema.contract.ClientContract;
import com.dg.sigco.db.shema.contract.LineContract;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Sofia on 25/10/2017.
 */

public class Line implements Serializable{
    private String _id;
    @SerializedName("lineId")
    @Expose
    private Integer lineId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("config")
    @Expose
    private String config;
    @SerializedName("cardsDto")
    @Expose
    private List<Card> cards = null;
    @SerializedName("clientsDto")
    @Expose
    private List<Client> clients = null;
    @SerializedName("error")
    @Expose
    private Integer error;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Line(Cursor cursor){
        this._id =  cursor.getString(cursor.getColumnIndex(LineContract.LineEntry._ID));
        this.lineId =  cursor.getInt(cursor.getColumnIndex(LineContract.LineEntry.LINE_ID_COLUMN));
        this.name =  cursor.getString(cursor.getColumnIndex(LineContract.LineEntry.NAME_COLUMN));
        this.code =  cursor.getString(cursor.getColumnIndex(LineContract.LineEntry.CODE_COLUMN));
        this.config =  cursor.getString(cursor.getColumnIndex(LineContract.LineEntry.CONFIG_COLUMN));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(LineContract.LineEntry.LINE_ID_COLUMN, getLineId());
        values.put(LineContract.LineEntry.NAME_COLUMN, getName());
        values.put(LineContract.LineEntry.CODE_COLUMN, getCode());
        values.put(LineContract.LineEntry.DESCRIPTION_COLUMN, getDescription());
        values.put(LineContract.LineEntry.CONFIG_COLUMN, getConfig());
        return values;
    }
}
