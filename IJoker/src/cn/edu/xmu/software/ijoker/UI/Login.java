package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.edu.xmu.software.ijoker.R;

public class Login extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        find();
    }
    
    private void find(){
    	Button login_btn=(Button)findViewById(R.id.login_btn);
    	login_btn.setOnClickListener(login);
    }
    
    private Button.OnClickListener login=new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent =new Intent();
    		intent.setClass(Login.this,Loading.class);
    		startActivity(intent);			
		}    	
    };
}