package com.dg.sigco.login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dg.sigco.R;
import com.dg.sigco.card.view.CardsContainerActivity;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.UtilView;
import com.dg.sigco.login.presenter.ILoginPresenter;
import com.dg.sigco.login.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView{
    private EditText username, password;
    private Button btnLogin;
    private ProgressBar progressBar;
    private ILoginPresenter iLoginPresenter;
    private String logged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logged = getIntent().getExtras().getString(Constants.USER_LOGGED, null);
        iLoginPresenter = new LoginPresenter(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        initializeBtnLogin();
        initializeTextLogin();
    }

    private void initializeBtnLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateView()){
                    validateAccessUser();
                }
            }
        });
    }

    private void initializeTextLogin() {
        if(TextUtils.isEmpty(logged)){
            return;
        }
        final String[] split = logged.split(":");
        username.setText(split[0]);
        password.setText(split[1]);
        validateAccessUser();
    }

    private void validateAccessUser(){
        disableView();
        iLoginPresenter.validateUser(username.getText().toString(), password.getText().toString());
    }

    private boolean validateView(){
        if(username.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese un usuario valido.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese una contraseña valida.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void enableView(){
        btnLogin.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    private void disableView(){
        btnLogin.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, CardsContainerActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginError() {
        enableView();
        UtilView.showAlertDialog(this,
                "Usuario inválido",
                "La combinación usuario/contraseña no es correcta o el usuario no está activo. Revise e intente de nuevo o avise al administrador.",
                false);
    }
}
