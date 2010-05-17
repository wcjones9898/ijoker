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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.entity.ClassItem;
import cn.edu.xmu.software.ijoker.service.DivisionListService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class JokeDivision extends BaseActivity {

	private ListView listView;
	private final String TAG = JokeDivision.class.getName();
	private List<ClassItem> divisionList;
	private ProgressDialog progressDialog;
	private DivisionListService divisionService;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.MSG_DIVISIONLIST_READY:
				divisionList = msg.getData().getParcelableArrayList("data");
				Log.i(TAG, "divisionList: " + divisionList + "; size: "
						+ divisionList.size());
				updateDivisionList();
				Log
						.i(
								TAG,
								"divisionservice get the divisionlist from webservice and pass the divisionlist to divisionlist UI!"
										+ divisionList.size());
				break;
			case Consts.ERROR_CALLWEBSERVICE:
				Toast.makeText(JokeDivision.this, Consts.NETWORK_FAILED,
						Toast.LENGTH_SHORT).show();
			default:
			}
			progressDialog.dismiss();

		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jokedivision);
		listView = (ListView) findViewById(R.id.divisionList);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ClassItem classItem = divisionList.get(arg2);
				if (classItem.getJokeNum() == 0) {
					Toast.makeText(JokeDivision.this, Consts.NOJOKE,
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent();
					intent.setAction("cn.edu.xmu.software.ijoker.divisionList");
					intent.putExtra("catalogId", classItem.getClassId());
					intent.putExtra("catalogName", classItem.getClassName());
					intent.putExtra("jokeNum", classItem.getJokeNum());
					intent.setClass(JokeDivision.this, JokeList.class);
					startActivity(intent);
				}
			}
		});
		divisionService = new DivisionListService(handler);
		initDivisionList();
	}

	private void initDivisionList() {
		progressDialog = ProgressDialog.show(JokeDivision.this, "提示",
				"正在获取列表，请稍候...", true);
		divisionService.getDivisionList();
	}

	private List<Map<String, Object>> buildPlayListForSimpleAdapter() {
		if (divisionList != null) {
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>(
					divisionList.size());
			Iterator<ClassItem> iterator = divisionList.iterator();
			while (iterator.hasNext()) {
				ClassItem classItem = (ClassItem) iterator.next();

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("divisionTitle", classItem.getClassName() + " ("
						+ classItem.getJokeNum() + ")");
				list.add(map);
			}
			return list;

		}
		return null;

	}

	public void updateDivisionList() {
		List<Map<String, Object>> list = buildPlayListForSimpleAdapter();
		SimpleAdapter divisionAdapter = new SimpleAdapter(this, list,
				R.layout.divisionlist_style, new String[] { "divisionTitle" },
				new int[] { R.id.divisionTitle });
		listView.setAdapter(divisionAdapter);
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onDestroy() {
		super.onDestroy();
		this.stopService(new Intent(
		"cn.edu.xmu.software.ijoker.PLAY_SERVICE"));
	}
}
