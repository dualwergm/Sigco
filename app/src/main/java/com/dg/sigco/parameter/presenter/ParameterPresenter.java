package com.dg.sigco.parameter.presenter;

import android.text.TextUtils;

import com.dg.sigco.api.service.RetrofitBuilder;
import com.dg.sigco.parameter.data.Parameter;
import com.dg.sigco.parameter.repository.ParameterRespository;
import com.dg.sigco.parameter.view.IParameterView;

/**
 * Created by Sofia on 20/11/2017.
 */

public class ParameterPresenter implements IParameterPresenter {

    private IParameterView iParameterView;
    private ParameterRespository parameterRespository;
    public ParameterPresenter(final IParameterView iParameterView){
        this.iParameterView = iParameterView;
        parameterRespository = ParameterRespository.getInstance();
    }

    public ParameterPresenter(){
        parameterRespository = ParameterRespository.getInstance();
    }

    @Override
    public void saveServer(Parameter parameter) {
        final long save = parameterRespository.saveServer(parameter);
        iParameterView.afterSave(save);
    }

    @Override
    public void loadServer() {
        if(TextUtils.isEmpty(RetrofitBuilder.URL_REST)){
            RetrofitBuilder.URL_REST = parameterRespository.getServer();
        }
    }
}
