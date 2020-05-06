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

public class RegisterUser extends Activity {
	
	
	EditText id,name,mob,email,pass;
	Button sub;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reg_user);
	
		id = (EditText) findViewById(R.id.reg_id);
		
		name = (EditText) findViewById(R.id.reg_name);
		
		mob = (EditText) findViewById(R.id.reg_no);
		
		email = (EditText) findViewById(R.id.reg_email);
		
		pass = (EditText) findViewById(R.id.reg_pass);
		
		sub = (Button) findViewById(R.id.reg_sub);
		
		new AsynGetUserID().execute();
		
		sub.setOnClickListener(new View.OnClickListener() {
			
            @Override
            public void onClick(View v) {
            	
            	String c = check();
            	if(c.compareTo("OK")==0)
            	{
            		new AsynSetUser().execute(id.getText().toString(), name.getText().toString(), mob.getText().toString(), email.getText().toString(), pass.getText().toString());

            	}
            	else
            	{
            		Toast.makeText(RegisterUser.this, "Please Enter "+c, Toast.LENGTH_SHORT).show();
            	}
            }
        });
	}
	
	public String check()
	{
		if(name.getText().toString().compareTo("")==0)
		{
			return "Name";
		}
		
		if(mob.getText().toString().compareTo("")==0)
		{
			return "Mobile No";
		}
		
		if(email.getText().toString().compareTo("")==0)
		{
			return "Email ID";
		}
		
		if(pass.getText().toString().compareTo("")==0)
		{
			return "Password";
		}
		return "OK";
	}
	
	public class AsynGetUserID extends AsyncTask<Void, JSONObject, String>
	{
		@Override
		protected String doInBackground(Void... params) {
			String Status = "false";
			try {
				RestAPI api = new RestAPI();
				
                JSONObject getStatus = api.getUId();
				//Parse the JSON Object to String
                JSONParser parser = new JSONParser();
                Status = parser.parseUId(getStatus);
                return Status;
                
           } catch (Exception e) {

        	   		Status=e.getMessage();
					return Status;
        }
	}
	protected void onPostExecute(String result) {
			try{
			String status = result;
			Toast.makeText(RegisterUser.this, "UId : "+result, Toast.LENGTH_SHORT).show();
			if(status.compareTo("false") == 0)
			{
				
			}
			else
			{
				id.setText(result);
			}
			}catch(Exception e)
			{
				Toast.makeText(RegisterUser.this, result, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public class AsynSetUser extends AsyncTask<String, JSONObject, String>
	{
		@Override
		protected String doInBackground(String... params) {
			String Status = "false";
			try {
				RestAPI api = new RestAPI();
				
                JSONObject getStatus = api.register(params[0], params[1], params[2], params[3], params[4]);
				//Parse the JSON Object to String
                JSONParser parser = new JSONParser();
                Status = parser.parseRs(getStatus);
                return Status;
                
           } catch (Exception e) {
					Toast.makeText(RegisterUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
					return Status;
        }
	}
		
	protected void onPostExecute(String result) {
		String status = result;
			
			if(status.compareTo("false")!=0)
			{
			   String i = id.getText().toString();
			   Toast.makeText(RegisterUser.this, "User Successfully added with UId : "+i, Toast.LENGTH_SHORT).show();
			   Intent i1 = new Intent(RegisterUser.this,LoginActivity.class);
			   startActivity(i1);
			}
			else
			{
				
			}
		}
	}
}
