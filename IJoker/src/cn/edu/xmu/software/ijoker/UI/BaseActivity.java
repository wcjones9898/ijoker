package cn.edu.xmu.software.ijoker.UI;

import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.MenuUtils;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
			callLoginUI();
			return true;
		default:
			return true;
		}

	}

	private void callLoginUI() {
		SharedPreferences settings = getSharedPreferences(
				Consts.preferencesSetting, 0);
		Editor editer = settings.edit();
		editer.putString(Consts.session, "");
		Log.i(TAG, "save data to preferences with session: " + "");
		editer.commit();
		Intent intent = new Intent();
		intent.setClass(BaseActivity.this, Login.class);
		intent.putExtra("errorCode", Consts.ERROR_NOERROR);
		startActivity(intent);
		finish();
	}

}
