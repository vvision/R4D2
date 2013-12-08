package com.android.utbm.lo52.libbmp;

import android.util.Log;

public class Example {

	static {
		Log.d("pwet", "Loading....");
		System.loadLibrary("bmpExample_jni");
	}

	public native int bpmFunction(String filename, int width, int height, int depth);

	public Example() {

	}

	public static void main (){
    bmpFunction("file", 800, 600, 42);
  } 

}
