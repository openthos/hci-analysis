package com.example.threadtest;

import java.util.Random;

import android.util.Log;

public class ThreadDetourFactory {

	public static ThreadDetour getThreadDetour(Thread t,int callId) {
		// TODO Auto-generated method stub
		int matchId=new Random().nextInt(10)+1;
		Log.d("LOG", "async start ("+callId+","+matchId+")"+Thread.currentThread().getId());
		return new ThreadDetour(t,matchId);
	}

}
