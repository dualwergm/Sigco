package com.dg.sigco.login.presenter;

import com.dg.sigco.login.data.Userc;

public interface ILoginPresenter {
    void validateUser(String user, String password);
    void loginSucces(Userc user);
    void loginError();
}
