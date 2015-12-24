package com.movieticketing.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.movieticketing.bo.LoginBO;

/**
 * Created by nagal_000 on 11/30/2015.
 */
public class Test {

	@Autowired
	LoginBO loginBO;

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");

		// LoginBO bo = (LoginBO) appContext.getBean("loginBo");
		LoginDetails lg = new LoginDetails();
		lg.setUserId("ani@bazinga.com");
		lg.setPassword("ani123");
		ResultBean rb = new Test().loginBO.validateLogin(lg);
		System.out.println("Login:" + rb);
	}
}
