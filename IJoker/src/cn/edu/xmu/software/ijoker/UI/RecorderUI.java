package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.edu.xmu.software.ijoker.R;

public class RecorderUI extends Activity {
	
	private EditText jokeTitle_txt,keyword_txt;
	private ProgressBar record_progress;
	private Button listen_btn,record_btn,clear_btn,upload_btn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recorder);
		this.stopService(new Intent("cn.edu.xmu.software.ijoker.REMOTE_SERVICE"));
		find();
	}
	
	private void find(){
		jokeTitle_txt=(EditText)findViewById(R.id.jokeTitle_txt);
		keyword_txt=(EditText)findViewById(R.id.keyword_txt);
		record_progress=(ProgressBar)findViewById(R.id.record_progress);
		listen_btn=(Button)findViewById(R.id.listen_btn);
		record_btn=(Button)findViewById(R.id.record_btn);
		clear_btn=(Button)findViewById(R.id.clear_btn);
		upload_btn=(Button)findViewById(R.id.upload_btn);
		
	}

}
