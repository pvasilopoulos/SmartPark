package com.android.SmartParking;
 
import org.json.JSONObject;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

 
public class LoginActivity extends Activity {
 
      EditText etUserName, etPassword;
      Button btnLogin,btnReg;
      Context context;
 
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            // Initialize  the layout components
            context=this;
            etUserName = (EditText) findViewById(R.id.login_id);
            etPassword = (EditText) findViewById(R.id.login_pass);
            btnLogin = (Button) findViewById(R.id.login_button1);
            btnReg = (Button) findViewById(R.id.log_reg);
            
            etUserName.setText("");
            etPassword.setText("");

            Boolean ans=weHavePermissionforGPS();
            if(!ans)
            {
                requestforGPSPermissionFirst();
            }
            else
            {
                GPSTracker gps=new GPSTracker(this,LoginActivity.this);
                if (gps.canGetLocation())
                {

                }
                else
                {
                    Toast.makeText(this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
                }
            }

            if( getIntent().getBooleanExtra("Exit me", false)){
                finish();
                return; // add this to prevent from doing unnecessary stuffs
            }
            
            etUserName.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(etUserName, InputMethodManager.SHOW_IMPLICIT);
            
            
            btnLogin.setOnClickListener(new View.OnClickListener() {
 
                  @Override
                  public void onClick(View v) {
                        // TODO Auto-generated method stub
                	  	
                        String username=etUserName.getText().toString();
                        String password=etPassword.getText().toString();
                        if(username.compareTo("")!=0 && password.compareTo("")!=0)
                        {

                            Boolean ans=weHavePermissionforGPS();
                            if(!ans)
                            {
                                requestforGPSPermissionFirst();
                            }
                            else
                            {
                                GPSTracker gps=new GPSTracker(LoginActivity.this,LoginActivity.this);
                                if (gps.canGetLocation())
                                {
                                    new login().execute(username,password);
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else
                        {
                        	Toast.makeText(getApplicationContext(),"All Fieds are Mandatory", Toast.LENGTH_SHORT).show();
                        }
                        
 
                  }
            });
            
            btnReg.setOnClickListener(new View.OnClickListener() {
            	 
                @Override
                public void onClick(View v) {
                      // TODO Auto-generated method stub

                      Intent i1 = new Intent(LoginActivity.this, RegisterUser.class);
                      startActivity(i1);
                      }
          });
 
      }
      
      public class login extends AsyncTask<String, JSONObject, String>{
    	  
    	  
		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.ulogin(params[0], params[1]);
				JSONParser jp=new JSONParser();
				a=jp.loginuser(json);
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
			if(b.compareTo("true")==0)
			{
				Intent i=new Intent(LoginActivity.this,MainActivity.class);
				i.putExtra("ID", etUserName.getText().toString());
				startActivity(i);
			}
			else if(b.compareTo("false")==0)
			{
		 		Toast.makeText(LoginActivity.this, "Invalid User Credentials", Toast.LENGTH_SHORT).show();
		 		etUserName.setText("");
		 		etPassword.setText("");
			}
			else
			{
				Toast.makeText(LoginActivity.this, "Problem in Login :"+"\n"+b, Toast.LENGTH_SHORT).show();
			}
			
		}
      }


    private boolean weHavePermissionforGPS()
    {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestforGPSPermissionFirst()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION))
        {
            requestForResultContactsPermission();
        }
        else
        {
            requestForResultContactsPermission();
        }
    }
    private void requestForResultContactsPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
    }
}