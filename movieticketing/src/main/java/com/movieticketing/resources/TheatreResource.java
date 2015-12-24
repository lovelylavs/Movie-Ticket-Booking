package com.movieticketing.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movieticketing.bo.TheatreBO;
import com.movieticketing.common.MemcachedInstance;
import com.movieticketing.common.ResultBean;
import com.movieticketing.common.ShowDetails;
import com.movieticketing.common.TheatreDetails;
import com.movieticketing.memcached.MemcachedClient;
import com.movieticketing.model.Screens;

@Component
@Path("/theatre")
@Produces(MediaType.APPLICATION_JSON)
public class TheatreResource {

	@Autowired
	TheatreBO theatreBO;

	MemcachedClient memcachedClient = MemcachedInstance.getMemcachedMovieInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTheatre(TheatreDetails theatre) {
		ResultBean result = theatreBO.createTheatre(theatre);
		if (memcachedClient != null && memcachedClient.get("getAllTheatres") != null) {
			memcachedClient.delete("getAllTheatres");
		}
		if (memcachedClient != null && memcachedClient.get("getAllMoviesInTheatres") != null) {
			memcachedClient.delete("getAllMoviesInTheatres");
		}
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("{theatreId}")
	public Response getTheatre(@PathParam("theatreId") String theatreId) {
		System.out.println("Get details for theatre with id:" + theatreId);
		ResultBean result = null;
		if (memcachedClient != null && memcachedClient.get(theatreId) != null) {
			System.out.println("Getting theatre details from cache");
			result = (ResultBean) memcachedClient.get(theatreId);
		} else {
			System.out.println("Retrieving theatre details from DB");
			result = theatreBO.getTheatre(theatreId);
			memcachedClient.add(theatreId, result);
		}
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/screens/{theatreId}")
	public Response getScreensOfTheatre(@PathParam("theatreId") String theatreId) {
		System.out.println("Get details for screens from the theatre with id:" + theatreId);
		ResultBean result = theatreBO.getScreensOfTheatre(theatreId);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	public Response getAllTheatre() {
		ResultBean result = null;
		if (memcachedClient != null && memcachedClient.get("getAllTheatres") != null) {
			System.out.println("Getting all movies from cache");
			result = (ResultBean) memcachedClient.get("getAllTheatres");
		} else {
			result = theatreBO.getAllTheatres();
			memcachedClient.add("getAllTheatres", result);
		}
		return Response.status(result.getStatus()).entity(result).build();
	}

	@POST
	@Path("/screens")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createScreen(Screens screens) {
		ResultBean result = theatreBO.createScreen(screens);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@POST
	@Path("/shows")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createShows(ShowDetails shows) {
		ResultBean result = theatreBO.createShows(shows);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/movies/{theatreId}")
	public Response getMovies(@PathParam("theatreId") String theatreId) {
		System.out.println("Get all the movies running in a theatre:" + theatreId);
		ResultBean result = theatreBO.getMoviesByTheatre(theatreId);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/movies")
	public Response getAllMovies() {
		System.out.println("Get all the movies running in all theatres");
		ResultBean result = null;
		if (memcachedClient != null && memcachedClient.get("getAllMoviesInTheatres") != null) {
			System.out.println("Getting all movies for a theatre from cache");
			result = (ResultBean) memcachedClient.get("getAllMoviesInTheatres");
		} else {
			result = theatreBO.getAllMovies();
			memcachedClient.add("getAllMoviesInTheatres", result);
		}
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/shows/{movieId}")
	public Response getTheatreIdForMovie(@PathParam("movieId") String movieId) {
		System.out.println("Get theatres for a movie");
		ResultBean result = theatreBO.getTheatreIdForMovie(movieId);
		return Response.status(result.getStatus()).entity(result).build();
	}

	@GET
	@Path("/shows/{movieId}/{theatreId}")
	public Response getShowsforMovieId(@PathParam("movieId") String movieId, @PathParam("theatreId") String theatreId) {
		System.out.println("Get show details for movieId and theatreId");
		ResultBean result = theatreBO.getShowsForMovieAndTheatre(movieId, theatreId);
		return Response.status(result.getStatus()).entity(result).build();
	}

}
