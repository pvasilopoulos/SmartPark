package com.android.SmartParking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

public class Layout extends Activity {
	ScrollView scroll;
	Button a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,search,book;
	String Id = "",booked="",region="",slot="",cdate="",t="",et="";
	int cou=0;
	String rs="50",sl="";
	Spinner stime, duration;
	EditText date,cardno,cvvno,cardval,cardname,bookamt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.p_layout);
		scroll=(ScrollView) findViewById(R.id.ScrollView1);
		a1 = (Button) findViewById(R.id.a1);
		a2 = (Button) findViewById(R.id.a2);
		a3 = (Button) findViewById(R.id.a3);
		a4 = (Button) findViewById(R.id.a4);
		a5 = (Button) findViewById(R.id.a5);
		a6 = (Button) findViewById(R.id.a6);
		a7 = (Button) findViewById(R.id.a7);
		a8 = (Button) findViewById(R.id.a8);
		a9 = (Button) findViewById(R.id.a9);
		a10 = (Button) findViewById(R.id.a10);

		search = (Button) findViewById(R.id.p_search);

		date = (EditText) findViewById(R.id.l_date);

		stime = (Spinner) findViewById(R.id.p_time);
		duration = (Spinner) findViewById(R.id.p_duration);

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		cdate = df.format(c.getTime());
		date.setText(cdate);


		book=(Button) findViewById(R.id.bookbtn);
		cardno=(EditText) findViewById(R.id.cardno);
		cvvno=(EditText) findViewById(R.id.cvvno);
		cardval=(EditText) findViewById(R.id.cardval);
		cardname=(EditText) findViewById(R.id.cardname);
		bookamt=(EditText) findViewById(R.id.bookamt);

		String dur = "1 Hrs,2 Hrs,3 Hrs,6 Hrs,12 Hrs,23 Hrs";
		List<String> l1 = Arrays.asList(dur.split(","));
		ArrayAdapter<String> adp=new ArrayAdapter<String>(Layout.this,android.R.layout.simple_list_item_1,l1);
		duration.setAdapter(adp);


		String time = "1:00,2:00,3:00,4:00,5:00,6:00,7:00,8:00,9:00,10:00,11:00,12:00,13:00,14:00,15:00,16:00,17:00,18:00,19:00,20:00,21:00,22:00,23:00";
		List<String> l = Arrays.asList(time.split(","));
		ArrayAdapter<String> adp1=new ArrayAdapter<String>(Layout.this,android.R.layout.simple_list_item_1,l);
		stime.setAdapter(adp1);


		Intent i1 = getIntent();
		region = i1.getStringExtra("Region");
		Id = i1.getStringExtra("ID");
		slot = i1.getStringExtra("Slot");

		new AsynGetlay().execute(slot,region);
		enab(false);



		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				Calendar c = Calendar.getInstance();
				SimpleDateFormat dft = new SimpleDateFormat("yyyy/MM/dd");
				String todate = dft.format(c.getTime());

				String gdate = date.getText().toString();

				if(gdate.compareTo(todate)>=0)
				{
					t = stime.getSelectedItem().toString();
					String dur = duration.getSelectedItem().toString();
					if(dur.compareTo("1 Hrs") == 0)
					{
						SimpleDateFormat df = new SimpleDateFormat("HH:MM");
						try {
							Date date = df.parse(t);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							cal.add(Calendar.HOUR, 1);

							et = df.format(cal.getTime());

							rs = "50";
							bookamt.setText("Rs : "+rs);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					else if(dur.compareTo("2 Hrs") == 0)
					{
						SimpleDateFormat df = new SimpleDateFormat("HH:MM");
						try {
							Date date = df.parse(t);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							cal.add(Calendar.HOUR, 2);
							rs = "80";
							et = df.format(cal.getTime());
							bookamt.setText("Rs : "+rs);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if(dur.compareTo("3 Hrs") == 0)
					{
						SimpleDateFormat df = new SimpleDateFormat("HH:MM");
						try {
							Date date = df.parse(t);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							cal.add(Calendar.HOUR, 3);
							rs = "120";
							et = df.format(cal.getTime());
							bookamt.setText("Rs : "+rs);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if(dur.compareTo("6 Hrs") == 0)
					{
						SimpleDateFormat df = new SimpleDateFormat("HH:MM");
						try {
							Date date = df.parse(t);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							cal.add(Calendar.HOUR, 6);
							rs = "200";
							et = df.format(cal.getTime());
							bookamt.setText("Rs : "+rs);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if(dur.compareTo("12 Hrs") == 0)
					{
						SimpleDateFormat df = new SimpleDateFormat("HH:MM");
						try {
							Date date = df.parse(t);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							cal.add(Calendar.HOUR, 12);
							rs = "350";
							et = df.format(cal.getTime());
							bookamt.setText("Rs : "+rs);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if(dur.compareTo("23 Hrs") == 0)
					{
						SimpleDateFormat df = new SimpleDateFormat("HH:MM");
						try {
							Date date = df.parse(t);

							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							cal.add(Calendar.HOUR, 24);
							rs = "600";
							et = df.format(cal.getTime());
							bookamt.setText("Rs : "+rs);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					t = t.substring(0,t.length()-3);
					et = et.substring(0,et.length()-3);

					cdate = date.getText().toString();

					new AsynCheck().execute(region,slot,cdate,t,et);

				}
				else
				{
					Toast.makeText(Layout.this, "Date can not be less then Today's Date", Toast.LENGTH_SHORT).show();
				}
			}
		});


		a1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				a1.setBackgroundColor(Color.GREEN);
				sl="1";

				payment();
			}
		});


		a2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				a2.setBackgroundColor(Color.GREEN);
				sl="2";

				payment();
			}
		});


		a3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				a3.setBackgroundColor(Color.GREEN);
				sl="3";

				payment();
			}
		});


		a4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				a4.setBackgroundColor(Color.GREEN);
				sl="4";

				payment();
			}
		});


		a5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				a5.setBackgroundColor(Color.GREEN);
				sl="5";

				payment();
			}
		});


		a6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				a6.setBackgroundColor(Color.GREEN);
				sl="6";

				payment();
			}
		});

		a7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				a7.setBackgroundColor(Color.GREEN);
				sl="7";

				payment();
			}
		});

		a8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				a8.setBackgroundColor(Color.GREEN);
				sl="8";

				payment();
			}
		});

		a9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				a9.setBackgroundColor(Color.GREEN);
				sl="9";

				payment();
			}
		});

		a10.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				a10.setBackgroundColor(Color.GREEN);
				sl="10";

				payment();
			}
		});

	}

	public void enab(Boolean b)
	{
		a1.setEnabled(b);
		a2.setEnabled(b);
		a3.setEnabled(b);
		a4.setEnabled(b);
		a5.setEnabled(b);
		a6.setEnabled(b);
		a7.setEnabled(b);
		a8.setEnabled(b);
		a9.setEnabled(b);
		a10.setEnabled(b);
	}

	public class AsynCheck extends AsyncTask<String, JSONObject, String>
	{
		@Override
		protected String doInBackground(String... params) {
			String Status = "false";
			try {
				RestAPI api = new RestAPI();

				JSONObject getStatus = api.checkslot(params[0],params[1],params[2],params[3],params[4]);
				//Parse the JSON Object to String
				JSONParser parser = new JSONParser();
				Status = parser.parseUId(getStatus);
				return Status;

			} catch (Exception e) {
				Toast.makeText(Layout.this, e.getMessage(), Toast.LENGTH_SHORT).show();
				return Status;
			}
		}

		protected void onPostExecute(String result) {
			String status = result;
			if(status.compareTo("false") != 0)
			{
				if(status.contains("1")==true)
				{
					a1.setEnabled(false);
					a1.setBackgroundColor(Color.RED);
					a1.setTextColor(Color.WHITE);
				}
				else
				{
					paymentdis();
					a1.setEnabled(true);
					a1.setBackgroundColor(Color.WHITE);
					a1.setTextColor(Color.BLACK);
				}
				if(status.contains("2")==true)
				{
					a2.setEnabled(false);
					a2.setTextColor(Color.WHITE);
					a2.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a2.setTextColor(Color.BLACK);
					a2.setEnabled(true);
					a2.setBackgroundColor(Color.WHITE);
				}
				if(status.contains("3")==true)
				{
					a3.setTextColor(Color.WHITE);
					a3.setEnabled(false);
					a3.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a3.setTextColor(Color.BLACK);
					a3.setEnabled(true);
					a3.setBackgroundColor(Color.WHITE);
				}
				if(status.contains("4")==true)
				{
					a4.setTextColor(Color.WHITE);
					a4.setEnabled(false);
					a4.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a4.setTextColor(Color.BLACK);
					a4.setEnabled(true);
					a4.setBackgroundColor(Color.WHITE);
				}
				if(status.contains("5")==true)
				{
					a5.setTextColor(Color.WHITE);
					a5.setEnabled(false);
					a5.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a5.setTextColor(Color.BLACK);
					a5.setEnabled(true);
					a5.setBackgroundColor(Color.WHITE);
				}
				if(status.contains("6")==true)
				{
					a6.setTextColor(Color.WHITE);
					a6.setEnabled(false);
					a6.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a6.setTextColor(Color.BLACK);
					a6.setEnabled(true);
					a6.setBackgroundColor(Color.WHITE);
				}
				if(status.contains("7")==true)
				{
					a7.setTextColor(Color.WHITE);
					a7.setEnabled(false);
					a7.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a7.setTextColor(Color.BLACK);
					a7.setEnabled(true);
					a7.setBackgroundColor(Color.WHITE);
				}
				if(status.contains("8")==true)
				{
					a8.setTextColor(Color.WHITE);
					a8.setEnabled(false);
					a8.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a8.setTextColor(Color.BLACK);
					a8.setEnabled(true);
					a8.setBackgroundColor(Color.WHITE);
				}
				if(status.contains("9")==true)
				{
					a9.setTextColor(Color.WHITE);
					a9.setEnabled(false);
					a9.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a9.setTextColor(Color.BLACK);
					a9.setEnabled(true);
					a9.setBackgroundColor(Color.WHITE);
				}
				if(status.contains("10")==true)
				{
					a10.setTextColor(Color.WHITE);
					a10.setEnabled(false);
					a10.setBackgroundColor(Color.RED);
				}
				else
				{
					paymentdis();
					a10.setTextColor(Color.BLACK);
					a10.setEnabled(true);
					a10.setBackgroundColor(Color.WHITE);
				}

				date.setEnabled(false);
				stime.setEnabled(false);
				duration.setEnabled(false);
			}
			else
			{
				enab(true);
			}
		}
	}


	public class AsynBook extends AsyncTask<String, JSONObject, String>
	{
		@Override
		protected String doInBackground(String... params) {
			String Status = "false";
			try {
				RestAPI api = new RestAPI();

				JSONObject getStatus = api.BSlots(params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7],params[8]);
				//Parse the JSON Object to String
				JSONParser parser = new JSONParser();
				Status = parser.parseUId(getStatus);
				return Status;

			} catch (Exception e) {
				Status=e.getMessage();
				Toast.makeText(Layout.this, e.getMessage(), Toast.LENGTH_SHORT).show();
				return Status;
			}
		}

		protected void onPostExecute(String result) {
			String status = result;
			if(status.compareTo("false") == 0)
			{
				Toast.makeText(Layout.this, result, Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(Layout.this, "Booking Done with ID : "+result, Toast.LENGTH_SHORT).show();
				Intent i=new Intent(Layout.this,MainActivity.class);
				i.putExtra("ID", Id);
				startActivity(i);
				finish();
			}
		}
	}
	public class AsynGetlay extends AsyncTask<String, JSONObject, String>
	{
		@Override
		protected String doInBackground(String... params) {
			String Status = "false";
			try {
				RestAPI api = new RestAPI();

				JSONObject getStatus = api.getmslots(params[0],params[1]);
				//Parse the JSON Object to String
				JSONParser parser = new JSONParser();
				Status = parser.parseUId(getStatus);
				return Status;

			} catch (Exception e) {
				Toast.makeText(Layout.this, e.getMessage(), Toast.LENGTH_SHORT).show();
				return Status;
			}
		}

		protected void onPostExecute(String result) {
			String status = result;
			if(status.compareTo("false") == 0)
			{
				Toast.makeText(Layout.this, result, Toast.LENGTH_SHORT).show();
			}
			else
			{
				cou = Integer.parseInt(result);
				if(cou == 10)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(128);
					a4.setAlpha(128);
					a5.setAlpha(128);
					a6.setAlpha(128);
					a7.setAlpha(128);
					a8.setAlpha(128);
					a9.setAlpha(128);
					a10.setAlpha(128);
				}

				if(cou == 9)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(128);
					a4.setAlpha(128);
					a5.setAlpha(128);
					a6.setAlpha(128);
					a7.setAlpha(128);
					a8.setAlpha(128);
					a9.setAlpha(128);
					a10.setAlpha(0);
				}

				if(cou == 8)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(128);
					a4.setAlpha(128);
					a5.setAlpha(128);
					a6.setAlpha(128);
					a7.setAlpha(128);
					a8.setAlpha(128);
					a9.setAlpha(0);
					a10.setAlpha(0);
				}

				if(cou == 7)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(128);
					a4.setAlpha(128);
					a5.setAlpha(128);
					a6.setAlpha(128);
					a7.setAlpha(128);
					a8.setAlpha(0);
					a9.setAlpha(0);
					a10.setAlpha(0);
				}

				if(cou == 6)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(128);
					a4.setAlpha(128);
					a5.setAlpha(128);
					a6.setAlpha(128);
					a7.setAlpha(0);
					a8.setAlpha(0);
					a9.setAlpha(0);
					a10.setAlpha(0);
				}

				if(cou == 5)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(128);
					a4.setAlpha(128);
					a5.setAlpha(128);
					a6.setAlpha(0);
					a7.setAlpha(0);
					a8.setAlpha(0);
					a9.setAlpha(0);
					a10.setAlpha(0);
				}

				if(cou == 4)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(128);
					a4.setAlpha(128);
					a5.setAlpha(0);
					a6.setAlpha(0);
					a7.setAlpha(0);
					a8.setAlpha(0);
					a9.setAlpha(0);
					a10.setAlpha(0);
				}

				if(cou == 3)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(128);
					a4.setAlpha(0);
					a5.setAlpha(0);
					a6.setAlpha(0);
					a7.setAlpha(0);
					a8.setAlpha(0);
					a9.setAlpha(0);
					a10.setAlpha(0);
				}

				if(cou == 2)
				{
					a1.setAlpha(128);
					a2.setAlpha(128);
					a3.setAlpha(0);
					a4.setAlpha(0);
					a5.setAlpha(0);
					a6.setAlpha(0);
					a7.setAlpha(0);
					a8.setAlpha(0);
					a9.setAlpha(0);
					a10.setAlpha(0);
				}

				if(cou == 1)
				{
					a1.setAlpha(128);
					a2.setAlpha(0);
					a3.setAlpha(0);
					a4.setAlpha(0);
					a5.setAlpha(0);
					a6.setAlpha(0);
					a7.setAlpha(0);
					a8.setAlpha(0);
					a9.setAlpha(0);
					a10.setAlpha(0);
				}
				//Toast.makeText(lay.this, result, Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void payment()
	{
		cardno.requestFocus();
		scroll.fullScroll(View.FOCUS_DOWN);
		cardno.setEnabled(true);
		cardname.setEnabled(true);
		cvvno.setEnabled(true);
		cardval.setEnabled(true);
		bookamt.setEnabled(true);
		book.setEnabled(true);
	}

	public void paymentdis()
	{
		cardno.setEnabled(false);
		cardname.setEnabled(false);
		cvvno.setEnabled(false);
		cardval.setEnabled(false);
		bookamt.setEnabled(false);
		book.setEnabled(false);
	}
	public void onbook(View v)
	{
		if(cardno.getText().toString().compareTo("")!=0 && cvvno.getText().toString().compareTo("")!=0 && cardval.getText().toString().compareTo("")!=0 && cardname.getText().toString().compareTo("")!=0 && bookamt.getText().toString().compareTo("")!=0)
		{
			if(cardno.getText().toString().length()==16)
			{
				if(cvvno.getText().toString().length()==3)
				{
					if(cardval.getText().toString().length()==4)
					{
						new AsynBook().execute(region, slot, sl, sl, date.getText().toString(), t, et, Id, rs);
						enab(false);
					}
					else
					{
						Toast.makeText(this, "Card Validity should be of 4 digits MMYY without Space", Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(this, "CVV No should be of 3 digits", Toast.LENGTH_SHORT).show();
				}
			}
			else
			{
				Toast.makeText(this, "Card No should be of 16 digits", Toast.LENGTH_SHORT).show();
			}

		}
		else
		{
			Toast.makeText(this, "Payment details are Mandatory", Toast.LENGTH_SHORT).show();
		}
	}
}
