package com.dg.sigco.api.service;

import com.dg.sigco.card.data.Line;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sofia on 31/10/2017.
 */

public interface CardService {
    @GET("cards/download")
    Call<List<Line>> downloadCards(@Query("maxCardId") int maxCardId);

    @POST("cards/upload")
    Call<JsonObject> uploadCards(@Body RequestBody roundXML);
}
