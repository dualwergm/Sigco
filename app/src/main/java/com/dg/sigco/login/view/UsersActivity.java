package com.dg.sigco.login.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dg.sigco.R;
import com.dg.sigco.detail.view.DetailAdapter;
import com.dg.sigco.login.data.Userc;
import com.dg.sigco.login.presenter.IUserPresenter;
import com.dg.sigco.login.presenter.UserPresenter;

import java.util.List;

public class UsersActivity extends AppCompatActivity implements IUsersView{
    private static UsersActivity usersActivity;
    private IUserPresenter iUserPresenter;
    private TextView close;
    private RecyclerView usersReycler;
    private RecyclerView.LayoutManager lManager;
    private FloatingActionButton addUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        this.usersActivity = this;
        iUserPresenter = new UserPresenter(this);
        usersReycler = (RecyclerView)findViewById(R.id.usersReycler);
        lManager = new LinearLayoutManager(this);
        usersReycler.setLayoutManager(lManager);
        iUserPresenter.listUsers();
        addUser = (FloatingActionButton)findViewById(R.id.addUser);
        initializeAddUser();
        initializeClose();
    }

    private void initializeClose(){
        close = (TextView)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeAddUser() {
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateUserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showUsers(List<Userc> users) {
        usersReycler.setAdapter(new UserAdapter(users, this, R.layout.item_user));
    }

    public static void toFinish(){
        if(usersActivity != null){
            usersActivity.finish();
            usersActivity = null;
        }
    }
}