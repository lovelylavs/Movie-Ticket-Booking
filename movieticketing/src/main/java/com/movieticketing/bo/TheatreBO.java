package com.movieticketing.bo;

import com.movieticketing.common.ResultBean;
import com.movieticketing.common.ShowDetails;
import com.movieticketing.common.TheatreDetails;
import com.movieticketing.model.Screens;

public interface TheatreBO {
	
	ResultBean getTheatre(String userid);
	
	ResultBean createTheatre(TheatreDetails theatre);

	ResultBean createScreen(Screens screens);

	ResultBean createShows(ShowDetails shows);

	ResultBean getMoviesByTheatre(String theatreId);

	ResultBean getAllMovies();

	ResultBean getAllTheatres();

	ResultBean getScreensOfTheatre(String theatreId);

	ResultBean getTheatreIdForMovie(String movieId);

	ResultBean getShowsForMovieAndTheatre(String movieId, String theatreId);

}
