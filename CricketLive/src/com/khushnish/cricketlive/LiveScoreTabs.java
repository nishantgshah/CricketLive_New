package com.khushnish.cricketlive;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.khushnish.cricketlive.fragments.LiveScoreFragment;
import com.khushnish.cricketlive.model.ScorecardModel;
import com.khushnish.cricketlive.webservice.WSLiveScoreBoard;

public class LiveScoreTabs implements TabListener {
	
	private ActionBar actionBar;
	private ArrayList<ScorecardModel> scorecardModels;
	private Fragment scoreFragment;
	private MainActivity context;
	
	public LiveScoreTabs( ActionBar actionBar, MainActivity context ) {
		this.actionBar = actionBar;
		this.context = context;
		new WSLiveScoreBoard().executeService(context, this);
	}
	
	public void updateScores(ArrayList<ScorecardModel> scorecardModels) {
		this.scorecardModels = scorecardModels;
		
		if ( scorecardModels.size() > 0 ) {
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			
			for (ScorecardModel scorecardModel : scorecardModels) {
				Log.e("Teams", scorecardModel.getTeam1ShortName() +
						" vs " + scorecardModel.getTeam2ShortName());
				Tab tab = actionBar.newTab();
				tab.setText(Html.fromHtml("<font color='#ffffff'>" + 
						scorecardModel.getTeam1ShortName() +
						" vs " + scorecardModel.getTeam2ShortName()+ "</font>"));
				tab.setTabListener(this);
				actionBar.addTab(tab);
			}
		} else {
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			final FragmentManager fragmentManager = context.getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.content_frame, 
					new NoLiveScoreFragment()).commit();
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		final ScorecardModel scorecardModel = scorecardModels.get(tab.getPosition());
		scoreFragment = new LiveScoreFragment();
		final Bundle args = new Bundle();
		args.putParcelable("scorecard", scorecardModel);
		scoreFragment.setArguments(args);
		ft.replace(R.id.content_frame, scoreFragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
//		if ( scoreFragment != null ) {
//			ft.remove(scoreFragment);
//		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
}

class NoLiveScoreFragment extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {
    	final View view = inflater.inflate(R.layout.fragment_no_live_score, container, false);
    	
        return view; 
    }
    
}