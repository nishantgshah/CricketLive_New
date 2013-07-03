package com.khushnish.cricketlive.webservice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.khushnish.cricketlive.MainActivity;
import com.khushnish.cricketlive.R;
import com.khushnish.cricketlive.model.MatchModel;
import com.khushnish.cricketlive.model.TeamModel;

public class WSRecentMatches {
	
	private String TAG = this.getClass().getSimpleName();
	private ProgressDialog pd;
	
	private ArrayList<MatchModel> matchModels;
	
	public void executeService( Context context ) {
		
		try {
			final StringBuilder sb = new StringBuilder()
			.append(context.getString(R.string.server_url))
			.append("?q=")
			.append(URLEncoder.encode(context
					.getString(R.string.ws_recent_matches), "UTF-8"))
			.append(context.getString(R.string.format))
			.append(URLEncoder.encode(
					context.getString(R.string.formatValue),
					"UTF-8"))
			.append(context.getString(R.string.env))
			.append(URLEncoder.encode(
					context.getString(R.string.envValue),
					"UTF-8"));
			pd = ProgressDialog.show(context, "", "Please Wait...");
			final JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,
					sb.toString(), null, new Response.Listener<JSONObject>() {
			    @Override
			    public void onResponse(JSONObject response) {
			        Log.i(TAG, response.toString());
			        parseResponse(response);
			        pd.dismiss();
			    }
			},new Response.ErrorListener() {
			    @Override
			    public void onErrorResponse(VolleyError error) {
			    	if ( error != null ) {
			    		Log.i(TAG, "error in WS");
			    	}
			    }
			});
			MainActivity.getmRequestQueue().add(jr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void parseResponse( JSONObject response ) {
		try {
			if ( response != null ) {
				final JSONObject jsonQuery = response.optJSONObject("query");
				
				if ( jsonQuery != null ) {
					final JSONObject jsonResults = jsonQuery.optJSONObject("results");
					
					if ( jsonResults != null ) {
						final JSONObject jsonMatch = jsonResults.optJSONObject("Match");
						
						if ( jsonMatch == null ) {
							final JSONArray jsonScorecardArray = jsonResults.optJSONArray("Match");
							
							for (int i = 0; i < jsonScorecardArray.length(); i++) {
								matchModels.add(parseScorecardResponse(jsonScorecardArray.optJSONObject(i)));
							}
						} else {
							matchModels.add(parseScorecardResponse(jsonMatch));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		matchModels.trimToSize();
	}
	
	private MatchModel parseScorecardResponse( JSONObject jsonMatch ) {
		final MatchModel matchModel= new MatchModel();
		
		if ( jsonMatch != null ) {
			matchModel.setGroupName(jsonMatch.optString("group"));
			matchModel.setMatchId(jsonMatch.optLong("matchid"));
			matchModel.setMatchType(jsonMatch.optString("mtype"));
			matchModel.setSeriesId(jsonMatch.optLong("series_id"));
			matchModel.setSeriesName(jsonMatch.optString("series_name"));
			matchModel.setMatchStatus(jsonMatch.optString("status"));
			matchModel.setMatchNumber(jsonMatch.optString("MatchNo"));
			matchModel.setStartDate(jsonMatch.optString("StartDate"));
			matchModel.setEndDate(jsonMatch.optString("EndDate"));
			matchModel.setMatchTimeSpan(jsonMatch.optString("MatchTimeSpan"));
			matchModel.setDateMatchStart(jsonMatch.optLong("date_match_start"));
			matchModel.setDateMatchEnd(jsonMatch.optLong("date_match_end"));
			
			//	Team Details
			final ArrayList<TeamModel> teamModels = new ArrayList<TeamModel>();
			final JSONArray jsonTeamArray = jsonMatch.optJSONArray("Team");
			JSONObject jsonTeam;
			
			if ( jsonTeamArray != null ) {
				for (int i = 0; i < jsonTeamArray.length(); ++i) {
					jsonTeam = jsonTeamArray.optJSONObject(i);
					teamModels.add(parseTeamDetails(jsonTeam));
				}
			}  else {
				jsonTeam = jsonMatch.optJSONObject("Team");
				teamModels.add(parseTeamDetails(jsonTeam));
			}
			
			teamModels.trimToSize();
			matchModel.setTeamModels(teamModels);
			
			//	Venue
			final JSONObject jsonVenue = jsonMatch.optJSONObject("Venue");
			
			if ( jsonVenue != null ) {
				matchModel.setVenueId(jsonVenue.optInt("venueid"));
				matchModel.setVenue(jsonVenue.optString("content"));
			}
			
			//	Result
			
			final JSONObject jsonResult = jsonMatch.optJSONObject("Result");
			
			if ( jsonResult != null ) {
				String by = jsonResult.optString("by");
				
				if ( by.equalsIgnoreCase("") ) {
					matchModel.setResult("Match " + jsonResult.optString("how"));
				} else {
					final JSONArray jsonTeams = jsonResult.optJSONArray("Team");
					
					if ( jsonTeams != null ) {
						String matchWon = "";
						for (int i = 0; i < jsonTeams.length(); ++i) {
							jsonTeam = jsonTeams.optJSONObject(i);
							if ( jsonTeam != null  ) {
								matchWon = jsonTeam.optString("jsonTeam");
								if (matchWon.equalsIgnoreCase("yes")) {
									for (TeamModel teamModel : teamModels) {
										if ( teamModel.getTeamId() == jsonTeam.optInt("id")) {
											matchModel.setResult(teamModel.getTeamName() 
													+ " won by " + by + " " + jsonResult.optString("how"));
											break;
										}
									}
									break;
								}
							}
						}
					}
				}
			}
		}
		
		return matchModel;
	}
	
	private TeamModel parseTeamDetails( JSONObject jsonTeam ) {
		final TeamModel teamModel = new TeamModel();
		if ( jsonTeam != null ) {
			teamModel.setTeamName(jsonTeam.optString("Team"));
			teamModel.setRole(jsonTeam.optString("role"));
			teamModel.setTeamId(jsonTeam.optInt("teamid"));
		}
		return teamModel;
	}
}