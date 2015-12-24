package com.movieticketing.common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MovieTheatreShows implements Serializable {

	String movieId;
	String theatreId;
	Map<String, TreeSet<Date>> screenDate = new HashMap<String, TreeSet<Date>>();

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}

	public Map<String, TreeSet<Date>> getScreenDate() {
		return screenDate;
	}

	public void setScreenDate(Map<String, TreeSet<Date>> screenDate) {
		this.screenDate = screenDate;
	}

	public void addScreenDate(String screen, Date date) {
		if (screenDate.containsKey(screen)) {
			screenDate.get(screen).add(date);
		} else {
			TreeSet<Date> dates = new TreeSet<Date>();
			dates.add(date);
			screenDate.put(screen, dates);
		}
	}

	@Override
	public String toString() {
		return "MovieTheatreShows [movieId=" + movieId + ", theatreId=" + theatreId + ", screenDate=" + screenDate
				+ "]";
	}

}
