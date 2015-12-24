package com.movieticketing.dao;

import java.util.List;

import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;

public interface UserDao {

	TicketConfirmationDetails confirmTicket(TicketConfirmationDetails ticket);

	Boolean createUser(UserDetails user);

	List getUser(String userId);

	List getUserBookingDetails(String userId);

	void updateMovieRating(String movieId, String rating);

	List getTopRatedMovies();

}
