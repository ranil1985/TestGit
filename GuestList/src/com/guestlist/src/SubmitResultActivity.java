package com.guestlist.src;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class SubmitResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String submitStatus=getIntent().getStringExtra("submit_status");
		if(submitStatus.equals("SUCCESS")){
			setContentView(R.layout.activity_submit_result);
		}else if(submitStatus.equals("FAIL")){
			setContentView(R.layout.activity_submit_result_failed);
			
		}
		
		
	//String submitStatus=getIntent().getStringExtra("submit_status");

	
	}

	
}
