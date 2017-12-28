package com.dg.sigco.card.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.dg.sigco.db.shema.contract.CardContract;
import com.dg.sigco.db.shema.contract.ClientContract;
import com.dg.sigco.db.shema.contract.LineContract;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Sofia on 25/10/2017.
 */

public class Client implements Serializable{
    private String _id;
    @SerializedName("clientId")
    @Expose
    private Integer clientId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public Client(Cursor cursor){
        this._id =  cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry._ID));
        this.clientId =  cursor.getInt(cursor.getColumnIndex(ClientContract.ClientEntry.CLIENT_ID_COLUMN));
        this.name =  cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry.NAME_COLUMN));
        this.alias =  cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry.ALIAS_COLUMN));
        this.address =  cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry.ADDRESS_COLUMN));
        this.phone =  cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry.PHONE_COLUMN));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ClientContract.ClientEntry.CLIENT_ID_COLUMN, getClientId());
        values.put(ClientContract.ClientEntry.NAME_COLUMN, getName());
        values.put(ClientContract.ClientEntry.ALIAS_COLUMN, getAlias());
        values.put(ClientContract.ClientEntry.ADDRESS_COLUMN, getAddress());
        values.put(ClientContract.ClientEntry.PHONE_COLUMN, getPhone());
        return values;
    }

    public String toJson(){
        try {
            JSONObject jClient = new JSONObject();
            jClient.put(ClientContract.ClientEntry.NAME_COLUMN, getName());
            jClient.put(ClientContract.ClientEntry.ALIAS_COLUMN, getAlias());
            jClient.put(ClientContract.ClientEntry.ADDRESS_COLUMN, getAddress());
            jClient.put(ClientContract.ClientEntry.PHONE_COLUMN, getPhone());
            return jClient.toString();
        } catch (JSONException e) {
            Log.e("Client", "Error cargando los datos al json");
            return null;
        }
    }
}