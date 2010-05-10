package cn.edu.xmu.software.ijoker.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.service.IPlayService;
import cn.edu.xmu.software.ijoker.service.PlayService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class JokeList extends Activity {
	private ListView listView;
	private List<Joke> jokeList;
	private IPlayService playService;
	private int position = 0;
	private final String TAG = JokeList.class.getName();

	private ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jokelist);
		listView = (ListView) findViewById(R.id.JokeList);
		registerReceiver(receiver, new IntentFilter(
				Consts.ACTION_JOKELIST_READY));
		this.startService(new Intent(
				"cn.edu.xmu.software.ijoker.REMOTE_SERVICE"));
		this.bindService(new Intent(JokeList.this, PlayService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);

	}

	private ServiceConnection serviceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			playService = IPlayService.Stub.asInterface((IBinder) service);
			Log.i(TAG, "connect to playservice.");
			try {
				progressDialog = ProgressDialog.show(JokeList.this, "提示",
						"正在获取列表，请稍候...", true);
				playService.updateJokeList();
			} catch (RemoteException e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}

		public void onServiceDisconnected(ComponentName className) {
			playService = null;
		}
	};

	// update the jokelist in UI.
	public void updateJokeList() {
		List<Map<String, Object>> list = buildPlayListForSimpleAdapter();
		// 生成适配器的Item和动态数组对应的元素
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, list,// 数据源
				R.layout.jokelist_style,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "likeNum", "jokeTitle", "joker", "uploadTime" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.likeNum, R.id.jokeTitle, R.id.joker,
						R.id.uploadTime });

		// 添加并且显示
		listView.setAdapter(listItemAdapter);
		listView.setSelection(position);
		progressDialog.dismiss();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// setTitle("点击第" + arg2 + "个项目");
				position = arg2;

				// chose the joke to play and change the view to playUI;
				try {
					playService.chose(arg2);
					Log.i(TAG, "chose the " + arg2
							+ " joke to play and change the view to playUI");
				} catch (RemoteException e) {
					Log.e(TAG, e.getMessage(), e);
				}
				Intent intent = new Intent();
				intent.setClass(JokeList.this, PlayerUI.class);
				Joke joke = jokeList.get(arg2);
				Bundle jokeInfo = new Bundle();
				jokeInfo.putParcelable("joke", joke);
				intent.putExtra("android.intent.extra.jokeInfo", jokeInfo);
				startActivityForResult(intent, 0);
			}

		});

		// 添加长按点击
		listView
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						menu.setHeaderTitle("长按菜单-ContextMenu");
						menu.add(0, 0, 0, "弹出长按菜单0");
						menu.add(0, 1, 0, "弹出长按菜单1");
					}
				});
	}

	// clear the jokelist data int playservice to get new data from
	// webservice into playlist.
	private List<Map<String, Object>> buildPlayListForSimpleAdapter() {
		try {
			// playService.clearJokeList();
			// if (jokeList == null)
			jokeList = playService.getJokeList();
			Log.i(TAG, "jokeList: " + jokeList + "; size: " + jokeList.size());
		} catch (RemoteException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		if (jokeList != null) {
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>(
					jokeList.size());
			Iterator<Joke> iterator = jokeList.iterator();
			while (iterator.hasNext()) {
				Joke joke = (Joke) iterator.next();
				// try {
				// playService.addJokeList(joke.getLocation());
				// } catch (RemoteException e) {
				// e.printStackTrace();
				// }
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("likeNum", joke.getLike());// 图像资源的ID
				map.put("jokeTitle", joke.getTitle());
				map.put("joker", joke.getAuthor());
				map.put("uploadTime", "（" + joke.getUploadTime() + "）");
				list.add(map);
			}
			return list;
		}
		return null;

	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equalsIgnoreCase(
					Consts.ACTION_JOKELIST_READY)) {
				switch (intent.getIntExtra("errorCode", -1)) {
				case Consts.ERROR_NOERROR:
					updateJokeList();
					break;
				case Consts.ERROR_CALLWEBSERVICE:
					progressDialog.dismiss();
					Log.i(TAG, "have error message: " + Consts.NETWORK_FAILED);
					Toast.makeText(JokeList.this, Consts.NETWORK_FAILED,
							Toast.LENGTH_SHORT).show();
					break;
				default:
				}
			}
		}
	};

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		setTitle("点击了长按菜单里面的第" + item.getItemId() + "个项目");
		return super.onContextItemSelected(item);
	}

	protected void onResume() {
		super.onResume();
		this.bindService(new Intent(JokeList.this, PlayService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);
		registerReceiver(receiver, new IntentFilter(
				Consts.ACTION_JOKELIST_READY));
	}

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
		unbindService(serviceConnection);
	}
}
