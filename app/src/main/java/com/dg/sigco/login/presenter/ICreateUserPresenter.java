package com.dg.sigco.login.presenter;

import com.dg.sigco.login.data.Userc;

/**
 * Created by Sofia on 16/11/2017.
 */

public interface ICreateUserPresenter {
    void save(Userc user);
    void afterSave();
}
