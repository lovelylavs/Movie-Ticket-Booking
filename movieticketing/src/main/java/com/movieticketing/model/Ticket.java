package com.movieticketing.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket", catalog = "movie_ticketing")
public class Ticket implements Serializable {

	String userId;
	String theatreId;
	String screen;
	String show;
	String movieId;
	Date date;
	Integer seatCount;
	String confCode;

	@Column(name = "userid", nullable = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "theatreid", nullable = false)
	public String getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}

	@Column(name = "screen", nullable = false)
	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	@Column(name = "showname", nullable = false)
	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	@Column(name = "movieid", nullable = false)
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	@Column(name = "date", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "seatcount", nullable = false)
	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	@Id
	@Column(name = "confcode", nullable = false)
	public String getConfCode() {
		return confCode;
	}

	public void setConfCode(String confCode) {
		this.confCode = confCode;
	}

}
