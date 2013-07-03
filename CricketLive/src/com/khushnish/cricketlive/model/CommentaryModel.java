package com.khushnish.cricketlive.model;

import java.util.ArrayList;

public class CommentaryModel {

	private int overNumber = 0;
	private ArrayList<CommentaryBallsModel> commentaryBallsModels =
			new ArrayList<CommentaryBallsModel>();

	public int getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(int overNumber) {
		this.overNumber = overNumber;
	}

	public ArrayList<CommentaryBallsModel> getCommentaryBallsModels() {
		return commentaryBallsModels;
	}

	public void setCommentaryBallsModels(
			ArrayList<CommentaryBallsModel> commentaryBallsModels) {
		this.commentaryBallsModels = commentaryBallsModels;
	}

}