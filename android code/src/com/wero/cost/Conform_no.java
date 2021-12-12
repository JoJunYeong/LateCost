package com.wero.cost;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

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
import android.widget.TextView;
import android.widget.Toast;

public class Conform_no extends Activity {

	
	String ID = "";
	String Password = "";		// 받아올 아이들 미리 변수선언함
	Button refresh,delete;
	String ok1,no1;
	String arrive = "",result="",result2="",result3="";		// 받아올 아이들 미리 변수선언함
	String cost = "";
	String day = "";
	String time = "";
	String t="";
	String k_hour = "";
	String name = "";
	String k_min = "";		// 받아올 아이들 미리 변수선언함
	String seat = "";
	String phone = "";
	String yesno="";
	String ready="";
	String ready2="";
	String check="";
	String g3="";
	String student1="";
	String student2="";
	String money="";
	String count="";
	int i_cost=0;
	int i_count=0;
	int i_money=0;
	int i=0;
	int a=0;
	int q=0;
	int s_hour=0,s_min=0;
	 private Calendar calendar;
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.76.143/cost";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conform_no);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    calendar = Calendar.getInstance();
	    Intent intent = getIntent();
		ID = intent.getStringExtra("ID");		// ID값을 받아옴
		Password = intent.getStringExtra("Password");		// Password값을 받아옴
	
		
		final int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    final int min = calendar.get(Calendar.MINUTE);
	// 	Toast.makeText(Conform_no.this,hour+":"+min , Toast.LENGTH_SHORT).show();
	    k_hour = String.valueOf(hour);
	    k_min = String.valueOf(min);

   	    refresh= (Button)findViewById(R.id.button1);
   	    delete= (Button)findViewById(R.id.button2);
   	    
   	    
   	    try{//////////////////////////////예약 신청 / 취소
			URL url = new URL(SERVER_ADDRESS + "/exsist_check2.php?"
					+ "ID=" + URLEncoder.encode(ID,"UTF-8")
					);					
			url.openStream();		
			String result = getXmlData("exsist_check2.xml", "result");
			t = result;
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
	    
   	    
   	    if(t.equals("0"))
   	    {
   	    	Toast.makeText(Conform_no.this, "약속이 없습니다.", Toast.LENGTH_SHORT).show();
   	    	i=1;
   	    	name=ID;
   	    	time="약속없음";
   	    	arrive="약속없음";
   	    	cost="약속없음";
   	    }
   	    else
   	    {
	   	     try{//////////////////////////////예약 신청 / 취소
	 			URL url = new URL(SERVER_ADDRESS + "/arrive_check.php?"
	 					+ "ID=" + URLEncoder.encode(ID,"UTF-8")
	 					);					
	 			url.openStream();		
	 			 result = getXmlData("arrive_check.xml", "result");
	 			result2 = getXmlData("arrive_check.xml", "result2");
	 			result3 = getXmlData("arrive_check.xml", "result3");
	 		} catch(Exception e) {
	 			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
	 			Log.e("Error", e.getMessage());
	 		}
	   	  s_hour  = Integer.parseInt(result);
	   	  s_min  = Integer.parseInt(result2);
   	    cost=	result3;
   	i_cost   = Integer.parseInt(cost);
 
	   	  
	 //  	Toast.makeText(Conform_no.this,s_hour+":"+s_min +"/"+cost+"원", Toast.LENGTH_SHORT).show();
   	    	
	   	name=ID;
	    time=s_hour+":"+s_min;	
		   	
   	    	
   	    }
   	    
   	    
   	    
   	    
   	    
   	    
   	    TextView testView1 = (TextView)findViewById(R.id.textView1);
		testView1.setText(name);
		TextView testView2 = (TextView)findViewById(R.id.textView2);
		testView2.setText(time);
		TextView testView3 = (TextView)findViewById(R.id.textView3);
		testView3.setText(arrive);
		TextView testView4 = (TextView)findViewById(R.id.textView4);
		testView4.setText("확인버튼을 눌러주세요.");
   	    
   	    

        refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=0;
				
				 try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/exsist_check2.php?"
								+ "ID=" + URLEncoder.encode(ID,"UTF-8")
								);					
						url.openStream();		
						String result = getXmlData("exsist_check2.xml", "result");
						t = result;
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
				    
			   	    
			   	    if(t.equals("0"))
			   	    {
			   	    	Toast.makeText(Conform_no.this, "약속이 없습니다.", Toast.LENGTH_SHORT).show();
			   	    	i=1;
			   	    }
				
