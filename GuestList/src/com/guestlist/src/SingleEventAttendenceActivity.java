package com.guestlist.src;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.os.Build;

public class SingleEventAttendenceActivity extends FragmentActivity implements OnClickListener,OnTabChangeListener {

	FragmentTabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_event_attendence);

		ImageView addNameButton=(ImageView) findViewById(R.id.addNames);
		ImageView closedButton=(ImageView) findViewById(R.id.closeEvent);
		ImageView refreshButton=(ImageView) findViewById(R.id.refreshEvent);
		
		addNameButton.setOnClickListener(this);
		
		
		//Tab View
		tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		

		View pastView = LayoutInflater.from(this).inflate(
				R.layout.two_tabs_layout_past, null);
		View upcomingView = LayoutInflater.from(this).inflate(
				R.layout.two_tabs_layout_upcoming, null);
		
		View xx = LayoutInflater.from(this).inflate(
				R.layout.fragment_guest_list, null);

		// tabHost.addTab(tabHost.newTabSpec("Upcoming").setIndicator("Upcoming",
		// EventActivity.this.getResources().getDrawable(R.drawable.upcoming_past_select)),
		// UpcomingEventsFragment.class, null);

		tabHost.addTab(tabHost.newTabSpec("Upcoming")
				.setIndicator(upcomingView), UpcomingEventsFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("Past").setIndicator(pastView),
				PastEventsFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("success")
				.setIndicator(xx), GuestListFragment.class, null);
		

		// tabHost.getTabWidget().setBackgroundDrawable(EventActivity.this.getResources().getDrawable(R.drawable.navi_button_bar));

		tabHost.setOnTabChangedListener(this);

		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addNames:
			
			Intent add=new Intent(SingleEventAttendenceActivity.this,CreateNamesActivity.class);
			startActivity(add);
			
			break;
        case R.id.closeEvent:
			
			break;
        case R.id.refreshEvent:
			
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		
	}

	

}
