package com.movieticketing.dao.impl;

import com.movieticketing.dao.MovieDao;
import com.movieticketing.model.Movie;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagal_000 on 12/13/2015.
 */
public class MovieDaoImpl extends HibernateDaoSupport implements MovieDao {

	@Override
	public Boolean createMovie(Movie movie) {
		try {
			getHibernateTemplate().save(movie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List getMovie(String movieId) {
		return getHibernateTemplate().find("from Movie where movieId = ?", movieId);

	}

	@Override
	public List getAllMovies() {
		return getHibernateTemplate().find("from Movie ");
	}

	@Override
	public String getMovieNameById(String movieId) {
		List lst = getHibernateTemplate().find("from Movie where movieId = ?", movieId);
		if (lst != null & lst.size() == 1) {
			return ((Movie) lst.get(0)).getTitle();
		}
		return null;
	}

	@Override
	public List getTopRatedMovies() {
		List lst = getHibernateTemplate().find("from Movie order by userRating desc");
		List ratedList = new ArrayList();
		for(int i=0;i<10;i++){
			ratedList.add(lst);
		}
		return ratedList;
	}
}
