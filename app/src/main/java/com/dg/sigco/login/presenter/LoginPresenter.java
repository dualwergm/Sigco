package com.dg.sigco.login.presenter;

import com.dg.sigco.common.Constants;
import com.dg.sigco.common.UserSession;
import com.dg.sigco.login.data.Userc;
import com.dg.sigco.login.repository.LoginRepository;
import com.dg.sigco.login.view.ILoginView;
import com.dg.sigco.parameter.data.Parameter;
import com.dg.sigco.parameter.repository.ParameterRespository;

public class LoginPresenter implements ILoginPresenter{

    private ILoginView iLoginView;
    private LoginRepository loginRepository;
    public LoginPresenter(final ILoginView iLoginView){
        this.iLoginView = iLoginView;
        loginRepository = new LoginRepository(this);
    }

    @Override
    public void validateUser(String user, String password) {
        loginRepository.validateUser(user, password);
    }

    @Override
    public void loginSucces(Userc user) {
        UserSession.name = user.getName();
        UserSession.user = user.getUserc();
        UserSession.isAdmin = user.isAdmin();
        String loggedValue = user.getUserc()+":"+user.getPassword();
        ParameterRespository.getInstance().save(new Parameter(Constants.KEY_USER_LOGGED_PARAMETER, loggedValue));
        iLoginView.loginSuccess();
    }

    @Override
    public void loginError() {
        UserSession.clean();
        iLoginView.loginError();
    }
}
