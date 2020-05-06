package com.android.SmartParking;


import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Slot extends Activity {
	
	Button s1;
	int cou = 0;
	String region="";
	String slot="",Id="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slot);
		
		s1 = (Button) findViewById(R.id.p1);

		
		Intent i1 = getIntent();
		region = i1.getStringExtra("Region");
		Id = i1.getStringExtra("ID");
		
		s1.setOnClickListener(new View.OnClickListener() {
			
            @Override
            public void onClick(View v) {
            	
            	Intent i1 = new Intent(Slot.this, Layout.class);
            	i1.putExtra("Slot", s1.getText().toString());
            	i1.putExtra("Region", region);
            	i1.putExtra("ID", Id);
            	startActivity(i1);
            	finish();
            }
        });
		

		
		new AsynGetSlots().execute(region);
		
		
	}
	
	public class AsynGetSlots extends AsyncTask<String, JSONObject, String>
	{
		@Override
		protected String doInBackground(String... params) {
			String Status = "false";
			try {
				RestAPI api = new RestAPI();
				
                JSONObject getStatus = api.getslot(params[0]);
				//Parse the JSON Object to String
                JSONParser parser = new JSONParser();
                Status = parser.parseUId(getStatus);
                return Status;
                
           } catch (Exception e) {
					Toast.makeText(Slot.this, e.getMessage(), Toast.LENGTH_SHORT).show();
				return Status;
        }
	}
		
	@SuppressLint("Range")
	protected void onPostExecute(String result) {
			String status = result;
			if(status.compareTo("false") == 0)
			{
				Toast.makeText(Slot.this, result, Toast.LENGTH_SHORT).show();
			}
			else
			{
				String[] reg2 = result.split(",");
				cou = reg2.length;
				slot = result;
				
				if(cou == 4)
				{
					s1.setAlpha(128);

					
					String[] reg1 = slot.split(",");
					s1.setText(reg1[0]);

				}
				
				if(cou == 3)
				{
					
					s1.setAlpha(128);

					
					String[] reg1 = slot.split(",");
					s1.setText(reg1[0]);

				}
				
				if(cou == 2)
				{
					s1.setAlpha(128);

					
					String[] reg1 = slot.split(",");
					s1.setText(reg1[0]);

				}
				
				if(cou == 1)
				{
					s1.setAlpha(128);

					
					String[] reg1 = slot.split(",");
					s1.setText(reg1[0]);
				}

			}
		}
	}
}
