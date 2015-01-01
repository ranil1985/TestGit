package com.guestlist.src;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class EventActivity extends FragmentActivity implements
		OnTabChangeListener {

	FragmentTabHost tabHost;
	LinearLayout ucs;
	LinearLayout ucus;

	LinearLayout ps;
	LinearLayout pus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);

		Resources ressources = this.getResources();

		TextView createEventText = (TextView) findViewById(R.id.createEvent);
		TextView logoutText = (TextView) findViewById(R.id.logout);

		View pastView = LayoutInflater.from(this).inflate(
				R.layout.two_tabs_layout_past, null);
		View upcomingView = LayoutInflater.from(this).inflate(
				R.layout.two_tabs_layout_upcoming, null);
		
		

		ucs = (LinearLayout) upcomingView.findViewById(R.id.upcomingTabSelect);
		ucus = (LinearLayout) upcomingView
				.findViewById(R.id.upcomingTabNotSelect);

		ps = (LinearLayout) pastView.findViewById(R.id.pastSelect);
		pus = (LinearLayout) pastView.findViewById(R.id.pastUnSelect);

		ucus.setVisibility(View.GONE);
		ps.setVisibility(View.GONE);

		tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// tabHost.addTab(tabHost.newTabSpec("Upcoming").setIndicator("Upcoming",
		// EventActivity.this.getResources().getDrawable(R.drawable.upcoming_past_select)),
		// UpcomingEventsFragment.class, null);

		tabHost.addTab(tabHost.newTabSpec("Upcoming")
				.setIndicator(upcomingView), UpcomingEventsFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("Past").setIndicator(pastView),
				PastEventsFragment.class, null);

		// tabHost.getTabWidget().setBackgroundDrawable(EventActivity.this.getResources().getDrawable(R.drawable.navi_button_bar));

		tabHost.setOnTabChangedListener(this);

		createEventText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent add=new Intent(getApplicationContext(),
						CreateEditEventActivity.class);
				add.putExtra("EVENT_ACTION", "ADD");
				startActivity(add);
			}
		});
		
		
		logoutText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent add=new Intent(getApplicationContext(),
						LoginActivity.class);				
				startActivity(add);
			}
		});
		
		
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		if (tabId.equals("Upcoming")) {
			ucs.setVisibility(View.VISIBLE);
			ucus.setVisibility(View.GONE);
			ps.setVisibility(View.GONE);
			pus.setVisibility(View.VISIBLE);

		} else if (tabId.equals("Past")) {

			ucus.setVisibility(View.VISIBLE);
			ucs.setVisibility(View.GONE);
			pus.setVisibility(View.GONE);
			ps.setVisibility(View.VISIBLE);
		}
		System.out.println("xxxxxxxx==" + tabId);

	}

}
