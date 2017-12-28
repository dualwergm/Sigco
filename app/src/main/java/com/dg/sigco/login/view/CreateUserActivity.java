package com.dg.sigco.login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dg.sigco.R;
import com.dg.sigco.common.Constants;
import com.dg.sigco.login.data.Userc;
import com.dg.sigco.login.presenter.CreateUserPresenter;
import com.dg.sigco.login.presenter.ICreateUserPresenter;

public class CreateUserActivity extends AppCompatActivity implements ICreateUserView{
    private Button saveUser;
    private Userc userc;
    private EditText userName, etUser, password, confirmPassword;
    private CheckBox isAdmin;
    private ICreateUserPresenter iCreateUserPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        userc = getIntent().getExtras() != null ? (Userc)getIntent().getExtras().getSerializable(Constants.USER) : null;
        iCreateUserPresenter = new CreateUserPresenter(this);
        saveUser = (Button)findViewById(R.id.saveUser);
        userName = (EditText) findViewById(R.id.userName);
        etUser = (EditText) findViewById(R.id.userc);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        isAdmin = (CheckBox)findViewById(R.id.isAdmin);
        initializeSaveUser();
        loadDataToEdit();
    }

    private void initializeSaveUser() {
        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate()){
                    return;
                }
                loadUser();
                iCreateUserPresenter.save(userc);
            }
        });
    }

    private void loadDataToEdit(){
        if(userc == null){
            return;
        }
        userName.setText(userc.getName());
        etUser.setText(userc.getUserc());
        password.setText(userc.getPassword());
        confirmPassword.setText(userc.getPassword());
        isAdmin.setChecked(userc.isAdmin());
    }

    private boolean validate(){
        if(userName.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingresa un nombre para el usuario.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etUser.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingresa un usuario para identificarte.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingresa una contraseña.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(confirmPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Confirma la contraseña.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.getText().toString().equals(confirmPassword.getText().toString())){
            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void loadUser(){
        if(userc == null){
            userc = new Userc();
        }
        userc.setName(userName.getText().toString());
        userc.setUserc(etUser.getText().toString());
        userc.setPassword(password.getText().toString());
        userc.setRol(Integer.valueOf(isAdmin.isChecked() ? "1" : "0"));
    }

    @Override
    public void afterSave() {
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
        UsersActivity.toFinish();
        finish();
    }
}
