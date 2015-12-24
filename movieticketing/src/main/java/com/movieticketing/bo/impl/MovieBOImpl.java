package com.movieticketing.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.movieticketing.bo.MovieBO;
import com.movieticketing.common.ResultBean;
import com.movieticketing.dao.MovieDao;
import com.movieticketing.model.Movie;

/**
 * Created by nagal_000 on 12/13/2015.
 */
@Controller
public class MovieBOImpl implements MovieBO {

    @Autowired
    MovieDao movieDao;

    @Override
    public ResultBean createMovie(Movie movie) {
        ResultBean rb = new ResultBean();
        Boolean result =  movieDao.createMovie(movie);
        if(result){
            rb.setStatus(200);
        }
        else {
            rb.setStatus(400);
        }
        return rb;
    }

    @Override
    public ResultBean getMovie(String movieId) {
        ResultBean rb = new ResultBean();
        rb.setRows(movieDao.getMovie(movieId));
        rb.setStatus(200);
        return rb;
    }

	@Override
	public ResultBean getAllMovie() {
		ResultBean rb = new ResultBean();
        rb.setRows(movieDao.getAllMovies());
        rb.setStatus(200);
        return rb;
	}
}
