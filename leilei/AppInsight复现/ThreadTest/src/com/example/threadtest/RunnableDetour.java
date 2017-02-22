package com.example.threadtest;

import android.util.Log;

public class RunnableDetour {

	int matchId;
	Runnable r;
	Runnable r1 = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d("LOG", "callback start,"+"("+matchId+");"+Thread.currentThread().getId());
			r.run();
		}
		
	};
	
	public RunnableDetour(Runnable r,int matchId){
		this.r=r;
		this.matchId=matchId;
	}
	
}

