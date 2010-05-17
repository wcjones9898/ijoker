package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.util.Consts;
import cn.edu.xmu.software.ijoker.util.MenuUtils;

public class BaseActivity extends Activity {
	private static final int DIALOG_TEXT_ENTRY = 1;
	private static final String TAG = BaseActivity.class.getName();

	protected String getMediaCenterURL() {
		SharedPreferences settings = getSharedPreferences(
				Consts.preferencesSetting, 0);
		String media_ip = settings.getString(Consts.MEDIA_IP, "59.77.5.42:80");
		return Consts.HTTP + media_ip;
	}

	protected String getServiceBaseURL() {
		SharedPreferences settings = getSharedPreferences(
				Consts.preferencesSetting, 0);
		String server_ip = settings.getString(Consts.SERVER_IP,
				"59.77.5.42:8080");
		return Consts.HTTP + server_ip + Consts.SERVICE_BASE_URL;
	}

	protected String getServerUploadURL() {
		SharedPreferences settings = getSharedPreferences(
				Consts.preferencesSetting, 0);
		String server_ip = settings.getString(Consts.SERVER_IP,
				"59.77.5.42:8080");
		return Consts.HTTP + server_ip + Consts.SERVER_UPLOAD_URL;
	}

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
			showDialog(DIALOG_TEXT_ENTRY);
		default:
			return true;
		}

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_TEXT_ENTRY:
			LayoutInflater factory = LayoutInflater.from(this);
			final View textEntryView = factory.inflate(
					R.layout.alert_dialog_text_entry, null);
			return new AlertDialog.Builder(BaseActivity.this).setIcon(
					R.drawable.alert_dialog_icon).setTitle(
					R.string.alert_dialog_text_entry).setView(textEntryView)
					.setPositiveButton(R.string.alert_dialog_ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									TextView tv1 = (TextView) textEntryView
											.findViewById(R.id.media_ip_edit);
									TextView tv2 = (TextView) textEntryView
											.findViewById(R.id.server_ip_edit);
									String media_ip = tv1.getText().toString();
									String server_ip = tv2.getText().toString();
									SharedPreferences settings = getSharedPreferences(
											Consts.preferencesSetting, 0);
									Editor editer = settings.edit();
									if (!media_ip.equals("")) {
										editer.putString(Consts.MEDIA_IP,
												media_ip);
										Log.i(TAG,
												"save data to preferences with media ip: "
														+ media_ip);
									}
									if (!server_ip.equals("")) {
										editer.putString(Consts.SERVER_IP,
												server_ip);
										Log.i(TAG,
												"save data to preferences with server ip: "
														+ server_ip);
									}
									editer.commit();
									Toast.makeText(
											BaseActivity.this,
											"Media IP:" + media_ip
													+ "\nServer IP:"
													+ server_ip,
											Toast.LENGTH_SHORT).show();
								}
							}).setNegativeButton(R.string.alert_dialog_cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked cancel so do some stuff */
								}
							}).create();
		}
		return super.onCreateDialog(id);

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
