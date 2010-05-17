package cn.edu.xmu.software.ijoker.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.service.JokeListService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class JokeList extends BaseActivity {
	private ListView listView;
	private List<Joke> jokeList;
	private int page = 1;
	private int pages = 0;
	private String classId;
	private final String TAG = JokeList.class.getName();
	private Button prev_button;
	private Button next_button;
	private ProgressDialog progressDialog;
	private JokeListService jokeListService;
	private boolean isSearch = false;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.MSG_JOKELIST_READY:
				jokeList = msg.getData().getParcelableArrayList("data");
				Log.i(TAG, "jokeList: " + jokeList + "; size: "
						+ jokeList.size());
				updateJokeList();
				Log
						.i(
								TAG,
								"jokeservice get the jokeList from webservice and pass the jokeList to jokeList UI!"
										+ jokeList.size());
				break;
			case Consts.ERROR_CALLWEBSERVICE:
				Toast.makeText(JokeList.this, Consts.NETWORK_FAILED,
						Toast.LENGTH_SHORT).show();
			default:
			}
			progressDialog.dismiss();

		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jokelist);
		listView = (ListView) findViewById(R.id.JokeList);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

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
		prev_button = (Button) findViewById(R.id.lastPage_btn);
		prev_button.setEnabled(false);
		next_button = (Button) findViewById(R.id.nextPage_btn);
		prev_button.setOnClickListener(pageListener);
		next_button.setOnClickListener(pageListener);
		TextView divisionTitile = (TextView) findViewById(R.id.divisionLabel);
		Intent intent = this.getIntent();
		int jokeNum;
		if (intent.getAction().equalsIgnoreCase(
				"cn.edu.xmu.software.ijoker.divisionList")) {
			divisionTitile
					.setText(this.getIntent().getStringExtra("catalogName"));
			classId = this.getIntent().getStringExtra("catalogId");
			jokeNum = this.getIntent().getIntExtra("jokeNum", 0);
		} else {
			Bundle b = intent.getBundleExtra("android.intent.extra.jokes");
			jokeList = b.getParcelableArrayList("data");
			jokeNum = jokeList.size();
			isSearch = true;
		}
		pages = jokeNum / Consts.PAGESIZE;
		if (jokeNum % Consts.PAGESIZE != 0)
			pages++;
		if (jokeNum <= Consts.PAGESIZE)
			next_button.setEnabled(false);
		jokeListService = new JokeListService(handler);
		getJokeList();
	}

	private Button.OnClickListener pageListener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i(TAG, "pages:" + pages + "page:" + page);
			if (v.equals(next_button)) {
				if (++page == pages)
					next_button.setEnabled(false);
				if (page == 2)
					prev_button.setEnabled(true);
				getJokeList();
			} else {
				if (--page == 1)
					prev_button.setEnabled(false);
				if (page == pages - 1)
					next_button.setEnabled(true);
				getJokeList();
			}
		}
	};

	private void getJokeList() {
		progressDialog = ProgressDialog.show(JokeList.this, "提示",
				"正在获取列表，请稍候...", true);
		if (isSearch) {
			ArrayList<Joke> list = new ArrayList<Joke>();
			int size = 0;
			if (page == pages) {
				size = jokeList.size() - (page - 1) * Consts.PAGESIZE;
			} else {
				size = Consts.PAGESIZE;
			}
			for (int i = (page - 1) * Consts.PAGESIZE; i < size; i++) {
				list.add(jokeList.get(i));
			}
			Message message = handler.obtainMessage(Consts.MSG_JOKELIST_READY);
			Bundle b = new Bundle();
			b.putParcelableArrayList("data", list);
			message.setData(b);
			handler.sendMessage(message);
		} else {
			jokeListService.getJokeList(page,classId);

		}
	}

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
	}

	// clear the jokelist data int playservice to get new data from
	// webservice into playlist.
	private List<Map<String, Object>> buildPlayListForSimpleAdapter() {
		Log.i(TAG, "jokeList: " + jokeList + "; size: " + jokeList.size());
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

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		setTitle("点击了长按菜单里面的第" + item.getItemId() + "个项目");
		return super.onContextItemSelected(item);
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onDestroy() {
		super.onDestroy();
	}

}
