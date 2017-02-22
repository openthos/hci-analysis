package com.example.threadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public  class MainActivity extends Activity implements OnClickListener{

	private TextView textView;
	private Button button;	
	private Handler myHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView=(TextView)findViewById(R.id.text_view);
		button=(Button)findViewById(R.id.button);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d("LOG", "call start (7)"+Thread.currentThread().getId());
		ThreadDetour dt=ThreadDetourFactory.getThreadDetour(new MyThread(),7);
		//如果不是这样开启线程怎么处理。。。。。。
		dt.getThread1().start();
		Log.d("LOG", "call end (7)"+Thread.currentThread().getId());
	}
	
	public class MyThread extends Thread{
		public void run(){
			Log.d("LOG", "upcall start (19)"+Thread.currentThread().getId());
			try {
				
				sleep(10*1000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Runnable runnable=new Runnable(){

				@Override
				public void run() {
					Log.d("LOG", "callup start(21);"+Thread.currentThread().getId());
					// TODO Auto-generated method stub
					textView.setText("after update");
					Log.d("LOG", "callup end(21);"+Thread.currentThread().getId());
				}
				
			};
			Log.d("LOG", "call start(13);"+Thread.currentThread().getId());
			//给myHandler.post(dt.r);这个系统调用标号为１３
			RunnableDetour dt=RunnableDetourFactory.getDetour(runnable,13);
			myHandler.post(dt.r1);
			Log.d("LOG", "call end(13);"+Thread.currentThread().getId());
			Log.d("LOG", "upcall end (19)"+Thread.currentThread().getId());
		}
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