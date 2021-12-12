package com.wero.cost;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
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

public class Many extends Activity {

	int hour,min,count=5;
	String S_count="", S_hour="",S_min="",result="",join5="",join4="",join3="",join2="",join1="",cost="",result2="",result3="",result4="",result5="";
	EditText ejoin5,ejoin4,ejoin3,ejoin2,ejoin1,ecost;
	Button ok;
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.76.143/cost";
	int i=0,ii=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_many);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		
		ok= (Button)findViewById(R.id.button1);
		 Intent intent = getIntent();
			hour = intent.getIntExtra("hour",0);	
			min = intent.getIntExtra("min",0);	
				
			Toast.makeText(Many.this,hour+":"+min , Toast.LENGTH_SHORT).show();
		
		S_hour= String.valueOf(hour);
		S_min= String.valueOf(min);
		ejoin1 = (EditText)findViewById(R.id.join1);
		ejoin2 = (EditText)findViewById(R.id.join2);
		ejoin3 = (EditText)findViewById(R.id.join3);
		ejoin4 = (EditText)findViewById(R.id.join4);
		ejoin5 = (EditText)findViewById(R.id.join5);
		ecost = (EditText)findViewById(R.id.cost);
		
		 ok.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					count=5;
					if(ejoin1.getText().toString().equals("")&&ejoin2.getText().toString().equals("")&&ejoin3.getText().toString().equals("")
							&&ejoin4.getText().toString().equals("")&&ejoin5.getText().toString().equals(""))
					{
						Toast.makeText(Many.this, "참가자를 자신을 포함해서 적어도 한명 이상은 입력하세요.", Toast.LENGTH_SHORT).show();
						return;
					}
					if(ecost.getText().toString().equals(""))
					{
						Toast.makeText(Many.this, "벌금을 정하세요.", Toast.LENGTH_SHORT).show();
						return;
					}
					
					join1 = ejoin1.getText().toString();
					
					join2 = ejoin2.getText().toString();
					
					join3 = ejoin3.getText().toString();
					
					join4 = ejoin4.getText().toString();
					
					join5 = ejoin5.getText().toString();
					cost = ecost.getText().toString();
					
					if(join1.equals(""))
						count--;
					if(join2.equals(""))
						count--;
					if(join3.equals(""))
						count--;
					if(join4.equals(""))
						count--;
					if(join5.equals(""))
						count--;
					
					
					
					try{//////////////////////////////이름적은사람들 중 이미 약속이 잡힌 사람이 있는지 점사
						URL url = new URL(SERVER_ADDRESS + "/exsist_check.php?"
								+ "hour=" + URLEncoder.encode(S_hour,"UTF-8")
								+ "&min=" + URLEncoder.encode(S_min,"UTF-8")
								+ "&join1=" + URLEncoder.encode(join1,"UTF-8")
								+ "&join2=" + URLEncoder.encode(join2,"UTF-8")
								+ "&join3=" + URLEncoder.encode(join3,"UTF-8")
								+ "&join4=" + URLEncoder.encode(join4,"UTF-8")
								+ "&join5=" + URLEncoder.encode(join5,"UTF-8")
								+ "&cost=" + URLEncoder.encode(cost,"UTF-8")
								);					
						url.openStream();		
						result = getXmlData("exsist_check.xml", "result");
						result2 = getXmlData("exsist_check.xml", "result2");
						result3 = getXmlData("exsist_check.xml", "result3");
						result4 = getXmlData("exsist_check.xml", "result4");
						result5 = getXmlData("exsist_check.xml", "result5");
						
					} catch(Exception e) {
						Toast.makeText(Many.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
			
				if(result.equals("0")){
					if(join1.equals(""))
					{
						join1="null";
					}
					else
					{
					Toast.makeText(Many.this, "참가자1은 가입되어있지 않습니다.", Toast.LENGTH_SHORT).show();
					return;
					}
				}
				if(result2.equals("0")){
					if(join2.equals(""))
					{
						join2="null";
					}
					else
					{
					Toast.makeText(Many.this, "참가자2는 가입되어있지 않습니다.", Toast.LENGTH_SHORT).show();
					return;
					}
				}
				if(result3.equals("0")){
					if(join3.equals(""))
					{
						join3="null";
					}
					else
					{
					Toast.makeText(Many.this, "참가자3은 가입되어있지 않습니다.", Toast.LENGTH_SHORT).show();
					return;
					}
				}
				if(result4.equals("0")){
					if(join4.equals(""))
					{
						join4="null";
						}
					else
					{
					Toast.makeText(Many.this, "참가자4는 가입되어있지 않습니다.", Toast.LENGTH_SHORT).show();
					return;
					}
				}
				if(result5.equals("0")){
					if(join5.equals(""))
					{
						join5="null";
						}
					else
					{
					Toast.makeText(Many.this, "참가자5는 가입되어있지 않습니다.", Toast.LENGTH_SHORT).show();
					return;
					}
				}
				
					
					
					
					
					
					
					
					
						 try{//////////////////////////////이름적은사람들 중 이미 약속이 잡힌 사람이 있는지 점사
								URL url = new URL(SERVER_ADDRESS + "/at_create_check.php?"
										+ "hour=" + URLEncoder.encode(S_hour,"UTF-8")
										+ "&min=" + URLEncoder.encode(S_min,"UTF-8")
										+ "&join1=" + URLEncoder.encode(join1,"UTF-8")
										+ "&join2=" + URLEncoder.encode(join2,"UTF-8")
										+ "&join3=" + URLEncoder.encode(join3,"UTF-8")
										+ "&join4=" + URLEncoder.encode(join4,"UTF-8")
										+ "&join5=" + URLEncoder.encode(join5,"UTF-8")
										+ "&cost=" + URLEncoder.encode(cost,"UTF-8")
										);					
								url.openStream();		
								result = getXmlData("at_create_check.xml", "result");
								result2 = getXmlData("at_create_check.xml", "result2");
								result3 = getXmlData("at_create_check.xml", "result3");
								result4 = getXmlData("at_create_check.xml", "result4");
								result5 = getXmlData("at_create_check.xml", "result5");
								
							} catch(Exception e) {
								Toast.makeText(Many.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
					
						if(result.equals("1")){
							Toast.makeText(Many.this, "참가자1은 이미 다른약속이 있습니다.", Toast.LENGTH_SHORT).show();
							return;
						}
						if(result2.equals("1")){
							Toast.makeText(Many.this, "참가자2는 이미 다른약속이 있습니다.", Toast.LENGTH_SHORT).show();
							return;
						}
						if(result3.equals("1")){
							Toast.makeText(Many.this, "참가자3은 이미 다른약속이 있습니다.", Toast.LENGTH_SHORT).show();
							return;
						}
						if(result4.equals("1")){
							Toast.makeText(Many.this, "참가자4는 이미 다른약속이 있습니다.", Toast.LENGTH_SHORT).show();
							return;
						}
						if(result5.equals("1")){
							Toast.makeText(Many.this, "참가자5는 이미 다른약속이 있습니다.", Toast.LENGTH_SHORT).show();
							return;
						}
						
						
						S_count = String.valueOf(count);
						if(join1.equals("null"))
							join1="";
						if(join2.equals("null"))
							join2="";
						if(join3.equals("null"))
							join3="";
						if(join4.equals("null"))
							join4="";
						if(join5.equals("null"))
							join5="";
							 try{//////////////////////////////이름적은사람들 중 이미 약속이 잡힌 사람이 있는지 점사
									URL url = new URL(SERVER_ADDRESS + "/at_create.php?"
											+ "hour=" + URLEncoder.encode(S_hour,"UTF-8")
											+ "&min=" + URLEncoder.encode(S_min,"UTF-8")
											+ "&join1=" + URLEncoder.encode(join1,"UTF-8")
											+ "&join2=" + URLEncoder.encode(join2,"UTF-8")
											+ "&join3=" + URLEncoder.encode(join3,"UTF-8")
											+ "&join4=" + URLEncoder.encode(join4,"UTF-8")
											+ "&join5=" + URLEncoder.encode(join5,"UTF-8")
											+ "&cost=" + URLEncoder.encode(cost,"UTF-8")
											+ "&count=" + URLEncoder.encode(S_count,"UTF-8")
											);					
									url.openStream();		
									result = getXmlData("at_create_check.xml", "result");
									Toast.makeText(Many.this, "설정완료.", Toast.LENGTH_SHORT).show();
									finish();
									
								} catch(Exception e) {
									Toast.makeText(Many.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
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
					if(xpp.getName().equals(str)) {
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
		getMenuInflater().inflate(R.menu.many, menu);
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
