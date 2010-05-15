package cn.edu.xmu.software.ijoker.UI;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.entity.Joke;
import cn.edu.xmu.software.ijoker.service.SearchService;
import cn.edu.xmu.software.ijoker.util.Consts;

public class Search extends BaseActivity {
	private ArrayList<Joke> jokeList;
	private SearchService searchService;
	private EditText searchKeyword_txt;
	private RadioGroup radio_group;
	private Button search_btn;
	private String keyword;
	private int searchType = 1;
	private ProgressDialog progressDialog;
	private final static String TAG = Search.class.getName();
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressDialog.dismiss();
			switch (msg.what) {
			case Consts.MSG_SEARCHJOKE_READY:
				jokeList = msg.getData().getParcelableArrayList("data");
				if (jokeList.size() == 0) {
					Toast.makeText(Search.this, Consts.SEARCH_NOJOKES,
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent();
					Bundle jokes = new Bundle();
					jokes.putParcelableArrayList("data", jokeList);
					intent.putExtra("android.intent.extra.jokes", jokes);
					intent.setClass(Search.this, JokeList.class);
					startActivity(intent);
					finish();
				}
				break;
			case Consts.ERROR_CALLWEBSERVICE:
				Toast.makeText(Search.this, Consts.NETWORK_FAILED,
						Toast.LENGTH_SHORT).show();
			default:
			}

		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		find();
	}

	private void find() {
		searchKeyword_txt = (EditText) findViewById(R.id.searchKeyword_txt);
		radio_group = (RadioGroup) findViewById(R.id.searchType);
		radio_group.setOnCheckedChangeListener(searchChoice);
		search_btn = (Button) findViewById(R.id.search_btn);
		search_btn.setOnClickListener(search);
	}

	private RadioGroup.OnCheckedChangeListener searchChoice = new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			searchType = checkedId + 1;
		}
	};

	private boolean validate() {
		boolean flag = true;
		if (keyword.equals("")) {
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			searchKeyword_txt.startAnimation(shake);
			flag = flag && false;
			Log.i(TAG, "keyword validate: " + flag);
		}
		return flag;
	}

	private Button.OnClickListener search = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			keyword = searchKeyword_txt.getText().toString();
			if (validate()) {
				progressDialog = ProgressDialog.show(Search.this, "提示",
						"正在搜索，请稍候...", true);
				searchService = new SearchService(handler);
				searchService.search(keyword, searchType);
				Log.i(TAG, "call search webservice with keyword: " + keyword
						+ " searchType: " + searchType);
			}
		}
	};
}
