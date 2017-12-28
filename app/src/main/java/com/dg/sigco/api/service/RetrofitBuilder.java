package com.dg.sigco.api.service;

import android.text.TextUtils;

import com.dg.sigco.common.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sofia on 31/10/2017.
 */

public class RetrofitBuilder {
    public static String URL_REST; // se inicializa al guardar el server
    private static Retrofit retrofit = null;
    private RetrofitBuilder(){
    }

    public static Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl(getBaseRestURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static String getBaseRestURL(){
        if(TextUtils.isEmpty(URL_REST)){
            return null;
        }
        return URL_REST.endsWith("/") ? (URL_REST + "rest/") : (URL_REST + "/rest/");
    }

    public static CardService getCardService(){
        return getInstance().create(CardService.class);
    }
}
