package com.guestlist.src;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guestlist.model.Event;

public class UpcomingEventsFragment extends Fragment implements OnItemClickListener {
	
	List<Event> eventList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View view = inflater.inflate(R.layout.upcomingeventsfragment, container, false);
		
		eventList=getEventList();
		
		
		ListView listView=(ListView) view.findViewById(R.id.list);
		listView.setAdapter(new CustomListAdapter());
		
		listView.setOnItemClickListener(this);
		
		
		
		return view;
	}
    
	
	
	
	public class CustomListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return eventList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return eventList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater inflater = (LayoutInflater) getActivity()
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView=inflater.inflate(R.layout.event_list_row, null);
			
			TextView name = (TextView) convertView.findViewById(R.id.name);
			TextView venue = (TextView) convertView.findViewById(R.id.venue);

			final Event e=(Event)getItem(position);
			
			name.setText(e.getName());
			venue.setText(e.getVenue());
			
			ImageView arrowImage=(ImageView) convertView.findViewById(R.id.moreArrowImage);
			arrowImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent edit=new Intent(getActivity(),
							CreateEditEventActivity.class);
					edit.putExtra("CLICKED_EVENT", e);
					edit.putExtra("EVENT_ACTION", "EDIT");
					startActivity(edit);
					
				}
			});
			
			return convertView;
		}
		
		
	}
	
	public List<Event> getEventList(){
		
		List<Event> list=new ArrayList<Event>();
		
		Event e=new Event("First Event","colombo");
		e.setName("First Event");
		e.setVenue("colombo");
		
		list.add(e);
		
		Event e2=new Event("Second Event","Galle");
		e2.setName("Second Event");
		e2.setVenue("Galle");
		
		list.add(e2);
		
		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {


		startActivity(new Intent(getActivity(),SingleEventAttendenceActivity.class));
		
	}
	
}