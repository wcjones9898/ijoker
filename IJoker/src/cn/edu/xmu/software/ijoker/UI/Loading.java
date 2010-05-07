package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.edu.xmu.software.ijoker.R;

public class Loading extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
                
		Intent intent =new Intent();
		intent.setClass(Loading.this,Functions.class);
		startActivity(intent);	
		
    }
}

//class Loader extends Thread {
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//		try {
//			wait(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//}
