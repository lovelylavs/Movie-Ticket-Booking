package com.movieticketing.dao;

import com.movieticketing.model.Movie;

import java.util.List;

/**
 * Created by nagal_000 on 12/13/2015.
 */
public interface MovieDao {

	Boolean createMovie(Movie movie);

	List getMovie(String movieId);

	List getAllMovies();

	String getMovieNameById(String movieId);

	List getTopRatedMovies();

}
