package com.movieticketing.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.movieticketing.bo.UserBO;
import com.movieticketing.common.ResultBean;
import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;
import com.movieticketing.dao.UserDao;

@Controller
public class UserBOImpl implements UserBO {

	@Autowired
	UserDao userDao;

	@Override
	public ResultBean confirmTicket(TicketConfirmationDetails ticket) {
		TicketConfirmationDetails ticketDetails = userDao.confirmTicket(ticket);
		List<TicketConfirmationDetails> rows = new ArrayList<TicketConfirmationDetails>();
		ResultBean rb = new ResultBean();
		if (ticketDetails.getConfCode() != null) {
			rb.setStatus(200);
			rows.add(ticketDetails);
			rb.setRows(rows);
		} else {
			rb.setStatus(400);
		}
		return rb;
	}

	@Override
	public ResultBean createUser(UserDetails user) {
		ResultBean rb = new ResultBean();
		Boolean result = userDao.createUser(user);
		if (result) {
			rb.setStatus(200);
		} else {
			rb.setStatus(400);
		}
		return rb;
	}

	@Override
	public ResultBean getUser(String userId) {
		ResultBean rb = new ResultBean();
		rb.setRows(userDao.getUser(userId));
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean getUserBookingDetails(String userId) {
		ResultBean rb = new ResultBean();
		rb.setRows(userDao.getUserBookingDetails(userId));
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean updateMovieRating(String movieId, String rating) {
		ResultBean rb = new ResultBean();
		userDao.updateMovieRating(movieId, rating);
		rb.setRows(null);
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean getTopRatedMovies() {
		ResultBean rb = new ResultBean();
		rb.setRows(userDao.getTopRatedMovies());
		rb.setStatus(200);
		return rb;
	}

}
