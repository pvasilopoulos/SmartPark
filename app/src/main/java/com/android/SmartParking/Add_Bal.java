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
import android.widget.Toast;

public class Add_Bal extends Activity {

	EditText cno,cvvno,amt,name,mon;
	Button sub;
	String id = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_balance);
		
		cno = (EditText) findViewById(R.id.editText1);
		cvvno = (EditText) findViewById(R.id.EditText01);
		mon = (EditText) findViewById(R.id.EditText02);
		name = (EditText) findViewById(R.id.editText3);
		amt = (EditText) findViewById(R.id.editText2);
		
		Intent i1 = getIntent();
		id = i1.getStringExtra("ID");
		
		sub = (Button) findViewById(R.id.ab_button1);
		
		sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(cno.length()==16)
				{
					if(cvvno.length()==3)
					{
						if(name.length()!=0)
						{
							if(mon.length()==4)
							{
								if(amt.length()!=0)
								{
									String rs = amt.getText().toString();
									new AsyncAddBal().execute(id,rs);
								}
								else
								{
									Toast.makeText(Add_Bal.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
								}
							}
							else
							{
								Toast.makeText(Add_Bal.this, "Please Enter Valid Date", Toast.LENGTH_SHORT).show();
							}
						}
						else
						{
							Toast.makeText(Add_Bal.this, "Please Enter Name On Card No", Toast.LENGTH_SHORT).show();
						}
					}
					else
					{
						Toast.makeText(Add_Bal.this, "Please Enter 3 Digit CVV No", Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(Add_Bal.this, "Please Enter 16 Digit Card No", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
	}
	
	
	protected class AsyncAddBal extends AsyncTask<String, JSONObject, String> {
		 
        String userName=null;
        @Override
        protected String doInBackground(String... params) {

              RestAPI api = new RestAPI();
              String userAuth = "false";
              try {

                    // Call the User Authentication Method in API
                    JSONObject jsonObj = api.addbal(params[0],params[1]);

                    //Parse the JSON Object to boolean
                    JSONParser parser = new JSONParser();
                    userAuth = parser.parseUpdateBal(jsonObj);
                    userName = params[0];
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
        	
			if (result != "false" || result != "False") {
				Toast.makeText(Add_Bal.this, "Balance Updated Successfully",Toast.LENGTH_SHORT).show();
				cno.setText("");
				name.setText("");
				amt.setText("");
				cvvno.setText("");
				mon.setText("");
			}
			else
			{ 
				Toast.makeText(Add_Bal.this, "Your Balance could not be Updated due to Some Problem",Toast.LENGTH_SHORT).show();
			}
        }

  }
}
