package com.guestlist.src;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Build;

public class CreateNamesActivity extends Activity implements OnClickListener{

	EditText nameText;
	Spinner paxText;
	EditText promoterText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ceate_names);

		nameText=(EditText) findViewById(R.id.nameEditText);
		 paxText=(Spinner) findViewById(R.id.paxEditText);
		 promoterText=(EditText) findViewById(R.id.nameEditText);
		 
		 TextView submitText=(TextView) findViewById(R.id.submit);
		 submitText.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit:
			
			String n=nameText.getText().toString();
			String p="xxx";//paxText.getSelectedItem().toString();
			String pr=promoterText.getText().toString();
			
           if(!n.equals("") && !p.equals("") && !pr.equals("")){
				
				Intent i=new Intent(CreateNamesActivity.this,SubmitResultActivity.class);
				i.putExtra("submit_status", "SUCCESS");
				startActivity(i);
			}else{
				
				Intent i=new Intent(CreateNamesActivity.this,SubmitResultActivity.class);
				i.putExtra("submit_status", "FAIL");
				startActivity(i);
			}
			
			break;

		default:
			break;
		}
	}

	
}
