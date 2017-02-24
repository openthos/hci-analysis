package com.example.threadtest;

import android.util.Log;

public class ThreadDetour {

	private Thread thread1;
	private Thread thread;
	private int matchId;
	public ThreadDetour(Thread t, int matchId) {
		// TODO Auto-generated constructor stub
		this.thread=t;
		this.matchId=matchId;
	}

	public Thread getThread1(){
		thread1=new Thread(){
			public void run(){
				//问题：再开一个线程不合理。。。。。。。。
				Log.d("LOG", "callback start ("+matchId+")"+Thread.currentThread().getId());
				thread.start();
				
			}
		};
		return thread1;
	}
}
