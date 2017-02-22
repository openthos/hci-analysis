package com.example.sendmessagetest;

import java.util.Random;

import android.os.Handler;
import android.util.Log;

public class DetourFactory {
	
	public static Detour getDetour(Handler handler,int callId){
		int matchId=new Random().nextInt(10)+1;
		Log.d("LOG", "async start ("+callId+","+matchId+")"+Thread.currentThread().getId());
		return new Detour(handler,matchId);
		
	}

}
