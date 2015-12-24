package com.movieticketing.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.movieticketing.bo.LoginBO;
import com.movieticketing.common.LoginDetails;
import com.movieticketing.common.ResultBean;
import com.movieticketing.dao.LoginDao;
import com.movieticketing.model.Login;

@Controller
public class LoginBOImpl implements LoginBO {

	@Autowired
	LoginDao loginDao;

	public Login getLoginCredentials(String userName) {
		return loginDao.getLoginCredentials(userName);
	}

	public ResultBean validateLogin(LoginDetails login) {
		ResultBean rb = new ResultBean();
		LoginDetails lgin = loginDao.validateLogin(login);
		if (lgin != null) {
			List<LoginDetails> rows = new ArrayList<LoginDetails>();
			rows.add(lgin);
			rb.setRows(rows);
			rb.setStatus(200);
		} else {
			rb.setStatus(400);
		}
		return rb;
	}

}
