package com.movieticketing.bo;

import com.movieticketing.common.ResultBean;
import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;

public interface UserBO {

	ResultBean confirmTicket(TicketConfirmationDetails ticket);

	ResultBean createUser(UserDetails user);

	ResultBean getUser(String userId);

	ResultBean getUserBookingDetails(String userId);

	ResultBean updateMovieRating(String movieId, String rating);

	ResultBean getTopRatedMovies();
}
