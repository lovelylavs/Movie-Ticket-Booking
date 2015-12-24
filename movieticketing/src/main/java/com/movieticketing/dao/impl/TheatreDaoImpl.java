package com.movieticketing.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.movieticketing.common.MovieTheatreShows;
import com.movieticketing.common.ShowDetails;
import com.movieticketing.common.TheatreDetails;
import com.movieticketing.dao.TheatreDao;
import com.movieticketing.model.Login;
import com.movieticketing.model.Movie;
import com.movieticketing.model.Screens;
import com.movieticketing.model.Shows;
import com.movieticketing.model.Theatre;

public class TheatreDaoImpl extends HibernateDaoSupport implements TheatreDao {

	@Override
	public List getTheatre(String theatreId) {
		return getHibernateTemplate().find("from Theatre where theatreId = ?", theatreId);
	}

	@Override
	public Boolean createTheatre(TheatreDetails theatre) {
		try {
			Theatre theatreDB = new Theatre();
			theatreDB.setTheatreId(theatre.getTheatreId());
			theatreDB.setName(theatre.getName());
			theatreDB.setLocation(theatre.getLocation());
			theatreDB.setPhone(theatre.getPhone());
			getHibernateTemplate().save(theatreDB);

			Login login = new Login();
			login.setUserId(theatre.getTheatreId());
			login.setPassword(theatre.getPassword());
			login.setRole("TADMIN");
			getHibernateTemplate().save(login);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean createScreen(Screens screens) {
		try {
			getHibernateTemplate().save(screens);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean createShows(ShowDetails shows) {
		List result = getHibernateTemplate().find("from Screens where theatreId = ? and screen = ?",
				shows.getTheatreId(), shows.getScreen());
		try {
			if (result != null && result.size() == 1) {
				Screens screen = (Screens) result.get(0);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = sdf.parse(shows.getStartDate());

				Date endDate = sdf.parse(shows.getEndDate());
				Calendar start = Calendar.getInstance();
				start.setTime(startDate);
				Calendar end = Calendar.getInstance();
				end.setTime(endDate);

				while (!start.after(end)) {
					Date targetDay = start.getTime();
					for (int i = 1; i <= 4; i++) {
						Shows show = new Shows();
						show.setMovieId(shows.getMovieId());
						show.setTheatreId(shows.getTheatreId());
						show.setScreen(shows.getScreen());
						show.setShowName(Integer.toString(i));
						show.setDate(new java.sql.Date(targetDay.getTime()));
						show.setSeat(screen.getSeat());
						getHibernateTemplate().save(show);
					}
					start.add(Calendar.DATE, 1);
				}
				return true;

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List getMoviesByTheatre(String theatreId) {
		List lst = getHibernateTemplate().find(
				"from Movie where movieId in ( select distinct movieId from Shows where theatreId = ?)", theatreId);
		return lst;
	}

	@Override
	public List getAllMovies() {
		List lst = getHibernateTemplate().find("from Movie where movieId in ( select distinct movieId from Shows )");
		return lst;
	}

	@Override
	public List getAllTheatres() {
		return getHibernateTemplate().find("from Theatre");
	}

	@Override
	public List getScreensOfTheatre(String theatreId) {
		List lst = getHibernateTemplate().find("from Screens where theatreId= ? )", theatreId);
		return lst;
	}

	@Override
	public List getTheatreIdForMovie(String movieId) {
		List lst = getHibernateTemplate().find("select distinct theatreId from Shows where movieId= ? and date>= ? )",
				movieId, new Date());
		return lst;
	}

	@Override
	public String getTheatreNameById(String theatreId) {
		List lst = getHibernateTemplate().find("from Theatre where theatreId = ?", theatreId);
		if (lst != null & lst.size() == 1) {
			return ((Theatre) lst.get(0)).getName();
		}
		return null;
	}

	@Override
	public MovieTheatreShows getShowsForMovieAndTheatre(String movieId, String theatreId) {
		List lst = getHibernateTemplate().find(" from Shows where movieId =? and theatreId = ? and date>=?", movieId,
				theatreId, new Date());
		MovieTheatreShows theatreShows = new MovieTheatreShows();
		theatreShows.setMovieId(movieId);
		theatreShows.setTheatreId(theatreId);
		for (Object o : lst) {
			Shows show = (Shows) o;
			theatreShows.addScreenDate(show.getScreen(), show.getDate());
		}
		return theatreShows;
	}

}
