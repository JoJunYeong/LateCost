package com.wero.cost;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	
	String ID_result="";
	String ID = "";
	String Password = "";		// 받아올 아이들 미리 변수선언함
	EditText edtID,edtPassword;
	Button ok,create;
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.76.143/cost";
	
	
	@SuppressLint("NewApi")
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		
		ok= (Button)findViewById(R.id.button1);
        create= (Button)findViewById(R.id.button2);
   	
		
        create.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(Login.this,Create_admin.class);
				startActivity(intent2);		
				
			}
		});
		
        ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				edtID = (EditText)findViewById(R.id.ID);
			    edtPassword = (EditText)findViewById(R.id.Password);
				
				
				if(edtID.getText().toString().equals("")||edtPassword.getText().toString().equals("")){
					Toast.makeText(Login.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
					return;
					
				}
				
				ID = edtID.getText().toString();
				Password = edtPassword.getText().toString();
					
				
				
				try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/admin_login.php?"
							+ "ID=" + URLEncoder.encode(ID,"UTF-8")
							+ "&Password=" + URLEncoder.encode(Password,"UTF-8")
							);					
					url.openStream();		
					String result = getXmlData("admin_login.xml", "result");
					ID_result = result;
				} catch(Exception e) {
					Toast.makeText(Login.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
				
				if(ID_result.equals("1"))
				{
					Toast.makeText(Login.this, "로그인 성공.", Toast.LENGTH_SHORT).show();
					
					Intent intent1 = new Intent(Login.this,Conform_no.class);
					
					intent1.putExtra("ID",ID);//여긴 아이디를 보냅니다.
					intent1.putExtra("Password",Password);//여긴 비밀번호를 보냅니다.
					
					startActivity(intent1);		
					
					edtID.setText("");
					edtPassword.setText("");
					
					
					
				}
				else if(ID_result.equals("0"))
				{
					Toast.makeText(Login.this, "아이디 혹은 비밀번호 오류입니다.", Toast.LENGTH_SHORT).show();
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		
		
		
		
		
	}

	

	 private String getXmlData(String filename, String str){
			String rss = SERVER_ADDRESS + "/";
			String ret = "";
			
			try{
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				URL server = new URL(rss + filename);
				InputStream is = server.openStream();
				xpp.setInput(is, "UTF-8");
				
				int eventType = xpp.getEventType();
				
				while(eventType != XmlPullParser.END_DOCUMENT) {
					if(eventType == XmlPullParser.START_TAG) {
						if(xpp.getName().equals(str)) {
							ret = xpp.nextText();
						}
					}
					eventType = xpp.next();
					
				}
			} catch(Exception e) {
				Log.e("Error", e.getMessage());
			}
			
			return ret;
		}
		
	    private ArrayList<String> getXmlDataList(String filename, String str) {
			String rss = SERVER_ADDRESS + "/";
			ArrayList<String> ret = new ArrayList<String>();
			
			try {
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				URL server = new URL(rss + filename);
				InputStream is = server.openStream();
				xpp.setInput(is, "UTF-8");
				
				int eventType = xpp.getEventType();
				
				while(eventType != XmlPullParser.END_DOCUMENT) {
					if(eventType == XmlPullParser.START_TAG) {
						if(xpp.getName().equals(str)) { //占승깍옙 占싱몌옙占쏙옙 str 占쏙옙占쌘곤옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�
							ret.add(xpp.nextText());
						}
					}
					eventType = xpp.next();
				}
			} catch(Exception e) {
				Log.e("Error", e.getMessage());
			}
			
			return ret;
	    }
		
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
