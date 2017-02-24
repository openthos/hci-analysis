package com.example.sendmessagetest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class Detour {

	private Handler handler;
	private Handler handler1;
	private int matchId;
	public Detour(Handler handler,int matchId){
		this.handler=handler;
		this.matchId=matchId;
	}
	public Handler getHandler1(){
		Looper.prepare();
		handler1=new Handler(){
			public void handleMessage(Message msg){
				Log.d("LOG", "callback start ("+matchId+")"+Thread.currentThread().getId());
				Message message=new Message();
				message.what=msg.what;
				handler.sendMessage(message);
				getLooper().quit();
			}
		};
		
		//Looper.loop();
		return handler1;
	}
}
