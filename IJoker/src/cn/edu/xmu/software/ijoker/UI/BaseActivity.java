package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.MenuUtils;

public class BaseActivity extends Activity {
	private static final String TAG = BaseActivity.class.getName();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		return MenuUtils.addCommonMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent redirectIntent = new Intent();
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
			callLoginUI(Consts.ERROR_NOERROR);
			return true;
		case MenuUtils.IP:
			
		default:
			return true;
		}

	}

	protected void callLoginUI(int errorCode) {
		SharedPreferences settings = getSharedPreferences(
				Consts.preferencesSetting, 0);
		Editor editer = settings.edit();
		editer.putBoolean(Consts.autoLogin, false);
		editer.commit();
		Intent intent = new Intent();
		intent.setClass(BaseActivity.this, Login.class);
		intent.putExtra("errorCode", errorCode);
		startActivity(intent);
		finish();
	}

}
