package com.khushnish.cricketlive.model;

import java.util.ArrayList;

public class MatchModel {

	private String groupName = "";
	private long matchId = 0;
	private String matchType = "";
	private long venueId = 0;
	private String venue = "";
	private long seriesId = 0;
	private String seriesName = "";
	private String matchStatus = "";
	private String matchNumber = "";
	private String startDate = "";
	private String endDate = "";
	private String matchTimeSpan = "";
	private ArrayList<TeamModel> teamModels = new ArrayList<TeamModel>();
	private String result = "";
	private long dateMatchStart = 0;
	private long dateMatchEnd = 0;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(long seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	public String getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(String matchNumber) {
		this.matchNumber = matchNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMatchTimeSpan() {
		return matchTimeSpan;
	}

	public void setMatchTimeSpan(String matchTimeSpan) {
		this.matchTimeSpan = matchTimeSpan;
	}

	public ArrayList<TeamModel> getTeamModels() {
		return teamModels;
	}

	public void setTeamModels(ArrayList<TeamModel> teamModels) {
		this.teamModels = teamModels;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getDateMatchStart() {
		return dateMatchStart;
	}

	public void setDateMatchStart(long dateMatchStart) {
		this.dateMatchStart = dateMatchStart;
	}

	public long getDateMatchEnd() {
		return dateMatchEnd;
	}

	public void setDateMatchEnd(long dateMatchEnd) {
		this.dateMatchEnd = dateMatchEnd;
	}

}