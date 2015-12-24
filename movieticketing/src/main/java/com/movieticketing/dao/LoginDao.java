package com.movieticketing.dao;

import com.movieticketing.common.LoginDetails;
import com.movieticketing.model.Login;

public interface LoginDao {

	Login getLoginCredentials(String userName);

	LoginDetails validateLogin(LoginDetails login);
}
