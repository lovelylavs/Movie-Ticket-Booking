package com.movieticketing.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movieticketing.bo.UserBO;
import com.movieticketing.common.MemcachedInstance;
import com.movieticketing.common.ResultBean;
import com.movieticketing.common.TicketConfirmationDetails;
import com.movieticketing.common.UserDetails;
import com.movieticketing.memcached.MemcachedClient;

@Component
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Autowired
	UserBO userBO;

	MemcachedClient memcachedClient = MemcachedInstance.getMemcachedMovieInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmTicket(TicketConfirmationDetails ticket) {
		ResultBean result = userBO.confirmTicket(ticket);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserDetails user) {
		ResultBean result = userBO.createUser(user);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("{userId}")
	public Response getMovie(@PathParam("userId") String userId) {
		System.out.println("userId:" + userId);
		ResultBean result = null;
		if (memcachedClient != null && memcachedClient.get(userId) != null) {
			System.out.println("Getting user from cache");
			result = (ResultBean) memcachedClient.get(userId);
		} else {
			System.out.println("Retrieving data for user from DB");
			result = userBO.getUser(userId);
			memcachedClient.add(userId, result);
		}
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/booking/{userId}")
	public Response getBookingDetails(@PathParam("userId") String userId) {
		System.out.println("/booking/{userId}:" + userId);
		ResultBean result = userBO.getUserBookingDetails(userId);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/ratemovie")
	public Response rateMovie(@QueryParam(value = "movieId") String movieId,
			@QueryParam(value = "rating") String rating) {
		System.out.println("MovieId: " + movieId + " Rating: " + rating);
		ResultBean result = userBO.updateMovieRating(movieId, rating);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/topratedmovies")
	public Response getTopRatedMovies() {
		ResultBean result = userBO.getTopRatedMovies();
		return Response.status(result.getStatus()).entity(result).build();
	}

}
