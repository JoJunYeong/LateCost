package com.wero.cost;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		

		startActivity(new Intent(this, Intro.class));
		
		Button bt1 = (Button)findViewById(R.id.button1);
		bt1.setOnClickListener(this);
		
		Button bt2 = (Button)findViewById(R.id.button2);
		bt2.setOnClickListener(this);
		
		Button bt3 = (Button)findViewById(R.id.button3);
		bt3.setOnClickListener(this);
		
		
	}

	
	

	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:		//일반사용자
			Intent intent1 = new Intent(this,Create.class);
			startActivity(intent1);			
			break;

		case R.id.button2:		//관리자
			Intent intent2 = new Intent(this,Create_admin.class);
			startActivity(intent2);		
			break;
		case R.id.button3:		//관리자
			Intent intent3 = new Intent(this,Login.class);
			startActivity(intent3);		
			break;	
		
		}		
		
//		finish();
		
		
		
		
	}
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
