package com.movieticketing.bo;

import com.movieticketing.common.LoginDetails;
import com.movieticketing.common.ResultBean;
import com.movieticketing.model.Login;

public interface LoginBO {

	Login getLoginCredentials(String userName);

	ResultBean validateLogin(LoginDetails login);
	
}
