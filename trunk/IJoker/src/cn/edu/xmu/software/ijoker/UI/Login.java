package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.edu.xmu.software.ijoker.R;

public class Login extends Activity {
	Button login_btn,register_btn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        find();
    }
    
    private void find(){
    	login_btn=(Button)findViewById(R.id.login_btn);
    	login_btn.setOnClickListener(login);
    	
    	register_btn=(Button)findViewById(R.id.register_btn);
    	register_btn.setOnClickListener(register);
    }
    
    private Button.OnClickListener login=new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent =new Intent();
    		intent.setClass(Login.this,Loading.class);
    		startActivity(intent);			
		}    	
    };
    
    private Button.OnClickListener register=new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent =new Intent();
    		intent.setClass(Login.this,Register.class);
    		startActivity(intent);			
		}    	
    };
}