package cn.edu.xmu.software.ijoker.UI;

import cn.edu.xmu.software.ijoker.util.MenuUtils;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends Activity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		super.onCreateOptionsMenu(menu);				
		return MenuUtils.addCommonMenu(menu);	
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		Intent redirectIntent=new Intent();
		switch (item.getItemId()) {
			case MenuUtils.LISTEN_JOKE:
				redirectIntent.setClass(this, JokeDivision.class);
				startActivity(redirectIntent);
	            return true;
			case MenuUtils.RECORD_JOKE:
				redirectIntent.setClass(this, RecorderUI.class);
				startActivity(redirectIntent);
	            return true;
			case MenuUtils.FIND_JOKE:
				redirectIntent.setClass(this, Search.class);
				startActivity(redirectIntent);
	            return true;
			case MenuUtils.LOGOUT:
				redirectIntent.setClass(this, Login.class);
				startActivity(redirectIntent);
	            return true;
	        default:
	        	return true;
		}
		
	}

}
