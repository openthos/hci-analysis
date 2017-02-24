package com.example.posttest;

import java.util.Random;

import android.util.Log;

public class DetourFactory {

	public static Detour getDetour(Runnable r,int callId){
		//生成１到１０随机数
		int matchId=new Random().nextInt(10)+1; 
		Log.d("LOG", "AsyncStart,"+"("+callId+","+matchId+");"+Thread.currentThread().getId());
		return new Detour(r,matchId);
	}
}
