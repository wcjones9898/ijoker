package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.edu.xmu.software.ijoker.R;

public class Functions extends Activity {

	private String funcNames[] = { "听笑话", "讲笑话", "搜索" , "关于'笑客'" };
	private int funcImages[] = { R.drawable.icon_listen, R.drawable.icon_record, R.drawable.icon_search,R.drawable.icon_about };
	private int funcIndex;
	private int funcNum;
	private TextView funcs_text;
	private ImageButton funcs_img,leftfunc_btn,rightfunc_btn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.functions);
		funcIndex = 0;
		funcNum = 4;

		funcs_text = (TextView) findViewById(R.id.Funcs_text);
		funcs_img = (ImageButton) findViewById(R.id.Funcs_button);
		funcs_img.setOnClickListener(enterFunc);
		leftfunc_btn = (ImageButton) findViewById(R.id.LeftFunc_button);
		rightfunc_btn = (ImageButton) findViewById(R.id.RightFunc_button);
		leftfunc_btn.setOnClickListener(leftFunc);
		leftfunc_btn.setOnTouchListener(leftFuncTouched);
		rightfunc_btn.setOnClickListener(rightFunc);
		rightfunc_btn.setOnTouchListener(rightFuncTouched);

	}

	private ImageButton.OnClickListener leftFunc = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			
			funcIndex--;
			if (funcIndex < 0)
				funcIndex = funcNum - 1;
			funcs_text.setText(funcNames[funcIndex]);
			funcs_img.setBackgroundResource(funcImages[funcIndex]);			
		}
	};
	
	private ImageButton.OnTouchListener leftFuncTouched = new ImageButton.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			   
			   case MotionEvent.ACTION_DOWN:
				   leftfunc_btn.setImageResource(R.drawable.button_left_touched);				   
				   break;			   
			   case MotionEvent.ACTION_UP:				   
				   leftfunc_btn.setImageResource(R.drawable.button_left);
				   break;			   
			}
			return false;
		}
	};

	private ImageButton.OnClickListener rightFunc = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			funcIndex++;
			if (funcIndex == funcNum)
				funcIndex = 0;
			funcs_text.setText(funcNames[funcIndex]);
			funcs_img.setBackgroundResource(funcImages[funcIndex]);
		}
	};
	
	private ImageButton.OnTouchListener rightFuncTouched = new ImageButton.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {			   
			   case MotionEvent.ACTION_DOWN:
				   rightfunc_btn.setImageResource(R.drawable.button_right_touched);				   
				   break;
			   case MotionEvent.ACTION_UP:
				   rightfunc_btn.setImageResource(R.drawable.button_right);
				   break;			  
			}
			return false;
		}
	};

	private ImageButton.OnClickListener enterFunc = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();

			switch (funcIndex) {
			case 0:
				intent.setClass(Functions.this, JokeList.class);
				break;
			case 1:
				intent.setClass(Functions.this, RecorderUI.class);
				break;
			case 2:
				intent.setClass(Functions.this, Search.class);
				break;
			case 3:
				intent.setClass(Functions.this, About.class);
				break;
			}

			startActivity(intent);
		}
	};
}
