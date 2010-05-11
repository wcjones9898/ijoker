package cn.edu.xmu.software.ijoker.UI;

import cn.edu.xmu.software.ijoker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Search extends BaseActivity {
	
	private EditText searchKeyword_txt;
	private Button search_btn;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        find();
	}
	
	private void find(){
		searchKeyword_txt=(EditText)findViewById(R.id.searchKeyword_txt);
		search_btn=(Button)findViewById(R.id.search_btn);
		search_btn.setOnClickListener(search);
	}
	
	private Button.OnClickListener search = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Search.this, JokeList.class);
			startActivity(intent);
		}
	};
}
