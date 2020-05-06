package com.android.SmartParking;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Region extends Activity {
	
	TextView name;
	Button r1,r2,r3,r4;
	String reg="";
	String Id = "";
	String[] plotname;
	int plotno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.region);
	
		Intent i=getIntent();
		Id = i.getStringExtra("ID");
        plotno=i.getIntExtra("pno",0);
        plotname=new String[plotno];
        for(int j=0;j<plotno;j++)
        {
        	plotname[j]=i.getStringExtra("pname"+j);
        	//Toast.makeText(this, plotname[j], Toast.LENGTH_SHORT).show();
        }
		
		name = (TextView) findViewById(R.id.r_name);
		new AsynGetName().execute(Id);
		r1= (Button) findViewById(R.id.r1);
		r2= (Button) findViewById(R.id.r2);
		r3= (Button) findViewById(R.id.r3);
		r4= (Button) findViewById(R.id.r4);
		
		
		if(plotno == 4)
		{
			r1.setAlpha(128);
			r2.setAlpha(128);
			r3.setAlpha(128);
			r4.setAlpha(128);
			
			r1.setText(plotname[0]);
			r2.setText(plotname[1]);
			r3.setText(plotname[2]);
			r4.setText(plotname[3]);
		}
		
		if(plotno == 3)
		{
			r1.setAlpha(128);
			r2.setAlpha(128);
			r3.setAlpha(128);
			r4.setAlpha(0);
			
			r1.setText(plotname[0]);
			r2.setText(plotname[1]);
			r3.setText(plotname[2]);
		}
		
		if(plotno == 2)
		{
			r1.setAlpha(128);
			r2.setAlpha(128);
			r3.setAlpha(0);
			r4.setAlpha(0);
			
			r1.setText(plotname[0]);
			r2.setText(plotname[1]);
		}
		
		if(plotno == 1)
		{
			r1.setAlpha(128);
			r2.setAlpha(0);
			r3.setAlpha(0);
			r4.setAlpha(0);
			
			r1.setText(plotname[0]);
		}
		
		r1.setOnClickListener(new View.OnClickListener() {
			
            @Override
            public void onClick(View v) {
            	
            	Intent i1 = new Intent(Region.this,Slot.class);
            	i1.putExtra("Region", r1.getText().toString());
            	i1.putExtra("ID", Id);
            	startActivity(i1);
            	finish();
            }
        });
		
		r2.setOnClickListener(new View.OnClickListener() {
			
            @Override
            public void onClick(View v) {
            	
            	Intent i1 = new Intent(Region.this,Slot.class);
            	i1.putExtra("Region", r2.getText().toString());
            	i1.putExtra("ID", Id);
            	startActivity(i1);
            	finish();
            }
        });

		r3.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
    	
				Intent i1 = new Intent(Region.this,Slot.class);
				i1.putExtra("Region", r3.getText().toString());
				i1.putExtra("ID", Id);
				startActivity(i1);
				finish();
			}
		});

		r4.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
    	
				Intent i1 = new Intent(Region.this,Slot.class);
				i1.putExtra("Region", r4.getText().toString());
				i1.putExtra("ID", Id);
				startActivity(i1);
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
