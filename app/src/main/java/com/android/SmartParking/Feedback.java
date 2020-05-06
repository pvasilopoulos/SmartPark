package com.android.SmartParking;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends Activity {
	
	EditText feed;
	Button sub;
	String Id="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.feedback);
		
		feed = (EditText) findViewById(R.id.f_feed);
		sub = (Button) findViewById(R.id.f_sub);
		
		Intent i1 = getIntent();
		Id = i1.getStringExtra("ID");
		
		sub.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View v) {
                  // TODO Auto-generated method stub
            	
            	if(feed.getText().toString().compareTo("")!=0)
            	{
            		Calendar c1 = Calendar.getInstance();
            		SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
            		String date = df1.format(c1.getTime());
            	
            		Calendar c = Calendar.getInstance();
            		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            		String time = df.format(c.getTime());
            		
            		new AsyncFeed().execute(Id, feed.getText().toString(), date, time);
            	}
            	else
            	{
            		Toast.makeText(Feedback.this, "Please Enter Feed", Toast.LENGTH_SHORT).show();
            	}
            }
      });
	}
	
	
	protected class AsyncFeed extends AsyncTask<String, JSONObject, String> {
		 
        String userName=null;
        @Override
        protected String doInBackground(String... params) {

              RestAPI api = new RestAPI();
              String userAuth = "false";
              try {

                    // Call the User Authentication Method in API
                    JSONObject jsonObj = api.feedback(params[0],params[1],params[2],params[3]);

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
        	
        	  //Check user validity 
			if (result.compareTo("false")!=0) {
				Toast.makeText(Feedback.this, "Feedback Submited Successfully",Toast.LENGTH_SHORT).show();
				feed.setText("");
			}
			else
			{
				Toast.makeText(Feedback.this, "Feedback was Not Submited, please try again later",Toast.LENGTH_SHORT).show();
			}
        }
	}
}
