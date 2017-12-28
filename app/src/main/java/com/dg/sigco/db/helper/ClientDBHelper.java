package com.dg.sigco.db.helper;

import android.database.Cursor;

import com.dg.sigco.card.data.Client;
import com.dg.sigco.client.data.ClientKt;
import com.dg.sigco.db.shema.contract.ClientContract;

/**
 * Created by Sofia on 01/11/2017.
 */

public class ClientDBHelper {

    private static ClientDBHelper clientDBHelper;

    public static final String[] SELECT = new String[]{
            ClientContract.ClientEntry._ID,
            ClientContract.ClientEntry.CLIENT_ID_COLUMN,
            ClientContract.ClientEntry.NAME_COLUMN,
            ClientContract.ClientEntry.ALIAS_COLUMN,
            ClientContract.ClientEntry.ADDRESS_COLUMN,
            ClientContract.ClientEntry.PHONE_COLUMN
    };

    private DBHelperManager helperManager;

    private ClientDBHelper(DBHelperManager helperManager){
        this.helperManager = helperManager;
    }

    public static ClientDBHelper getInstance(DBHelperManager helperManager){
        if(clientDBHelper == null){
            clientDBHelper = new ClientDBHelper(helperManager);
        }
        return clientDBHelper;
    }

    /**
     * Para crear los que vienen de la web
      * @param client
     * @return
     */
    public long save(Client client) {
        return helperManager.getWritableDatabase().insert(
                ClientContract.ClientEntry.TABLE_NAME,
                null,
                client.toContentValues());
    }

    /**
     * Para crear desde la APP
     * @param clientKt
     * @return
     */
    public long save(ClientKt clientKt) {
        return helperManager.getWritableDatabase().insert(
                ClientContract.ClientEntry.TABLE_NAME,
                null,
                clientKt.toContentValues());

    }

    public Cursor getClient(String slId) {
        return helperManager.getReadableDatabase()
                .query(
                        ClientContract.ClientEntry.TABLE_NAME,
                        SELECT,
                        ClientContract.ClientEntry._ID +" = '"+slId+"'",
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getClients() {
        return helperManager.getReadableDatabase()
                .query(
                        ClientContract.ClientEntry.TABLE_NAME,
                        SELECT,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getClientsForMap() {
        return helperManager.getReadableDatabase()
                .query(
                        ClientContract.ClientEntry.TABLE_NAME,
                        new String[]{ClientContract.ClientEntry._ID,ClientContract.ClientEntry.CLIENT_ID_COLUMN},
                        null,
                        null,
                        null,
                        null,
                        null);
    }
}
