package com.movieticketing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "screens", catalog = "movie_ticketing")
public class Screens implements Serializable {

	String theatreId;
	String screen;
	Integer seat;

	@Id
	@Column(name = "theatreid", unique = true, nullable = false)
	public String getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}

	@Id
	@Column(name = "screen", nullable = false)
	public String getScreen() {
		return screen;
	}

	public void setScreen(String screenId) {
		this.screen = screenId;
	}

	@Column(name = "seats", nullable = false)
	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

}
