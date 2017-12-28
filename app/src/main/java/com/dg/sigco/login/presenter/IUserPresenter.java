package com.dg.sigco.login.presenter;

import com.dg.sigco.login.data.Userc;

import java.util.List;

/**
 * Created by dualwer on 14/11/17.
 */

public interface IUserPresenter {
    void listUsers();
    void showUsers(List<Userc> users);
}
