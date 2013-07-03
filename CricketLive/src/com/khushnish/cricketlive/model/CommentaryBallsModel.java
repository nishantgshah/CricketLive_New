package com.khushnish.cricketlive.model;

public class CommentaryBallsModel {

	private String ballTYpe = "";
	private int runs = 0;
	private int ballNumber = 0;
	private String commentry = "";
	private int overNumber = 0;

	public String getBallTYpe() {
		return ballTYpe;
	}

	public void setBallTYpe(String ballTYpe) {
		this.ballTYpe = ballTYpe;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getBallNumber() {
		return ballNumber;
	}

	public void setBallNumber(int ballNumber) {
		this.ballNumber = ballNumber;
	}

	public String getCommentry() {
		return commentry;
	}

	public void setCommentry(String commentry) {
		this.commentry = commentry;
	}

	public int getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(int overNumber) {
		this.overNumber = overNumber;
	}

}