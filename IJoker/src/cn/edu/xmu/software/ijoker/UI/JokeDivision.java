package cn.edu.xmu.software.ijoker.UI;

import cn.edu.xmu.software.ijoker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class JokeDivision extends Activity {
	
	private ArrayAdapter<String> divisionAdapter;
	private ListView divisionList;
	private String divisions[]={"经典笑料","网络流行","笑客原创","曲艺相声"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jokedivision);
		
		divisionList=(ListView)findViewById(R.id.divisionList);
		divisionAdapter=new ArrayAdapter<String>(this, android.R.layout.preference_category, divisions);
		divisionList.setAdapter(divisionAdapter);
		
		
		divisionList.setOnItemClickListener(new OnItemClickListener() {   		 
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(JokeDivision.this, JokeList.class);
				startActivity(intent);   
			}   
		});   
	}

}