			   	 try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/appointment_check.php?"
								+ "ID=" + URLEncoder.encode(ID,"UTF-8")
								);					
						url.openStream();		
						String result = getXmlData("appointment_check.xml", "result");
						t = result;
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
				    
			   	    
			   	    if(t.equals("1"))
			   	    {
			   	    	Toast.makeText(Conform_no.this, "저장된 기록을 불러옵니다.", Toast.LENGTH_SHORT).show();
			   	    	q=1;
			   	    }    
			   	    
			   	if(q==0)    
			   	{
					if(i==0)
					{
						 if(s_hour>=hour)
						   	{
						   		if(s_min>=min)
						   		{
						   			a=1;	// 성공
						   		}
						   		else
						   			a=2;
						   	}
					   	 else
					   		 a=2;
					//	 Toast.makeText(Conform_no.this, "a:"+a, Toast.LENGTH_SHORT).show();		
						 if(a==1)
						 {
							 try{//////////////////////////////예약 신청 / 취소
									URL url = new URL(SERVER_ADDRESS + "/insert_appointment_s.php?"
											+ "ID=" + URLEncoder.encode(ID,"UTF-8")
											+ "&t=" + URLEncoder.encode(k_hour+":"+k_min,"UTF-8")
											);					
									url.openStream();		
									String result = getXmlData("insert_appointment_s.xml", "result");
									t = result;
									
								} catch(Exception e) {
									Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
							
							 try{//////////////////////////////예약 신청 / 취소
									URL url = new URL(SERVER_ADDRESS + "/count_check.php?"
											+ "ID=" + URLEncoder.encode(ID,"UTF-8")
											+ "&t=" + URLEncoder.encode(k_hour+":"+k_min,"UTF-8")
											);					
									url.openStream();		
									money = getXmlData("count_check.xml", "result");
									count = getXmlData("count_check.xml", "result");
									i_money      = Integer.parseInt(money);
									i_count      = Integer.parseInt(count);
										i_money++;
								//		Toast.makeText(Conform_no.this, "i_money="+i_money+"/ i_count="+i_count, Toast.LENGTH_SHORT).show();
										
								money= String.valueOf(i_money);
								i_count=i_count-i_money;
								 try{//////////////////////////////예약 신청 / 취소
										URL url2 = new URL(SERVER_ADDRESS + "/count_update.php?"
												+ "money=" + URLEncoder.encode(money,"UTF-8")
												+ "&ID=" + URLEncoder.encode(ID,"UTF-8")
												);					
										url2.openStream();		
										String result = getXmlData("count_update.xml", "result");
										t = result;
										
									} catch(Exception e) {
										Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
										Log.e("Error", e.getMessage());
									}
									
								} catch(Exception e) {
									Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
							 
							 
						 }
						 else if(a==2)
						 {
							 try{//////////////////////////////예약 신청 / 취소
									URL url = new URL(SERVER_ADDRESS + "/insert_appointment_f.php?"
											+ "ID=" + URLEncoder.encode(ID,"UTF-8")
											+ "&t=" + URLEncoder.encode(k_hour+":"+k_min,"UTF-8")
											);					
									url.openStream();		
									String result = getXmlData("insert_appointment_f.xml", "result");
									t = result;
								} catch(Exception e) {
									Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
						 }
						
						 try{//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/count_check.php?"
										+ "ID=" + URLEncoder.encode(ID,"UTF-8")
										+ "&t=" + URLEncoder.encode(k_hour+":"+k_min,"UTF-8")
										);					
								url.openStream();		
								money = getXmlData("count_check.xml", "result");
								count = getXmlData("count_check.xml", "result");
								i_money      = Integer.parseInt(money);
								i_count      = Integer.parseInt(count);
									
								//	Toast.makeText(Conform_no.this, "i_money="+i_money+"/ i_count="+i_count, Toast.LENGTH_SHORT).show();
									
							money= String.valueOf(i_money);
							i_count=i_count-i_money;
							
							} catch(Exception e) {
								Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
						 
						 try{//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/arrive_t_check.php?"
										+ "ID=" + URLEncoder.encode(ID,"UTF-8")
										+ "&t=" + URLEncoder.encode(k_hour+":"+k_min,"UTF-8")
										);					
								url.openStream();		
								String result = getXmlData("arrive_t_check.xml", "result");
								arrive = result;
								ready = getXmlData("arrive_t_check.xml", "result2");
							} catch(Exception e) {
								Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
						
						
						 TextView testView1 = (TextView)findViewById(R.id.textView1);
							testView1.setText(name);
							TextView testView2 = (TextView)findViewById(R.id.textView2);
							testView2.setText(time);
							TextView testView3 = (TextView)findViewById(R.id.textView3);
							testView3.setText(arrive);
							if(ready.equals("1"))
								cost="면제";
							else
							{
							//	Toast.makeText(Conform_no.this, "i_cost="+i_cost+"/ i_count="+i_count, Toast.LENGTH_SHORT).show();
									i_cost=i_cost/i_count;
								 cost = String.valueOf(i_cost);
								 
							}
							TextView testView4 = (TextView)findViewById(R.id.textView4);
							testView4.setText(cost);
						   	
					   	    
					   
						
					   	 
					   	 
					   	 
					}
			   	}
			   	else{
			   	
			   	
			   	/////////////////////////////////
			   	

				 try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/count_check.php?"
								+ "ID=" + URLEncoder.encode(ID,"UTF-8")
								+ "&t=" + URLEncoder.encode(k_hour+":"+k_min,"UTF-8")
								);					
						url.openStream();		
						money = getXmlData("count_check.xml", "result");
						count = getXmlData("count_check.xml", "result2");
						i_money      = Integer.parseInt(money);
						i_count      = Integer.parseInt(count);
							
					//		Toast.makeText(Conform_no.this, "i_money="+i_money+"/ i_count="+i_count+"/ count="+count, Toast.LENGTH_SHORT).show();
							
					money= String.valueOf(i_money);
					i_count=i_count-i_money;
					
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
			   	
			   	
			   	
			   	
			   	///////////////////////////////////
			   	
			   	
			    try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/arrive_t_check.php?"
							+ "ID=" + URLEncoder.encode(ID,"UTF-8")
							+ "&t=" + URLEncoder.encode(k_hour+":"+k_min,"UTF-8")
							);					
					url.openStream();		
					String result = getXmlData("arrive_t_check.xml", "result");
					arrive = result;
					ready = getXmlData("arrive_t_check.xml", "result2");
				} catch(Exception e) {
					Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
			
			
			   TextView testView1 = (TextView)findViewById(R.id.textView1);
				testView1.setText(name);
				TextView testView2 = (TextView)findViewById(R.id.textView2);
				testView2.setText(time);
				TextView testView3 = (TextView)findViewById(R.id.textView3);
				testView3.setText(arrive);
				if(ready.equals("1"))
					cost="면제";
				else
				{
				//	Toast.makeText(Conform_no.this, "i_cost="+i_cost+"/ i_count="+i_count, Toast.LENGTH_SHORT).show();
						i_cost=i_cost/i_count;
					 cost = String.valueOf(i_cost);
					 
				}
				TextView testView4 = (TextView)findViewById(R.id.textView4);
				testView4.setText(cost);
			   	
			   	
			   	}
			   	
			}
		});
   	    

        delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=0;
				
				 try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/delete.php?"
								+ "ID=" + URLEncoder.encode(ID,"UTF-8")
								);					
						url.openStream();		
						String result = getXmlData("delete.xml", "result");
						t = result;
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
				    
			   	    
			   	   
			   	  Toast.makeText(Conform_no.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
			   	    	
			   	  finish();
				
				
				
				


				
				
				
				
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
		
	    private ArrayList<String> getXmlDataList(String filename, String str) { //占승그곤옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌨아울옙占쏙옙占쏙옙占쏙옙 ArrayList<String>占쏙옙 占쏙옙占쏙옙
			String rss = SERVER_ADDRESS + "/";
			ArrayList<String> ret = new ArrayList<String>();
			
			try { //XML 占식쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
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
		getMenuInflater().inflate(R.menu.conform_no, menu);
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
