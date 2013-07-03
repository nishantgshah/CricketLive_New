package com.khushnish.cricketlive.webservice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.khushnish.cricketlive.LiveScoreTabs;
import com.khushnish.cricketlive.MainActivity;
import com.khushnish.cricketlive.R;
import com.khushnish.cricketlive.model.ScorecardModel;

public class WSLiveScoreBoard {
	
	private String TAG = this.getClass().getSimpleName();
	private ProgressDialog pd;
	
	private ArrayList<ScorecardModel> scorecardModels;
	private LiveScoreTabs liveScoreTabs;
	
	public void executeService( MainActivity context, LiveScoreTabs liveScoreTabs) {
		scorecardModels = new ArrayList<ScorecardModel>();
		this.liveScoreTabs = liveScoreTabs;
		
		try {
			final StringBuilder sb = new StringBuilder()
			.append(context.getString(R.string.server_url))
			.append("?q=")
			.append(URLEncoder.encode(context
					.getString(R.string.ws_live_score_summary), "UTF-8"))
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
						final JSONObject jsonScorecard = jsonResults.optJSONObject("Scorecard");
						
						if ( jsonScorecard == null ) {
							final JSONArray jsonScorecardArray = jsonResults.optJSONArray("Scorecard");
							
							for (int i = 0; i < jsonScorecardArray.length(); i++) {
								parseScorecardResponse(jsonScorecardArray.optJSONObject(i));
							}
						} else {
							parseScorecardResponse(jsonScorecard);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		liveScoreTabs.updateScores(scorecardModels);
	}
	
	private void parseScorecardResponse( JSONObject jsonScorecard ) {
		if ( jsonScorecard != null) {
			final ScorecardModel scorecardModel = new ScorecardModel();
			scorecardModel.setMatchId(jsonScorecard.optInt("mid"));
			scorecardModel.setMatchName(jsonScorecard.optString("mn"));
			scorecardModel.setMatchStatus(jsonScorecard.optString("ms"));
			
			//		Team Details
			final JSONArray jsonTeams = jsonScorecard.optJSONArray("teams");
			JSONObject jsonTeam1;
			JSONObject jsonTeam2;
			
			if ( jsonTeams != null ) {
				
				jsonTeam1 = jsonTeams.optJSONObject(0);
				jsonTeam2 = jsonTeams.optJSONObject(1);
				
				if ( jsonTeam1 != null ) {
					scorecardModel.setTeam1Id(Integer.parseInt(jsonTeam1.optString("i")));
					scorecardModel.setTeam1Name(jsonTeam1.optString("fn"));
					scorecardModel.setTeam1ShortName(jsonTeam1.optString("sn"));
					
					final JSONObject jsonFlag = jsonTeam1.optJSONObject("flag");
					if ( jsonFlag != null ) {
						scorecardModel.setTeam1RoundSmallFlag(jsonFlag.optString("roundstd"));
					}
				}
				
				if ( jsonTeam2 != null ) {
					scorecardModel.setTeam2Id(Integer.parseInt(jsonTeam2.optString("i")));
					scorecardModel.setTeam2Name(jsonTeam2.optString("fn"));
					scorecardModel.setTeam2ShortName(jsonTeam2.optString("sn"));
					
					final JSONObject jsonFlag = jsonTeam2.optJSONObject("flag");
					if ( jsonFlag != null ) {
						scorecardModel.setTeam2RoundSmallFlag(jsonFlag.optString("roundstd"));
					}
				}
			}
			
			JSONObject jsonPastIngs = jsonScorecard.optJSONObject("past_ings");
			
			if ( jsonPastIngs == null ) {
				JSONArray jsonArray = jsonScorecard.optJSONArray("past_ings");
				if ( jsonArray != null ) {
					jsonPastIngs = jsonArray.optJSONObject(0);
			
					if ( jsonPastIngs != null ) {
						parsePastInnigs(jsonPastIngs, scorecardModel);
					}
				}
			} else {
				parsePastInnigs(jsonPastIngs, scorecardModel);
			}
			
			final JSONObject jsonToss = jsonScorecard.optJSONObject("toss");
			if ( jsonToss != null ) {
				int win = jsonToss.optInt("win");
				int bat = jsonToss.optInt("bat");
				
				String toss = "";
				if ( scorecardModel.getTeam1Id() == win ) {
					toss = scorecardModel.getTeam1Name() + " won the toss and elected to ";
				} else {
					toss = scorecardModel.getTeam2Name() + " won the toss and elected to ";
				}
				
				if ( bat == 0 ) {
					toss += "ball first";
				} else {
					toss += "bat first";
				}
				
				scorecardModel.setTossDetails(toss);
			}
			
			final JSONObject jsonResult = jsonScorecard.optJSONObject("result");
			
			if ( jsonResult != null ) {
				String by = jsonResult.optString("by");
				
				if ( by.equalsIgnoreCase("") ) {
					scorecardModel.setResult("Match " + jsonResult.optString("how"));
				} else {
					if ( scorecardModel.getTeam1Id() == jsonResult.optInt("winner")) {
						scorecardModel.setResult(scorecardModel.getTeam1ShortName() + " won by " + by + " " + jsonResult.optString("how"));
					} else {
						scorecardModel.setResult(scorecardModel
								.getTeam2ShortName()
								+ " won by "
								+ by
								+ " "
								+ jsonResult.optString("how"));
					}
				}
			}
			scorecardModels.add(scorecardModel);
		}
	}
	
	private void parsePastInnigs(JSONObject jsonPastIngs, ScorecardModel scorecardModel) {

		final JSONObject jsonS = jsonPastIngs.optJSONObject("s");
		final JSONObject jsonD = jsonPastIngs.optJSONObject("d");
		
		if ( jsonS != null ) {
			scorecardModel.setInningId(jsonS.optInt("i"));
			final JSONObject jsonA = jsonS.optJSONObject("a");
			
			if ( jsonA != null ) {
				scorecardModel.setCurrentPlayingId(jsonA.optInt("i"));
				scorecardModel.setOvers(jsonA.optString("o"));
				scorecardModel.setRunRate(jsonA.optString("cr"));
				scorecardModel.setRuns(jsonA.optInt("r"));
				scorecardModel.setWickets(jsonA.optInt("w"));
				
				scorecardModel.setRequiredRunRate(jsonA.optString("rr"));
				scorecardModel.setRequiredRun(jsonA.optInt("ru"));
				scorecardModel.setRequiredOver(jsonA.optString("ro"));
				scorecardModel.setTarget(jsonA.optInt("tg"));
				
				final JSONObject jsonCp = jsonA.optJSONObject("cp");
				
				if ( jsonCp != null ) {
					scorecardModel.setPartnershipRuns(jsonCp.optInt("cp"));
					scorecardModel.setPartnershipBalls(jsonCp.optInt("bls"));
				}
			}
		}
		
		if ( jsonD != null ) {
			final JSONObject jsonA = jsonD.optJSONObject("a");
			
			if ( jsonA != null ) {
				final JSONArray jsonT = jsonA.optJSONArray("t");
				if ( jsonT != null ) {
					scorecardModel.setPlayer1Name(jsonT.optJSONObject(0).optString("name"));
					scorecardModel.setPlayer1Runs(jsonT.optJSONObject(0).optInt("r"));
					scorecardModel.setPlayer1Balls(jsonT.optJSONObject(0).optInt("b"));
					scorecardModel.setPlayer1Fours(jsonT.optJSONObject(0).optInt("four"));
					scorecardModel.setPlayer1Sixes(jsonT.optJSONObject(0).optInt("six"));
					
					scorecardModel.setPlayer2Name(jsonT.optJSONObject(1).optString("name"));
					scorecardModel.setPlayer2Runs(jsonT.optJSONObject(1).optInt("r"));
					scorecardModel.setPlayer2Balls(jsonT.optJSONObject(1).optInt("b"));
					scorecardModel.setPlayer2Fours(jsonT.optJSONObject(1).optInt("four"));
					scorecardModel.setPlayer2Sixes(jsonT.optJSONObject(1).optInt("six"));
				} else {
					final JSONObject jsonPlayer = jsonA.optJSONObject("t");
					if ( jsonPlayer!= null ) {
						scorecardModel.setPlayer1Name(jsonPlayer.optString("name"));
						scorecardModel.setPlayer1Runs(jsonPlayer.optInt("r"));
						scorecardModel.setPlayer1Balls(jsonPlayer.optInt("b"));
						scorecardModel.setPlayer1Fours(jsonPlayer.optInt("four"));
						scorecardModel.setPlayer1Sixes(jsonPlayer.optInt("six"));
					}
				}
			}
		}
	
	}
}