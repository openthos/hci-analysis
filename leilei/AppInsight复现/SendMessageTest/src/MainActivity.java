package com.example.sendmessagetest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	public static final int UPDATE_TEXT=1;
	private TextView text;
	private Button changeText;
	private Handler handler=new Handler(){
		public void handleMessage(Message msg){
			Log.d("LOG", "upcall start (21)"+Thread.currentThread().getId());
			switch(msg.what){
			case UPDATE_TEXT:
				text.setText("Nice to meet you");
				break;
			default:
				break;
			}
			Log.d("LOG", "upcall end (21)"+Thread.currentThread().getId());
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text=(TextView)findViewById(R.id.text);
		changeText=(Button)findViewById(R.id.change_text);
		changeText.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message message=new Message();
				message.what=UPDATE_TEXT;
				Log.d("LOG", "call start(13)"+Thread.currentThread().getId());
				Detour dt=DetourFactory.getDetour(handler,13);
				dt.getHandler1().sendMessage(message);
				Looper.loop();
				Log.d("LOG","call end(13)"+Thread.currentThread().getId());
			}
			
		}).start();
	}
}
