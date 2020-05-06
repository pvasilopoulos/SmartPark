package com.android.SmartParking;

import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChoosePlot extends Activity{
	int z=0;
	int x=0;
	EditText txt;
	Button area,pincode,submit,cancel;
	String Id="",Latitude,Longitude;
	String kl="37.983810",klg="23.727539";
	String[] plot;
	String[] lat;
	String[] lng;
	Float[] dist;
	Float mindist,tmindist;
	int count;
	String[] plotname;
	int plotno;
	String finallat,finallong,region;
	String nfinallat,nfinallong,nregion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plot_by_pincode);
		
		new neararea().execute();
		Intent i=getIntent();
		Id = i.getStringExtra("ID");
		Latitude=i.getStringExtra("lat");
		Longitude=i.getStringExtra("lng");
		area=(Button) findViewById(R.id.areabtn);
		pincode=(Button) findViewById(R.id.pinbtn);
		submit=(Button) findViewById(R.id.subbtn);
		cancel=(Button) findViewById(R.id.canbtn);
		txt=(EditText) findViewById(R.id.pintxt);
	}
	
	public void areabtn(View v)
	{
			
			Intent i1=new Intent(this,MapActivity.class);
			i1.putExtra("ID", Id);
			//my loc
			i1.putExtra("mylat", Latitude);
	    	i1.putExtra("mylng", Longitude);
	    	i1.putExtra("count", count);
	    	for(int i=0;i<count;i++)
	    	{
	    		i1.putExtra("Region"+i, plot[i]);
	    		i1.putExtra("Lat"+i, lat[i]);
	    		i1.putExtra("Lng"+i, lng[i]);
	    		
	    	}
			startActivity(i1);
			finish();
			
			
//
	}
	
	public void pinbtn(View v)
	{
		txt.setAlpha(128);
		submit.setAlpha(128);
		cancel.setAlpha(128);
		area.setEnabled(false);
		pincode.setEnabled(false);
	}
	public void subbtn(View v)
	{
		if(txt.getText().toString().compareTo("")!=0)
		{
			
			new pincode().execute(txt.getText().toString());
		}
		else
		{
			Toast.makeText(this, "Enter Postal Code", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void canbtn(View v)
	{
		txt.setAlpha(0);
		submit.setAlpha(0);
		cancel.setAlpha(0);
		txt.setText("");
		area.setEnabled(true);
		pincode.setEnabled(true);
	}
	
	public class neararea extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.getnear();
				JSONParser jp=new JSONParser();
				a=jp.getneardata(json);
			} catch (Exception e) {
				a=e.getMessage();
			}
			return a;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String b="post";
			b=result;

			String str[]=b.split("\\*");
			plot=new String[str.length];
			lat=new String[str.length];
			lng=new String[str.length];
			dist=new Float[str.length];
			count=str.length;

			for(int i=0;i<str.length;i++)
			{
				String str1[]=str[i].split("\\$");
				plot[i]=str1[0];
				lat[i]=str1[1];
				lng[i]=str1[2];

			}
//

		}
		
	}
	public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
	    double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (earthRadius * c);
	    dist=dist/1000;
	    dist=dist/1000;
	    return dist;
	    }
	
	
	public class pincode extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.getPin(params[0]);
				JSONParser jp=new JSONParser();
				a=jp.getpincodedata(json);
			} catch (Exception e) {
				a=e.getMessage();
			}
			
			return a;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String b="post";
			b=result;
			if(b.compareTo("false")==0)
			{
				Toast.makeText(ChoosePlot.this, "NO Parking Plot Available in the Pincode Provided", Toast.LENGTH_SHORT).show();
			}
			else
			{
				b=b.substring(0,( b.length()-1));
				plotname=b.split(",");
				plotno=plotname.length;
				
				
				Intent i1 = new Intent(ChoosePlot.this,Region.class);
				i1.putExtra("ID", Id);
				i1.putExtra("pno", plotno);
				for(int i=0;i<plotno;i++)
				{
					i1.putExtra("pname"+i, plotname[i]);

				}
		    	startActivity(i1);
		    	finish();
			}
			
		}
		
	}

}
