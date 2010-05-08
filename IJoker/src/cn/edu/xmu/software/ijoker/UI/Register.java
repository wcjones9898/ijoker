package cn.edu.xmu.software.ijoker.UI;

import cn.edu.xmu.software.ijoker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends Activity {
	
	private Button register_btn;
	private EditText username_txt,password_txt,repeatPassword_txt; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        find();
    }
	private void find(){
		
		register_btn=(Button)findViewById(R.id.register_btn);
		register_btn.setOnClickListener(register);
		
		username_txt=(EditText)findViewById(R.id.username);
		password_txt=(EditText)findViewById(R.id.password);
		repeatPassword_txt=(EditText)findViewById(R.id.repeatPassword);
	}
	
	private Button.OnClickListener register=new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent =new Intent();
    		intent.setClass(Register.this,Loading.class);
    		startActivity(intent);			
		}    	
    };

}
