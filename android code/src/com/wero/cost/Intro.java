package com.wero.cost;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;


public class Intro extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		
		
		 Handler handler = new Handler();
	       handler.postDelayed(new Runnable(){
	       	public void run(){
	        		finish();
	      	}
	      },2000);//�ڵ鷯
		
		
		
	}

	
}
