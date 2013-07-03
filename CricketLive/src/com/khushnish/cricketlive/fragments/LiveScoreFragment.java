package com.khushnish.cricketlive.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.BitmapLruCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.khushnish.cricketlive.MainActivity;
import com.khushnish.cricketlive.R;
import com.khushnish.cricketlive.model.ScorecardModel;
import com.khushnish.cricketlive.utils.Utils;

public class LiveScoreFragment extends Fragment implements OnClickListener {
	
	private ScorecardModel scorecardModel;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {
    	final View view = inflater.inflate(R.layout.fragment_live_score, container, false);
    	scorecardModel = getArguments().getParcelable("scorecard");
    	initializeComponents(view);
    	
        return view; 
    }
    
    private void initializeComponents(View view) {
    	
    	final NetworkImageView imgTeam1Logo = (NetworkImageView)
    			view.findViewById(R.id.fragment_live_score_team1_img_logo);
    	final NetworkImageView imgTeam2Logo = (NetworkImageView)
    			view.findViewById(R.id.fragment_live_score_team2_img_logo);
    	
    	final TextView txtToss = (TextView) view.findViewById(R.id.fragment_live_score_txt_toss);
    	final TextView txtMatchStatus = (TextView) view.findViewById(R.id.fragment_live_score_txt_match_status);
    	final TextView txtTeamName = (TextView) view.findViewById(R.id.fragment_live_score_txt_team_name);
    	
    	final TextView txtScore = (TextView) view.findViewById(R.id.fragment_live_score_txt_score);
    	final TextView txtOvers = (TextView) view.findViewById(R.id.fragment_live_score_txt_overs);
    	final TextView txtRunRate = (TextView) view.findViewById(R.id.fragment_live_score_runrate);
    	
    	final TextView txtPlayer1Name = (TextView) 
    			view.findViewById(R.id.fragment_live_score_txt_player1_name);
    	final TextView txtPlayer1Score = (TextView)
    			view.findViewById(R.id.fragment_live_score_txt_player1_runs);
    	final TextView txtPlayer1FoursAndSixes = (TextView)
    			view.findViewById(R.id.fragment_live_score_txt_player1_fours_sixes);
    	
    	final TextView txtPartnerShip = (TextView) 
    			view.findViewById(R.id.fragment_live_score_txt_partnership_score);
    	
    	final TextView txtPlayer2Name = (TextView) 
    			view.findViewById(R.id.fragment_live_score_txt_player2_name);
    	final TextView txtPlayer2Score = (TextView)
    			view.findViewById(R.id.fragment_live_score_txt_player2_runs);
    	final TextView txtPlayer2FoursAndSixes = (TextView)
    			view.findViewById(R.id.fragment_live_score_txt_player2_fours_sixes);
    	
    	final TextView txtReqRuns = (TextView) 
    			view.findViewById(R.id.fragment_live_score_requiredruns);
    	final TextView txtReqRunRate = (TextView) 
    			view.findViewById(R.id.fragment_live_score_requiredrun_rate);
    	final RelativeLayout relTarget = (RelativeLayout) 
    			view.findViewById(R.id.fragment_live_score_rel_target);
    	final RelativeLayout relPlayer1 = (RelativeLayout) 
    			view.findViewById(R.id.fragment_live_score_rel_player1);
    	final RelativeLayout relPlayer2 = (RelativeLayout) 
    			view.findViewById(R.id.fragment_live_score_rel_player2);
    	final RelativeLayout relPartnership = (RelativeLayout) 
    			view.findViewById(R.id.fragment_live_score_rel_partnership);
    	
    	if ( TextUtils.isEmpty(scorecardModel.getResult()) ) {
    		txtToss.setText(scorecardModel.getTossDetails());
    	} else{
    		txtToss.setText(scorecardModel.getResult());
    	}
    	
    	txtMatchStatus.setText(scorecardModel.getMatchStatus());
    	if (scorecardModel.getCurrentPlayingId() == scorecardModel.getTeam1Id()) {
    		txtTeamName.setText(scorecardModel.getTeam1ShortName());
    	} else {
    		txtTeamName.setText(scorecardModel.getTeam2ShortName());
    	}
    	
    	txtScore.setText(scorecardModel.getRuns() + "/" + scorecardModel.getWickets());
    	txtOvers.setText("Overs : " + scorecardModel.getOvers());
    	txtRunRate.setText("Run Rate : " + scorecardModel.getRunRate());
    	
    	if ( TextUtils.isEmpty(scorecardModel.getRequiredRunRate()) ) {
    		relTarget.setVisibility(RelativeLayout.GONE);
    	} else {
    		relTarget.setVisibility(RelativeLayout.VISIBLE);
    		txtReqRuns.setText("Needs " + scorecardModel.getRequiredRun() 
        			+ " runs to win in " + scorecardModel.getRequiredOver() + " overs");
    		txtReqRunRate.setText("Required Run Rate : " +
        			scorecardModel.getRequiredRunRate());
    	}
    	
    	txtPartnerShip.setText(scorecardModel.getPartnershipRuns() +
    			"(" + scorecardModel.getPartnershipBalls() + ")");
    	
    	if ( TextUtils.isEmpty(scorecardModel.getPlayer1Name()) ) {
    		relPlayer1.setVisibility(RelativeLayout.INVISIBLE);
    	} else {
    		relPlayer1.setVisibility(RelativeLayout.VISIBLE);
    		txtPlayer1Name.setText(Utils.getPlayerShortName(scorecardModel.getPlayer1Name()));
        	txtPlayer1Score.setText(scorecardModel.getPlayer1Runs() +
        			"(" + scorecardModel.getPlayer1Balls() + ")");
        	txtPlayer1FoursAndSixes.setText(scorecardModel.getPlayer1Fours() +
        			"x4, " + scorecardModel.getPlayer1Sixes() + "x6");
    	}
    	
    	if ( TextUtils.isEmpty(scorecardModel.getPlayer2Name()) ) {
    		relPlayer2.setVisibility(RelativeLayout.INVISIBLE);
    		relPartnership.setVisibility(RelativeLayout.INVISIBLE);
    	} else {
    		relPlayer2.setVisibility(RelativeLayout.VISIBLE);
    		relPartnership.setVisibility(RelativeLayout.VISIBLE);
    		
	    	txtPlayer2Name.setText(Utils.getPlayerShortName(scorecardModel.getPlayer2Name()));
	    	txtPlayer2Score.setText(scorecardModel.getPlayer2Runs() +
	    			"(" + scorecardModel.getPlayer2Balls() + ")");
	    	txtPlayer2FoursAndSixes.setText(scorecardModel.getPlayer2Fours() +
	    			"x4, " + scorecardModel.getPlayer2Sixes() + "x6");
    	}
    	
    	imgTeam1Logo.setDefaultImageResId(R.drawable.ic_launcher);
    	imgTeam2Logo.setDefaultImageResId(R.drawable.ic_launcher);
    	
    	imgTeam1Logo.setErrorImageResId(R.drawable.ic_launcher);
    	imgTeam2Logo.setErrorImageResId(R.drawable.ic_launcher);
    	
    	final ImageLoader mImageLoader = new ImageLoader(MainActivity.getmRequestQueue(), 
    			new BitmapLruCache(2));
    	
    	imgTeam1Logo.setImageUrl(scorecardModel.getTeam1RoundSmallFlag(), 
    			mImageLoader);
    	imgTeam2Logo.setImageUrl(scorecardModel.getTeam2RoundSmallFlag(), 
    			mImageLoader);
    	
    	final Button btnCommentary = (Button) view.findViewById(R.id.fragment_live_score_btn_commentry);
    	final Button btnScoreboard = (Button) view.findViewById(R.id.fragment_live_score_btn_scoreboard);
    	
    	btnCommentary.setOnClickListener(this);
    	btnScoreboard.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if ( v.getId() == R.id.fragment_live_score_btn_scoreboard ) {
			
		} else if ( v.getId() == R.id.fragment_live_score_btn_commentry  ) {
			final Intent intent = new Intent(getActivity(), CommentaryFragment.class);
			intent.putExtra("matchId", scorecardModel.getMatchId());
			intent.putExtra("inningId", scorecardModel.getInningId());
			startActivity(intent);
		}
	}
}