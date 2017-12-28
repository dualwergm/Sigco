package com.dg.sigco.parameter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dg.sigco.R;
import com.dg.sigco.api.service.RetrofitBuilder;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.UtilView;
import com.dg.sigco.parameter.data.Parameter;
import com.dg.sigco.parameter.presenter.IParameterPresenter;
import com.dg.sigco.parameter.presenter.ParameterPresenter;

public class ServerActivity extends AppCompatActivity implements IParameterView{
    private TextView close;
    private EditText server;
    private IParameterPresenter iParameterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        iParameterPresenter = new ParameterPresenter(this);
        server = (EditText)findViewById(R.id.server);
        initializeClose();
        initializeSave();
        initializeServer();
    }

    private void initializeClose(){
        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeServer(){
        iParameterPresenter.loadServer();
        if(TextUtils.isEmpty(RetrofitBuilder.URL_REST)){
            server.setText(Constants.PREFFIX_URL);
        }else{
            server.setText(RetrofitBuilder.URL_REST);
        }
    }

    private void initializeSave(){
        Button button = (Button)findViewById(R.id.saveServer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(server.getText().toString().isEmpty()){
                    Toast.makeText(ServerActivity.this, "Ingrese el servidor a registrar.", Toast.LENGTH_SHORT).show();
                    return;
                }
                iParameterPresenter.saveServer(getParameter());
            }
        });
    }

    private Parameter getParameter(){
        return new Parameter(Constants.KEY_SERVER_PARAMETER, server.getText().toString());
    }

    @Override
    public void afterSave(long id) {
        if(id > 0){
            UtilView.showAlertDialog(this,
                    "Operación exitosa",
                    "Se registró correctamente el servidor.",
                    true);
            RetrofitBuilder.URL_REST = server.getText().toString();
        }else{
            UtilView.showAlertDialog(this,
                    "Operación erronea",
                    "Se presentó en error durante la operación. Intente de nuevo",
                    false);
        }
    }
}
