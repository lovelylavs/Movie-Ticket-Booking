package com.movieticketing.dao.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserBookingDetails;
import com.movieticketing.common.UserDetails;
import com.movieticketing.dao.MovieDao;
import com.movieticketing.dao.TheatreDao;
import com.movieticketing.dao.UserDao;
import com.movieticketing.model.Login;
import com.movieticketing.model.Movie;
import com.movieticketing.model.Shows;
import com.movieticketing.model.Ticket;
import com.movieticketing.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Autowired
	TheatreDao theatreDao;

	@Autowired
	MovieDao movieDao;

	@Override
	public TicketConfirmationDetails confirmTicket(TicketConfirmationDetails ticket) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date;

			date = sdf.parse(ticket.getDate());
            
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			List result = getHibernateTemplate().find(
					"from Shows where theatreId = ? and screen = ? and showName = ? and movieId = ? and date = ?", ticket.getTheatreId(),
					ticket.getScreen(), ticket.getShow(), ticket.getMovieId(), sqlDate);
			if (result != null && result.size() == 1) {
				Shows shows = (Shows) result.get(0);
				if (shows.getSeat() > 0) {
					int remainingSeats = shows.getSeat() - ticket.getSeatCount();
					if (remainingSeats >= 0) {

						SecureRandom random = new SecureRandom();
						String confCode = new BigInteger(130, random).toString(32);

						Ticket userTicket = new Ticket();
						userTicket.setUserId(ticket.getUserId());
						userTicket.setTheatreId(ticket.getTheatreId());
						userTicket.setScreen(ticket.getScreen());
						userTicket.setShow(ticket.getShow());
						userTicket.setSeatCount(ticket.getSeatCount());
						userTicket.setDate(sqlDate);
						userTicket.setMovieId(ticket.getMovieId());
						userTicket.setConfCode(confCode);
						getHibernateTemplate().save(userTicket);

						shows.setSeat(remainingSeats);
						getHibernateTemplate().merge(shows);
						ticket.setConfCode(confCode);
						return ticket;
					}

				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	@Override
	public Boolean createUser(UserDetails user) {
		try {
			User usr = new User();
			usr.setUserId(user.getUserId());
			usr.setPhone(user.getPhoneNumber());
			usr.setName(user.getName());
			getHibernateTemplate().save(usr);

			Login login = new Login();
			login.setUserId(user.getUserId());
			login.setPassword(user.getPassword());
			login.setRole("USER");
			getHibernateTemplate().save(login);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List getUser(String userId) {
		return getHibernateTemplate().find("from User where userId = ?", userId);
	}

	@Override
	public List getUserBookingDetails(String userId) {
		List<UserBookingDetails> userBookingDetails = null;
		List results = getHibernateTemplate().find("from Ticket where userId = ?", userId);
		if (results != null) {
			userBookingDetails = new ArrayList();
			for (Object o : results) {
				Ticket ticket = (Ticket) o;
				UserBookingDetails bookingDetail = new UserBookingDetails();
				bookingDetail.setConfCode(ticket.getConfCode());
				bookingDetail.setNoOfTickets(ticket.getSeatCount());
				bookingDetail.setScreen(ticket.getScreen());
				bookingDetail.setShow(ticket.getShow());
				bookingDetail.setMovieName(movieDao.getMovieNameById(ticket.getMovieId()));
				bookingDetail.setTheatreName(theatreDao.getTheatreNameById(ticket.getTheatreId()));
				bookingDetail.setDate(ticket.getDate().toString());
				userBookingDetails.add(bookingDetail);
			}
		}
		return userBookingDetails;
	}

	@Override
	public void updateMovieRating(String movieId, String rating) {
		try {
			List lst = movieDao.getMovie(movieId);
			Movie movie = (Movie) lst.get(0);
			int usr = movie.getUserCount();
			double usrRating = movie.getUserRating();
			if (rating != null && rating != "") {
				double tmpRating = Double.parseDouble(rating);
				System.out.println("Parsed Rating:" + tmpRating);
				double avg = ((usrRating * usr) + (tmpRating)) / (++usr);
				movie.setUserRating(avg);
				movie.setUserCount(usr);
				getHibernateTemplate().merge(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List getTopRatedMovies() {
		return movieDao.getTopRatedMovies();
	}

}
