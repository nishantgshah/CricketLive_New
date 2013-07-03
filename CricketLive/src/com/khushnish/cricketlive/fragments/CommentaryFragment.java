package com.khushnish.cricketlive.fragments;

import java.util.ArrayList;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockListActivity;
import com.khushnish.cricketlive.R;
import com.khushnish.cricketlive.adapter.CommentaryAdapter;
import com.khushnish.cricketlive.model.CommentaryBallsModel;
import com.khushnish.cricketlive.utils.LoadMoreListView;
import com.khushnish.cricketlive.utils.LoadMoreListView.OnLoadMoreListener;
import com.khushnish.cricketlive.webservice.WSCommentary;

public class CommentaryFragment extends SherlockListActivity {
	
	private ArrayList<CommentaryBallsModel> commentaryBallsModels;
	private CommentaryAdapter commentaryAdapter;
	private int matchId = 0;
	private int inningId = 0;
	private int start = 1;
	private int end = 5;
	private boolean moreCommentaryAvailable = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull_to_refresh);
		initializeComponents();
	}
	
    private void initializeComponents() {
    	
    	matchId = getIntent().getIntExtra("matchId", 0);
    	inningId = getIntent().getIntExtra("inningId", 0);
    	
    	commentaryBallsModels = new ArrayList<CommentaryBallsModel>();
    	commentaryAdapter = new CommentaryAdapter(this, R.layout.row_fragment_commentary, 
    			R.id.row_fragment_commentary_txt_runs, commentaryBallsModels);
    	this.setListAdapter(commentaryAdapter);
    	
    	new WSCommentary().executeService(this, start, end, matchId, inningId);
        ((LoadMoreListView) getListView()).setOnLoadMoreListener(new OnLoadMoreListener() {
			public void onLoadMore() {
				if ( moreCommentaryAvailable ) {
	            	start += 5;
	            	end += 5;
	            	new WSCommentary().executeService(CommentaryFragment.this, start, end, matchId, inningId);
				}
            }
        });
	}
    
    public void updateCommentary(ArrayList<CommentaryBallsModel> commentaryBallsModels) {
    	this.commentaryBallsModels = commentaryBallsModels;
    	commentaryAdapter.updateCommentary(commentaryBallsModels);
    	((LoadMoreListView) getListView()).onLoadMoreComplete();
    	
    	if ( commentaryBallsModels != null &&
    			commentaryBallsModels.size() == 0 ) {
    		moreCommentaryAvailable = false;
    	}
    }
}