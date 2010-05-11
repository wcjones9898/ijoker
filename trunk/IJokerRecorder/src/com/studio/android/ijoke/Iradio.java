package com.studio.android.ijoke;



import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class Iradio extends Activity {
    /** Called when the activity is first created. */

	final int DIALOG_PROGRESS = 1;
	final int MAXPROGRESS = 100;
	final int MSG_PROGRESS = 1;
	ProgressDialog pd;
	Handler pHandler;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btn1 =(Button)findViewById(R.id.Button01);
		btn1.setOnClickListener(new View.OnClickListener(){  
			public void onClick(View v) {  
			Intent intent = new Intent();  
			intent.setClass(Iradio.this, RecorderActivity.class);  
			startActivity(intent);  
			finish();//停止当前的Activity,如果不写,则按返回键会跳转回原来的Activity        
			}  
			});  

//		this.pHandler = new Handler()
//		{
//			public void handleMessage(Message msg)
//			{
//				super.handleMessage(msg);
//				switch(msg.what)
//				{
//				
//				case MSG_PROGRESS:
//					if(pd.getProgress()>= MAXPROGRESS)
//					{
//						pd.dismiss();
//					}else{
//						pd.incrementProgressBy(1);
//						pHandler.sendEmptyMessageDelayed(MSG_PROGRESS, 100);
//					}
//					break;
//					default:
//						break;
//				}
//			}
//		};
//
//		showDialog(DIALOG_PROGRESS);
//		pHandler.sendEmptyMessage(MSG_PROGRESS);
		
		
		
    } 
//	protected Dialog onCreateDialog(int id)
//	{
//		switch(id)
//		{
//		case DIALOG_PROGRESS:
//			pd = new ProgressDialog(this);
//			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//			pd.setMax(this.MAXPROGRESS);
//			pd.setProgress(0);
//			return pd;
//		}
//		return null;
//	}
}