package com.dg.sigco.client.repository;

import android.database.Cursor;
import android.text.TextUtils;

import com.dg.sigco.card.data.Client;
import com.dg.sigco.db.helper.ClientDBHelper;
import com.dg.sigco.db.helper.DBHelperManager;
import com.dg.sigco.db.shema.contract.ClientContract;
import com.dg.sigco.line.repository.LineRepositoryImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sofia on 01/11/2017.
 */

public class ClientRepositoryImp {

    private static ClientRepositoryImp clientRepositoryImp;

    private ClientDBHelper dbHelper;
    public ClientRepositoryImp(){
        this.dbHelper = ClientDBHelper.getInstance(DBHelperManager.getInstance());
    }

    public static ClientRepositoryImp getInstance(){
        if(clientRepositoryImp == null){
            clientRepositoryImp = new ClientRepositoryImp();
        }
        return clientRepositoryImp;
    }

    public long save(Client client){
        return dbHelper.save(client);
    }

    public Client getClient(String slId){
        Cursor cursor = dbHelper.getClient(slId);
        Client client = null;
        if(cursor.moveToNext()){
            client = new Client(cursor);
        }
        cursor.close();
        return client;
    }

    public Map<Integer, String> getClientsAsMap(){
        Cursor cursor = dbHelper.getClientsForMap();
        Map<Integer, String> clientsMap = new HashMap<>();
        while(cursor.moveToNext()){
            clientsMap.put(cursor.getInt(cursor.getColumnIndex(ClientContract.ClientEntry.CLIENT_ID_COLUMN)),
                    cursor.getString(cursor.getColumnIndex(ClientContract.ClientEntry._ID)));
        }
        cursor.close();
        return clientsMap;
    }

    public void saveNewClients(List<Client> clients, Map<Integer, String> clientsMap){
        String clientSLId = "";
        for (Client client: clients) {
            if(TextUtils.isEmpty(clientsMap.get(client.getClientId()))){
                clientSLId = String.valueOf(clientRepositoryImp.save(client));
                LineRepositoryImp.savedMap.put(client.getClientId(), clientSLId);
            }
        }
    }
}


