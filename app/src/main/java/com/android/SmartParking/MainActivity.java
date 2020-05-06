package com.android.SmartParking;



import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	Button newbook,viewbook,mydet,addb,logout,can;
	TextView name;
	String names;
	String Id = "",Latitude,Longitude;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        name = (TextView) findViewById(R.id.c_name);
        
        Intent i=getIntent();
		Id = i.getStringExtra("ID");
        
		new AsynGetName().execute();
		
		
		GPSTracker gps=new GPSTracker(this,MainActivity.this);
		if (gps.canGetLocation())
        {
        	Latitude = String.valueOf(gps.latitude);
        	Longitude = String.valueOf(gps.longitude);
        	//Toast.makeText(this,"Lat : "+Latitude+", Long : "+ Longitude, Toast.LENGTH_SHORT).show();
        }
        else
        {
        	Toast.makeText(this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
        }
		
		
        //Feedback
        addb=(Button) findViewById(R.id.m_add_bal);
        addb.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					Intent i1 = new Intent(MainActivity.this,Feedback.class);
                	i1.putExtra("ID", Id);
                	startActivity(i1);
                
			}
		});
        
        
      //Cancel Booking
        can=(Button) findViewById(R.id.m_can);
        can.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					Intent i1 = new Intent(MainActivity.this, CancelBooking.class);
                	i1.putExtra("ID", Id);
                	i1.putExtra("head","Cancel");
                	i1.putExtra("btn","true");
                	startActivity(i1);
                
			}
		});
        
        
        
      //new book
        newbook=(Button) findViewById(R.id.new_book);
        newbook.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					Intent i1 = new Intent(MainActivity.this, ChoosePlot.class);
                	i1.putExtra("ID", Id);
                	i1.putExtra("lat", Latitude);
                	i1.putExtra("lng", Longitude);
                	startActivity(i1);
                
			}
		});
        
      //View Booking
        viewbook=(Button) findViewById(R.id.m_vbook);
        viewbook.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					Intent i1 = new Intent(MainActivity.this, CancelBooking.class);
                	i1.putExtra("ID", Id);
                	i1.putExtra("head","View");
                	i1.putExtra("btn","false");
                	startActivity(i1);
                
			}
		});
      
        
      //my Details
        mydet=(Button) findViewById(R.id.m_det);
        mydet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					Intent i1 = new Intent(MainActivity.this,my_details.class);
                	i1.putExtra("ID", Id);
                	startActivity(i1);
                
			}
		});
        
      //Logout
        logout=(Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent i1 = new Intent(MainActivity.this,LoginActivity.class);
				//startActivity(i1);
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
			    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    intent.putExtra("Exit me", true);
			    startActivity(intent);
			    finish();
			}
		});
        
        
        
}
    public class AsynGetName extends AsyncTask<String, JSONObject, String>
	{
		@Override
		protected String doInBackground(String... params) {
			String Name = new String();
			try {
				RestAPI api = new RestAPI();
				
                JSONObject getName = api.getName(Id);
				//Parse the JSON Object to String
                JSONParser parser = new JSONParser();
                Name = parser.parseName(getName);
                return Name;
                
           } catch (Exception e) {
					return Name;
        }
	}
	protected void onPostExecute(String result) {
			name.setText("Welcome  "+result);
			
		}
	}

 
}
