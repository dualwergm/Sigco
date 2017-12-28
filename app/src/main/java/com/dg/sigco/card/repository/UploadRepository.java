package com.dg.sigco.card.repository;

import com.dg.sigco.api.service.CardService;
import com.dg.sigco.api.service.RetrofitBuilder;
import com.dg.sigco.card.presenter.IUploadPresenter;
import com.dg.sigco.common.Constants;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dualwer on 12/12/17.
 */

public class UploadRepository implements Callback<JsonObject>{

    private static UploadRepository uploadRepository;
    private IUploadPresenter uploadPresenter;
    private UploadRepository(final IUploadPresenter uploadPresenter){
        this.uploadPresenter = uploadPresenter;
    }

    public static UploadRepository getInstance(final IUploadPresenter uploadPresenter){
        if(uploadRepository == null){
            uploadRepository = new UploadRepository(uploadPresenter);
        }
        return uploadRepository;
    }

    public void uploadCard(String jCards){
        final CardService cardService = RetrofitBuilder.getCardService();
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), jCards);
        final Call<JsonObject> call = cardService.uploadCards(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        if(response.code() != Constants.HTTP_OK){
            uploadPresenter.errorConection();
            return;
        }
        JsonObject body = response.body();
        uploadPresenter.afterUpload();
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        uploadPresenter.errorConection();
    }
}