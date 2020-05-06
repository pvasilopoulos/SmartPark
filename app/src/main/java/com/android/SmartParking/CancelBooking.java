package com.android.SmartParking;

import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CancelBooking extends Activity {
	String Head,Btn;
	EditText TId;
	TextView plot,slot,BId,date;
	Button sea,can;
	String Id="";
	TextView heading;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cancel);
		Intent i1 = getIntent();
		Id = i1.getStringExtra("ID");
		Head=i1.getStringExtra("head");
		Btn=i1.getStringExtra("btn");
		
		heading=(TextView) findViewById(R.id.headbook);
		heading.setText(Head+" Booking");
		
		plot = (TextView) findViewById(R.id.c_plot);
		slot = (TextView) findViewById(R.id.c_slot);
		BId = (TextView) findViewById(R.id.c_id);
		date = (TextView) findViewById(R.id.c_date);
	
		TId = (EditText) findViewById(R.id.c_tid);
		
		
		
		
		sea = (Button) findViewById(R.id.c_search);
		can = (Button) findViewById(R.id.c_can);
		
		sea.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View v) {
                  // TODO Auto-generated method stub
            	
            	if(TId.getText().toString().compareTo("")!=0)
            	{
            		new AsyncSearch().execute(TId.getText().toString(), Id);
            	}
            	else
            	{
            		Toast.makeText(CancelBooking.this, "Please Enter Booking ID", Toast.LENGTH_SHORT).show();
            	}
            }
      });
		
		can.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View v) {
                  // TODO Auto-generated method stub
            	
            	if(plot.getText().toString().compareTo("")!=0)
            	{
            		new AsyncCancel().execute(TId.getText().toString(), Id);
            	}
            	else
            	{
            		Toast.makeText(CancelBooking.this, "Please Enter Booking ID", Toast.LENGTH_SHORT).show();
            	}
            }
      });
		
	}
	
	protected class AsyncSearch extends AsyncTask<String, JSONObject, String> {
		 
        String userName=null;
        @Override
        protected String doInBackground(String... params) {

              RestAPI api = new RestAPI();
              String userAuth = "false";
              try {

                    // Call the User Authentication Method in API
                    JSONObject jsonObj = api.Bdetails(params[0],params[1]);

                    //Parse the JSON Object to boolean
                    JSONParser parser = new JSONParser();
                    userAuth = parser.parseUId(jsonObj);
                    
              } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.d("AsyncLogin", e.getMessage());
              }
              return userAuth;
        }

        @Override
        protected void onPostExecute(String result) {
              // TODO Auto-generated method stub
        	
        	if (result.compareTo("false")!=0) 
			{
				//Toast.makeText(Cancel_B.this, result,Toast.LENGTH_SHORT).show();
				String[] s = result.split(",");
				plot.setText(s[0]);
				slot.setText(s[1]);
				BId.setText(s[2]);
				date.setText(s[3]);
				
				TId.setEnabled(false);
				sea.setAlpha(0);
				if(Btn.compareTo("true")==0)
				{
					can.setAlpha(128);
				}
			}
			else
			{
				Toast.makeText(CancelBooking.this, "Invalid Booking ID",Toast.LENGTH_SHORT).show();
			}
        }
	}
	
	protected class AsyncCancel extends AsyncTask<String, JSONObject, String> {
		 
        String userName=null;
        @Override
        protected String doInBackground(String... params) {

              RestAPI api = new RestAPI();
              String userAuth = "false";
              try {

                    // Call the User Authentication Method in API
                    JSONObject jsonObj = api.canbook(params[0],params[1]);

                    //Parse the JSON Object to boolean
                    JSONParser parser = new JSONParser();
                    userAuth = parser.parseUId(jsonObj);
                    
              } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.d("AsyncLogin", e.getMessage());
              }
              return userAuth;
        }

        @Override
        protected void onPostExecute(String result) {
              // TODO Auto-generated method stub
        	
        	if (result.compareTo("false")!=0) 
			{
				Toast.makeText(CancelBooking.this, "Booking Canceled Successfully",Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(CancelBooking.this, "Booking Could not be Canceled",Toast.LENGTH_SHORT).show();
			}
        }
	}
}
