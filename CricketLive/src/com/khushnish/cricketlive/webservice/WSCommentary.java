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
import com.khushnish.cricketlive.MainActivity;
import com.khushnish.cricketlive.R;
import com.khushnish.cricketlive.fragments.CommentaryFragment;
import com.khushnish.cricketlive.model.CommentaryBallsModel;

public class WSCommentary {
	
	private String TAG = this.getClass().getSimpleName();
	private ProgressDialog pd;
	private CommentaryFragment commentaryFragment;
	private ArrayList<CommentaryBallsModel> commentaryBallsModels;
	
	public void executeService(CommentaryFragment fragmentActivity, int start, int end, int matchId, int inningId) {
		commentaryFragment = fragmentActivity;
		Log.i(TAG, "start : " + start + ", end :" + end);
		commentaryBallsModels = new ArrayList<CommentaryBallsModel>();
		try {
			final StringBuilder sb = new StringBuilder()
			.append(fragmentActivity.getString(R.string.server_url))
			.append("?q=")
			.append(URLEncoder.encode(fragmentActivity
					.getString(R.string.ws_commentary, start, end, matchId, inningId), "UTF-8"))
			.append(fragmentActivity.getString(R.string.format))
			.append(URLEncoder.encode(
					fragmentActivity.getString(R.string.formatValue),
					"UTF-8"))
			.append(fragmentActivity.getString(R.string.env))
			.append(URLEncoder.encode(
					fragmentActivity.getString(R.string.envValue),
					"UTF-8"));
			pd = ProgressDialog.show(fragmentActivity, "", "Please Wait...");
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
						final JSONArray jsonOvers = jsonResults.optJSONArray("Over");
						JSONObject jsonOver;
						if ( jsonOvers != null ) {
							for (int i = 0; i < jsonOvers.length(); ++i) {
								jsonOver = jsonOvers.getJSONObject(i);
								parseOvers(jsonOver);
							}
						} else {
							jsonOver = jsonQuery.optJSONObject("Over");
							parseOvers(jsonOver);
						}
					}
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		commentaryFragment.updateCommentary(commentaryBallsModels);
	}
	
	private void parseOvers(JSONObject jsonOver) {
		
		if ( jsonOver != null ) {
			
			int overNumber = jsonOver.optInt("num");
			
			final JSONArray jsonBalls = jsonOver.optJSONArray("Ball");
			
			JSONObject jsonBall;
			if ( jsonBalls != null ) {
				for (int i = 0; i < jsonBalls.length(); ++i) {
					jsonBall = jsonBalls.optJSONObject(i);
					commentaryBallsModels.add(parseBalls(jsonBall, overNumber));
				}
			} else {
				jsonBall = jsonOver.optJSONObject("Ball");
				commentaryBallsModels.add(parseBalls(jsonBall, overNumber));
			}
		}
		Log.e("WSCommentary", "WS commentary size : " + commentaryBallsModels.size());
		commentaryBallsModels.trimToSize();
	}
	
	private CommentaryBallsModel parseBalls(JSONObject jsonBall, int overNumber) {
		final CommentaryBallsModel commentaryBallsModel = new CommentaryBallsModel();
		if ( jsonBall != null ) {
			commentaryBallsModel.setOverNumber(overNumber);
			commentaryBallsModel.setBallTYpe(jsonBall.optString("type"));
			commentaryBallsModel.setCommentry(jsonBall.optString("c"));
			commentaryBallsModel.setRuns(jsonBall.optInt("r"));
			commentaryBallsModel.setBallNumber(jsonBall.optInt("n"));
		}
		return commentaryBallsModel;
	}
}