package com.movieticketing.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.movieticketing.bo.TheatreBO;
import com.movieticketing.common.ResultBean;
import com.movieticketing.common.ShowDetails;
import com.movieticketing.common.TheatreDetails;
import com.movieticketing.dao.TheatreDao;
import com.movieticketing.model.Screens;

@Controller
public class TheatreBOImpl implements TheatreBO {

	@Autowired
	TheatreDao theatreDao;

	@Override
	public ResultBean getTheatre(String userid) {
		ResultBean rb = new ResultBean();
		rb.setRows(theatreDao.getTheatre(userid));
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean createTheatre(TheatreDetails theatre) {
		ResultBean rb = new ResultBean();
		Boolean result = theatreDao.createTheatre(theatre);
		if (result) {
			rb.setStatus(200);
		} else {
			rb.setStatus(400);
		}
		return rb;
	}

	@Override
	public ResultBean createScreen(Screens screens) {
		ResultBean rb = new ResultBean();
		Boolean result = theatreDao.createScreen(screens);
		if (result) {
			rb.setStatus(200);
		} else {
			rb.setStatus(400);
		}
		return rb;
	}

	@Override
	public ResultBean createShows(ShowDetails shows) {
		ResultBean rb = new ResultBean();
		Boolean result = theatreDao.createShows(shows);
		if (result) {
			rb.setStatus(200);
		} else {
			rb.setStatus(400);
		}
		return rb;
	}

	@Override
	public ResultBean getMoviesByTheatre(String theatreId) {
		ResultBean rb = new ResultBean();
		rb.setRows(theatreDao.getMoviesByTheatre(theatreId));
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean getAllMovies() {
		ResultBean rb = new ResultBean();
		rb.setRows(theatreDao.getAllMovies());
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean getAllTheatres() {
		ResultBean rb = new ResultBean();
		rb.setRows(theatreDao.getAllTheatres());
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean getScreensOfTheatre(String theatreId) {
		ResultBean rb = new ResultBean();
		rb.setRows(theatreDao.getScreensOfTheatre(theatreId));
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean getTheatreIdForMovie(String movieId) {
		ResultBean rb = new ResultBean();
		rb.setRows(theatreDao.getTheatreIdForMovie(movieId));
		rb.setStatus(200);
		return rb;
	}

	@Override
	public ResultBean getShowsForMovieAndTheatre(String movieId, String theatreId) {
		ResultBean rb = new ResultBean();
		List rows = new ArrayList<>();
		rows.add(theatreDao.getShowsForMovieAndTheatre(movieId, theatreId));
		rb.setRows(rows);
		rb.setStatus(200);
		return rb;
	}

}
