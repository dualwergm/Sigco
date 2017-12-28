package com.dg.sigco.login.presenter;

import android.text.TextUtils;

import com.dg.sigco.login.data.Userc;
import com.dg.sigco.login.repository.UserRepository;
import com.dg.sigco.login.view.ICreateUserView;

/**
 * Created by Sofia on 16/11/2017.
 */

public class CreateUserPresenter implements ICreateUserPresenter {

    private UserRepository userRepository;
    private ICreateUserView iCreateUserView;
    public CreateUserPresenter(final ICreateUserView iCreateUserView){
        this.userRepository = new UserRepository(this);
        this.iCreateUserView = iCreateUserView;
    }

    @Override
    public void save(Userc user) {
        if(TextUtils.isEmpty(user.get_id())){
            userRepository.save(user);
        }else{
            userRepository.update(user);
        }
    }

    @Override
    public void afterSave() {
        iCreateUserView.afterSave();
    }
}
