package com.wero.cost;


import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Create extends Activity {

	  private TimePicker timePicker1;
	   private TextView time;
	   private Calendar calendar;
	   private String format = "";
	   Button ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		
		 timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
	      calendar = Calendar.getInstance();
	      ok= (Button)findViewById(R.id.button1);
	   
	      GregorianCalendar oCalendar = new GregorianCalendar();

	      int hour = calendar.get(Calendar.HOUR_OF_DAY);
	      int min = calendar.get(Calendar.MINUTE);
	    


	      

	        ok.setOnClickListener(new View.OnClickListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void onClick(View v) {
					int hour = timePicker1.getCurrentHour();
				      int min = timePicker1.getCurrentMinute();
					// TODO Auto-generated method stub
					Intent intent = new Intent(Create.this,Many.class);
					intent.putExtra("hour",hour);
					intent.putExtra("min",min);
					intent.putExtra("hour",hour);
					intent.putExtra("min",min);
					intent.putExtra("hour",hour);
					intent.putExtra("min",min);
					startActivity(intent);		
				//	Toast.makeText(Create.this,timePicker1.getCurrentHour()+":"+timePicker1.getCurrentMinute() , Toast.LENGTH_SHORT).show();
					
				}
			});
	      
	      
	}



	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
