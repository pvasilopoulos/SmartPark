package com.android.SmartParking;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class my_details extends Activity {

	TextView name,phone,email,bal,bid;
	String UId = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_det);
		
		name = (TextView) findViewById(R.id.md_name);
		phone = (TextView) findViewById(R.id.md_phone);
		email = (TextView) findViewById(R.id.md_email);
		bal = (TextView) findViewById(R.id.md_bal);
		bid = (TextView) findViewById(R.id.md_bid);
		
		Intent i1 = getIntent();
		UId = i1.getStringExtra("ID");
		
		Calendar c = Calendar.getInstance();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	String date = df.format(c.getTime());
		
		new AsynGetDetails().execute(UId,date);

	}
	
	public class AsynGetDetails extends AsyncTask<String, JSONObject, String>
	{
		@Override
		protected String doInBackground(String... params) {
			String Status = "false";
			try {
				RestAPI api = new RestAPI();
				
                JSONObject getStatus = api.details(params[0], params[1]);
				//Parse the JSON Object to String
                JSONParser parser = new JSONParser();
                Status = parser.parseUId(getStatus);
                return Status;
                
           } catch (Exception e) {
					Toast.makeText(my_details.this, e.getMessage(), Toast.LENGTH_SHORT).show();
				return Status;
        }
	}
	protected void onPostExecute(String result) {
			String status = result;
			if(status.compareTo("false") == 0)
			{
				Toast.makeText(my_details.this, result, Toast.LENGTH_SHORT).show();
			}
			else
			{
				String[] ans = result.split(",");
				name.setText(ans[0]);
				phone.setText(ans[1]);
				email.setText(ans[2]);
				bal.setText(ans[3]);
				bid.setText(ans[4]);
				//Toast.makeText(my_details.this, result, Toast.LENGTH_SHORT).show();
			}
		}
	}
}
