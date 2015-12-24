package com.movieticketing.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.movieticketing.common.LoginDetails;
import com.movieticketing.dao.LoginDao;
import com.movieticketing.model.Login;
import com.movieticketing.model.Theatre;
import com.movieticketing.model.User;

public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

	public Login getLoginCredentials(String userName) {
		List results = getHibernateTemplate().find("from Login where userId like ?", userName);
		return (Login) results.get(0);
	}

	public LoginDetails validateLogin(LoginDetails login) {
		List results = getHibernateTemplate().find("from Login where userId = ? and password = ?", login.getUserId(),
				login.getPassword());
		if (results.size() == 1) {
			Login lgin = (Login) results.get(0);
			LoginDetails details = new LoginDetails();
			details.setRole(lgin.getRole());
			details.setUserId(lgin.getUserId());
			if (lgin.getRole().equals("USER")) {
				results = getHibernateTemplate().find("from User where userId = ?", login.getUserId());
				if (results.size() == 1) {
					details.setName(((User) results.get(0)).getName());
				}
			} else if (lgin.getRole().equals("TADMIN")) {
				results = getHibernateTemplate().find("from Theatre where theatreid = ?", login.getUserId());
				if (results.size() == 1) {
					details.setName(((Theatre) results.get(0)).getName());
				}
			}
			return details;
		} else
			return null;
	}

}
