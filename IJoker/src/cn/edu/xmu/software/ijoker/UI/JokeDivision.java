package cn.edu.xmu.software.ijoker.UI;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.entity.ClassItem;
import cn.edu.xmu.software.ijoker.service.IPlayService;
import cn.edu.xmu.software.ijoker.service.PlayService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class JokeDivision extends BaseActivity {

	private ListView listView;
	// private String divisions[] = { "经典笑料", "网络流行", "笑客原创", "曲艺相声" };
	private final String TAG = JokeDivision.class.getName();
	private IPlayService playService;
	private int position = 0;
	private List<ClassItem> divisionList;
	private ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jokedivision);
		registerReceiver(receiver, new IntentFilter(
				Consts.ACTION_DIVISIONLIST_READY));
		// this
		// .startService(new Intent(
		// "cn.edu.xmu.software.ijoker.PLAY_SERVICE"));
		this.bindService(new Intent(JokeDivision.this, PlayService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);
		listView = (ListView) findViewById(R.id.divisionList);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				position = arg2;
				Intent intent = new Intent();
				ClassItem classItem = divisionList.get(arg2);
				intent.putExtra("classId", classItem.getClassId());
				intent.putExtra("className", classItem.getClassName());
				intent.putExtra("jokeNum", classItem.getJokeNum());
				intent.setClass(JokeDivision.this, JokeList.class);
				startActivity(intent);
			}
		});
	}

	private String[] buildPlayListForSimpleAdapter() {
		try {
			divisionList = playService.getDivisionList();
			Log.i(TAG, "divisionList: " + divisionList + "; size: "
					+ divisionList.size());
		} catch (RemoteException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		if (divisionList != null) {
			String list[] = new String[divisionList.size()];
			for (int i = 0; i < divisionList.size(); i++) {
				list[i] = divisionList.get(i).getClassName();
			}
			return list;
		}
		return null;

	}

	public void updateDivisionList() {
		String list[] = buildPlayListForSimpleAdapter();
		// ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(this,
		// android.R.layout.preference_category, list);
		ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(this,
				android.R.layout.browser_link_context_header, list);
		listView.setAdapter(divisionAdapter);
		listView.setSelection(position);
		progressDialog.dismiss();
	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equalsIgnoreCase(
					Consts.ACTION_DIVISIONLIST_READY)) {
				switch (intent.getIntExtra("errorCode", -1)) {
				case Consts.ERROR_NOERROR:
					updateDivisionList();
					break;
				case Consts.ERROR_CALLWEBSERVICE:
					progressDialog.dismiss();
					Log.i(TAG, "have error message: " + Consts.NETWORK_FAILED);
					Toast.makeText(JokeDivision.this, Consts.NETWORK_FAILED,
							Toast.LENGTH_SHORT).show();
					break;
				default:
				}
			}
		}
	};
	private ServiceConnection serviceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			playService = IPlayService.Stub.asInterface((IBinder) service);
			Log.i(TAG, "connect to playservice.");
			try {
				progressDialog = ProgressDialog.show(JokeDivision.this, "提示",
						"正在获取列表，请稍候...", true);
				playService.updateDivisionList();
			} catch (RemoteException e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}

		public void onServiceDisconnected(ComponentName className) {
			playService = null;
		}
	};

	protected void onResume() {
		super.onResume();
		// updateDivisionList();
		this.bindService(new Intent(JokeDivision.this, PlayService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);
		registerReceiver(receiver, new IntentFilter(
				Consts.ACTION_DIVISIONLIST_READY));
	}

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
		unbindService(serviceConnection);
	}
}
