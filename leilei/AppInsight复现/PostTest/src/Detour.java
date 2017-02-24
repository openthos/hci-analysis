package com.example.posttest;

import android.util.Log;

public class Detour {

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
	
	public Detour(Runnable r,int matchId){
		this.r=r;
		this.matchId=matchId;
	}
	
}
