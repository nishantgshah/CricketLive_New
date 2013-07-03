package com.khushnish.cricketlive.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khushnish.cricketlive.R;
import com.khushnish.cricketlive.model.CommentaryBallsModel;

public class CommentaryAdapter extends ArrayAdapter<CommentaryBallsModel> {
	
	private ArrayList<CommentaryBallsModel> commentaryModels;
	private Context context;
	
	public CommentaryAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<CommentaryBallsModel> commentaryModels) {
		super(context, resource, textViewResourceId, commentaryModels);
		this.context = context;
		this.commentaryModels = new ArrayList<CommentaryBallsModel>();
	}
	
	@Override
	public CommentaryBallsModel getItem(int position) {
		return commentaryModels.get(position);
	}
	
	@Override
	public int getCount() {
		return commentaryModels.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder viewHolder;
		if (rowView == null) {
            final LayoutInflater li = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            rowView = li.inflate(R.layout.row_fragment_commentary, parent, false);
            
            viewHolder = new ViewHolder();
            viewHolder.txtOver = (TextView) rowView.findViewById(R.id.row_fragment_commentary_txt_over);
            viewHolder.txtRuns = (TextView) rowView.findViewById(R.id.row_fragment_commentary_txt_runs);
            viewHolder.txtCommentary = (TextView) rowView.findViewById(R.id.row_fragment_commentary_txt_commentary);
            viewHolder.linCommentary = (LinearLayout) rowView.findViewById(R.id.row_fragment_commentary_lin_overs);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
		
		final CommentaryBallsModel commentaryModel = commentaryModels.get(position);
		
		if ( commentaryModel != null ) {
			viewHolder.txtOver.setText(String.valueOf(commentaryModel.getOverNumber()) +
					"." + String.valueOf(commentaryModel.getBallNumber()));
			viewHolder.txtRuns.setText(String.valueOf(commentaryModel.getRuns()));
			viewHolder.txtCommentary.setText(Html.fromHtml(commentaryModel.getCommentry()));
			
			if ( !commentaryModel.getBallTYpe().equalsIgnoreCase("ball") ){
				viewHolder.linCommentary.setVisibility(LinearLayout.GONE);
			} else {
				viewHolder.linCommentary.setVisibility(LinearLayout.VISIBLE);
			}
		}
		return rowView;
	}
	
	static class ViewHolder {
		TextView txtOver;
		TextView txtRuns;
		TextView txtCommentary;
		LinearLayout linCommentary;
	}
	
	public void updateCommentary(ArrayList<CommentaryBallsModel> commentaryModels){
		this.commentaryModels.addAll(commentaryModels);
		Log.e("Commentary", "commentary size : " + this.commentaryModels.size());
		notifyDataSetChanged();
	}
}