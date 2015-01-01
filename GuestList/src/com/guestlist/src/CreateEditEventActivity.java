package com.guestlist.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.guestlist.model.Event;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class CreateEditEventActivity extends Activity implements OnClickListener{

	private int year;
	private int month;
	private int day;
	EditText dateText;
	EditText name;
	EditText venue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_create_event);
		
		String eventAction=getIntent().getStringExtra("EVENT_ACTION");
		//get Parcble object 
		Event uobj= getIntent().getParcelableExtra("CLICKED_EVENT");

		
		TextView title=(TextView) findViewById(R.id.headerTitle);
		
	    name=(EditText) findViewById(R.id.nameEditText);
		venue=(EditText) findViewById(R.id.venueEditText);
		
	    dateText=(EditText) findViewById(R.id.dateEditText);
		TextView submitText=(TextView) findViewById(R.id.submit);
		
		LinearLayout editBottom=(LinearLayout) findViewById(R.id.editEventBottom);
		LinearLayout addBottom=(LinearLayout) findViewById(R.id.addEventBottom);
		
		
		
		if(eventAction.equals("ADD")){
			editBottom.setVisibility(View.GONE);
			
		}else{
			title.setText("Edit Event");
			addBottom.setVisibility(View.GONE);
			
			name.setText(uobj.getName());
			venue.setText(uobj.getVenue());
		}

		submitText.setOnClickListener(this);
		dateText.setOnClickListener(this);
	}

	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.submit:
			
			String date=dateText.getText().toString();
			String ven=venue.getText().toString();
			String nam=name.getText().toString();
			
			if(!date.equals("") && !nam.equals("") && !ven.equals("")){
				
				Intent i=new Intent(CreateEditEventActivity.this,SubmitResultActivity.class);
				i.putExtra("submit_status", "SUCCESS");
				startActivity(i);
				
				new PassToServerDb().execute(nam,ven,date);
			}else{
				
				Intent i=new Intent(CreateEditEventActivity.this,SubmitResultActivity.class);
				i.putExtra("submit_status", "FAIL");
				startActivity(i);
			}
			
		
			
			break;
			
        case R.id.dateEditText:
			
			datePickerLoad();
			
			break;

		default:
			break;
		}
	}
	

	public void datePickerLoad() {

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog tpd = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						dateText.setText(year + "-" + (monthOfYear + 1)
								+ "-" + dayOfMonth);
					}
				}, year, month, day);

		tpd.show();

	}
	
	
	public class PassToServerDb extends AsyncTask<String, String, String> {

		ProgressDialog progDailog = new ProgressDialog(CreateEditEventActivity.this);

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progDailog.setMessage("Loading...");
			progDailog.setIndeterminate(false);
			progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progDailog.setCancelable(true);
			progDailog.show();
		}

		@Override
		protected String doInBackground(String... arg) {
			// TODO Auto-generated method stub

			BufferedReader in = null;
			String result = "";
			JSONObject jsonObject = null;

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://oooloo.me/mygp/index.php/GuestList/saveEvents");

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
			nameValuePairs.add(new BasicNameValuePair("EVENT_NAME", arg[0]));
			nameValuePairs.add(new BasicNameValuePair("EVENT_VENUE", arg[1]));
			nameValuePairs.add(new BasicNameValuePair("EVENT_DATE", arg[2]));
			
		
			try {
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request

				HttpResponse response = httpclient.execute(httppost);

				in = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()));

				StringBuffer sb = new StringBuffer("");
				String line = "";
				String lineSeparator = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + lineSeparator);
				}
				in.close();
				result = sb.toString();
				try {
					jsonObject = new JSONObject(result);
				} catch (Exception e) {
				}

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("================////=" + result);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			try {
				JSONObject jo = new JSONObject(result);

			

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
