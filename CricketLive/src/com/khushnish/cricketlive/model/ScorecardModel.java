package com.khushnish.cricketlive.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ScorecardModel implements Parcelable {

	private int team1Id = 0;
	private int team2Id = 0;
	private int currentPlayingId = 0;
	private String team1Name = "";
	private String team2Name = "";
	private String team1ShortName = "";
	private String team2ShortName = "";
	private int runs = 0;
	private int wickets = 0;
	private String overs = "";
	private String runRate = "";
	private String player1Name = "";
	private int player1Runs = 0;
	private int player1Balls = 0;
	private int player1Fours = 0;
	private int player1Sixes = 0;
	private String player2Name = "";
	private int player2Runs = 0;
	private int player2Balls = 0;
	private int player2Fours = 0;
	private int player2Sixes = 0;
	private int partnershipRuns = 0;
	private int partnershipBalls = 0;
	private String result = "";
	private int matchId = 0;
	private String matchName = "";
	private String tossDetails = "";
	private String team1RoundSmallFlag = "";
	private String team2RoundSmallFlag = "";
	private String requiredRunRate = "";
	private int requiredRun = 0;
	private String requiredOver = "";
	private int target = 0;
	private String matchStatus = "";
	private int inningId = 0;
	
	public ScorecardModel() {
		
	}
	
	public int getTeam1Id() {
		return team1Id;
	}

	public void setTeam1Id(int team1Id) {
		this.team1Id = team1Id;
	}

	public int getTeam2Id() {
		return team2Id;
	}

	public void setTeam2Id(int team2Id) {
		this.team2Id = team2Id;
	}

	public int getCurrentPlayingId() {
		return currentPlayingId;
	}

	public void setCurrentPlayingId(int currentPlayingId) {
		this.currentPlayingId = currentPlayingId;
	}

	public String getTeam1Name() {
		return team1Name;
	}

	public void setTeam1Name(String team1Name) {
		this.team1Name = team1Name;
	}

	public String getTeam2Name() {
		return team2Name;
	}

	public void setTeam2Name(String team2Name) {
		this.team2Name = team2Name;
	}

	public String getTeam1ShortName() {
		return team1ShortName;
	}

	public void setTeam1ShortName(String team1ShortName) {
		this.team1ShortName = team1ShortName;
	}

	public String getTeam2ShortName() {
		return team2ShortName;
	}

	public void setTeam2ShortName(String team2ShortName) {
		this.team2ShortName = team2ShortName;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public String getOvers() {
		return overs;
	}

	public void setOvers(String overs) {
		this.overs = overs;
	}

	public String getRunRate() {
		return runRate;
	}

	public void setRunRate(String runRate) {
		this.runRate = runRate;
	}

	public String getPlayer1Name() {
		return player1Name;
	}

	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}

	public int getPlayer1Runs() {
		return player1Runs;
	}

	public void setPlayer1Runs(int player1Runs) {
		this.player1Runs = player1Runs;
	}

	public int getPlayer1Balls() {
		return player1Balls;
	}

	public void setPlayer1Balls(int player1Balls) {
		this.player1Balls = player1Balls;
	}

	public int getPlayer1Fours() {
		return player1Fours;
	}

	public void setPlayer1Fours(int player1Fours) {
		this.player1Fours = player1Fours;
	}

	public int getPlayer1Sixes() {
		return player1Sixes;
	}

	public void setPlayer1Sixes(int player1Sixes) {
		this.player1Sixes = player1Sixes;
	}

	public String getPlayer2Name() {
		return player2Name;
	}

	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}

	public int getPlayer2Runs() {
		return player2Runs;
	}

	public void setPlayer2Runs(int player2Runs) {
		this.player2Runs = player2Runs;
	}

	public int getPlayer2Balls() {
		return player2Balls;
	}

	public void setPlayer2Balls(int player2Balls) {
		this.player2Balls = player2Balls;
	}

	public int getPlayer2Fours() {
		return player2Fours;
	}

	public void setPlayer2Fours(int player2Fours) {
		this.player2Fours = player2Fours;
	}

	public int getPlayer2Sixes() {
		return player2Sixes;
	}

	public void setPlayer2Sixes(int player2Sixes) {
		this.player2Sixes = player2Sixes;
	}

	public int getPartnershipRuns() {
		return partnershipRuns;
	}

	public void setPartnershipRuns(int partnershipRuns) {
		this.partnershipRuns = partnershipRuns;
	}

	public int getPartnershipBalls() {
		return partnershipBalls;
	}

	public void setPartnershipBalls(int partnershipBalls) {
		this.partnershipBalls = partnershipBalls;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public String getTossDetails() {
		return tossDetails;
	}

	public void setTossDetails(String tossDetails) {
		this.tossDetails = tossDetails;
	}

	public String getTeam1RoundSmallFlag() {
		return team1RoundSmallFlag;
	}

	public void setTeam1RoundSmallFlag(String team1RoundSmallFlag) {
		this.team1RoundSmallFlag = team1RoundSmallFlag;
	}

	public String getTeam2RoundSmallFlag() {
		return team2RoundSmallFlag;
	}

	public void setTeam2RoundSmallFlag(String team2RoundSmallFlag) {
		this.team2RoundSmallFlag = team2RoundSmallFlag;
	}
	
	public String getRequiredRunRate() {
		return requiredRunRate;
	}

	public void setRequiredRunRate(String requiredRunRate) {
		this.requiredRunRate = requiredRunRate;
	}

	public int getRequiredRun() {
		return requiredRun;
	}

	public void setRequiredRun(int requiredRun) {
		this.requiredRun = requiredRun;
	}

	public String getRequiredOver() {
		return requiredOver;
	}

	public void setRequiredOver(String requiredOver) {
		this.requiredOver = requiredOver;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	public int getInningId() {
		return inningId;
	}

	public void setInningId(int inningId) {
		this.inningId = inningId;
	}

	protected ScorecardModel(Parcel in) {
		team1Id = in.readInt();
		team2Id = in.readInt();
		currentPlayingId = in.readInt();
		team1Name = in.readString();
		team2Name = in.readString();
		team1ShortName = in.readString();
		team2ShortName = in.readString();
		runs = in.readInt();
		wickets = in.readInt();
		overs = in.readString();
		runRate = in.readString();
		player1Name = in.readString();
		player1Runs = in.readInt();
		player1Balls = in.readInt();
		player1Fours = in.readInt();
		player1Sixes = in.readInt();
		player2Name = in.readString();
		player2Runs = in.readInt();
		player2Balls = in.readInt();
		player2Fours = in.readInt();
		player2Sixes = in.readInt();
		partnershipRuns = in.readInt();
		partnershipBalls = in.readInt();
		result = in.readString();
		matchId = in.readInt();
		matchName = in.readString();
		tossDetails = in.readString();
		team1RoundSmallFlag = in.readString();
		team2RoundSmallFlag = in.readString();
		requiredRunRate = in.readString();
		requiredRun = in.readInt();
		requiredOver = in.readString();
		target = in.readInt();
		matchStatus = in.readString();
		inningId = in.readInt();
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(team1Id);
		dest.writeInt(team2Id);
		dest.writeInt(currentPlayingId);
		dest.writeString(team1Name);
		dest.writeString(team2Name);
		dest.writeString(team1ShortName);
		dest.writeString(team2ShortName);
		dest.writeInt(runs);
		dest.writeInt(wickets);
		dest.writeString(overs);
		dest.writeString(runRate);
		dest.writeString(player1Name);
		dest.writeInt(player1Runs);
		dest.writeInt(player1Balls);
		dest.writeInt(player1Fours);
		dest.writeInt(player1Sixes);
		dest.writeString(player2Name);
		dest.writeInt(player2Runs);
		dest.writeInt(player2Balls);
		dest.writeInt(player2Fours);
		dest.writeInt(player2Sixes);
		dest.writeInt(partnershipRuns);
		dest.writeInt(partnershipBalls);
		dest.writeString(result);
		dest.writeInt(matchId);
		dest.writeString(matchName);
		dest.writeString(tossDetails);
		dest.writeString(team1RoundSmallFlag);
		dest.writeString(team2RoundSmallFlag);
		dest.writeString(requiredRunRate);
		dest.writeInt(requiredRun);
		dest.writeString(requiredOver);
		dest.writeInt(target);
		dest.writeString(matchStatus);
		dest.writeInt(inningId);
	}

	public static final Parcelable.Creator<ScorecardModel> CREATOR = new Parcelable.Creator<ScorecardModel>() {
		public ScorecardModel createFromParcel(Parcel in) {
			return new ScorecardModel(in);
		}

		public ScorecardModel[] newArray(int size) {
			return new ScorecardModel[size];
		}
	};
}