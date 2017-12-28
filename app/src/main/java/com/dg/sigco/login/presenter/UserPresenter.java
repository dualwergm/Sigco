package com.dg.sigco.login.presenter;

import com.dg.sigco.login.data.Userc;
import com.dg.sigco.login.repository.UserRepository;
import com.dg.sigco.login.view.IUsersView;

import java.util.List;

public class UserPresenter implements IUserPresenter{

    private UserRepository userRepository;
    private IUsersView iUsersView;
    public UserPresenter(final IUsersView iUsersView){
        userRepository = new UserRepository(this);
        this.iUsersView = iUsersView;
    }

    @Override
    public void listUsers() {
        userRepository.listUsers();
    }

    @Override
    public void showUsers(List<Userc> users) {
        iUsersView.showUsers(users);
    }
}
