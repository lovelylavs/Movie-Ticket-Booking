package com.movieticketing.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "shows", catalog = "movie_ticketing", uniqueConstraints = { @UniqueConstraint(columnNames = "theatreid") })
public class Shows implements Serializable {

	String theatreId;
	String screen;
	String showName;
	String movieId;
	Date date;
	Integer seat;

	@Id
	@Column(name = "theatreid", unique = true, nullable = false)
	public String getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(String userId) {
		this.theatreId = userId;
	}

	@Id
	@Column(name = "screen", nullable = false)
	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	@Id
	@Column(name = "showname", nullable = false)
	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	@Id
	@Column(name = "movieid", nullable = false)
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieid) {
		this.movieId = movieid;
	}

	@Id
	@Column(name = "date", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "seats", nullable = false)
	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

}
