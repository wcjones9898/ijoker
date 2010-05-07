package cn.edu.xmu.software.ijoker.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView.OnItemClickListener;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.service.IPlayService;
import cn.edu.xmu.software.ijoker.service.PlayService;

public class JokeList extends Activity {
	private ListView itemlist;
	private List<Joke> jokeList;
	private IPlayService playService;
	private int position = 0;
	private final String TAG = JokeList.class.getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jokelist);
		this.bindService(new Intent(JokeList.this, PlayService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);
		itemlist = (ListView) findViewById(R.id.JokeList);
	}

	private ServiceConnection serviceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			playService = IPlayService.Stub.asInterface((IBinder) service);
			Log.i(TAG, "connect to playservice and update the jokelist in UI.");
			updateJokeList();
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
				new String[] { "ItemImage", "ItemTitle", "ItemText" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.ItemImage, R.id.ItemTitle, R.id.ItemText });

		// 添加并且显示
		itemlist.setAdapter(listItemAdapter);
		itemlist.setSelection(position);
		itemlist.setOnItemClickListener(new OnItemClickListener() {

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
		itemlist
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
				map
						.put("ItemImage",
								android.R.drawable.checkbox_off_background);// 图像资源的ID
				map.put("ItemTitle", joke.getTitle());
				map.put("ItemText", joke.getAuthor());
				list.add(map);
			}
			return list;
		}
		return null;

	}

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
	}

	protected void onDestroy() {
		super.onDestroy();
		unbindService(serviceConnection);
	}
}
